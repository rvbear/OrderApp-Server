package com.kiosk.server.domain.user.service;

import com.kiosk.server.domain.user.bean.GetUserEntityBean;
import com.kiosk.server.domain.user.bean.SaveUserEntityBean;
import com.kiosk.server.domain.user.data.UserEntity;
import com.kiosk.server.domain.user.data.UserRole;
import com.kiosk.server.domain.user.data.dto.in.DeleteUserDto;
import com.kiosk.server.domain.user.data.dto.in.LoginDto;
import com.kiosk.server.domain.user.data.dto.in.OAuthLoginDto;
import com.kiosk.server.domain.user.data.dto.in.UpdateUserDto;
import com.kiosk.server.domain.user.data.dto.out.GetUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${google.redirect-uri}")
    private String redirectUri;

    private final GetUserEntityBean getUserEntityBean;
    private final SaveUserEntityBean saveUserEntityBean;
    private final RestTemplate restTemplate = new RestTemplate();

    public GetUserDto getUser(UUID userId) {
        UserEntity user = getUserEntityBean.exec(userId);
        if (user == null) {
            return null;
        }

        return GetUserDto.builder().user(user).build();
    }

    public List<GetUserDto> getUserAll() {
        List<UserEntity> userList = getUserEntityBean.exec();
        if (userList.isEmpty()) {
            return Collections.emptyList();
        }

        return userList.stream()
                .map(user -> GetUserDto.builder().user(user).build())
                .collect(Collectors.toList());
    }

    public UUID updateUser(UpdateUserDto updateUserDto) {
        UserEntity user = getUserEntityBean.exec(updateUserDto.getUserId());
        if (user == null) {
            return null;
        }

        user.update(updateUserDto);
        saveUserEntityBean.exec(user);

        return user.getUserId();
    }

    public boolean deleteUser(DeleteUserDto deleteUserDto) {
        UserEntity user = getUserEntityBean.exec(deleteUserDto.getUserId());
        if (user == null) {
            return false;
        }

        user.delete();
        saveUserEntityBean.exec(user);

        return true;
    }

    public UUID oauthGoogleLogin(OAuthLoginDto oAuthLoginDto) {
        String code = oAuthLoginDto.getCode();

        String tokenUri = "https://oauth2.googleapis.com/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> tokenParams = new LinkedMultiValueMap<>();
        tokenParams.add("code", code);
        tokenParams.add("client_id", clientId);
        tokenParams.add("client_secret", clientSecret);
        tokenParams.add("redirect_uri", redirectUri);
        tokenParams.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(tokenParams, headers);

        Map tokenResponse = restTemplate.postForObject(tokenUri, tokenRequest, Map.class);
        String accessToken = (String) tokenResponse.get("access_token");

        HttpHeaders userHeaders = new HttpHeaders();
        userHeaders.setBearerAuth(accessToken);
        HttpEntity<Void> userRequest = new HttpEntity<>(userHeaders);

        ResponseEntity<Map> userResponse = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v2/userinfo",
                HttpMethod.GET,
                userRequest,
                Map.class
        );

        Map userData = userResponse.getBody();
        String email = (String) userData.get("email");
        String name = (String) userData.get("name");

        UserEntity user = UserEntity.builder()
                .userId(UUID.randomUUID())
                .email(email)
                .passWord("passWord")
                .userName(name)
                .role(UserRole.USER)
                .hasDelete(false)
                .build();

        saveUserEntityBean.exec(user);

        return user.getUserId();
    }

    public UUID login(LoginDto loginDto) {
        UserEntity user = getUserEntityBean.exec(loginDto.getEmail());
        if (user == null) {
            return null;
        }

        if (!user.getPassWord().equals(loginDto.getPassWord())) {
            return null;
        }

        return user.getUserId();
    }

    public UserRole getUserRole(UUID userId) {
        UserEntity user = getUserEntityBean.exec(userId);
        if (user == null) {
            return null;
        }

        return user.getRole();
    }
}
