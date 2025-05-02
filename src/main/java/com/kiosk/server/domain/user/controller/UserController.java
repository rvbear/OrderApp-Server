package com.kiosk.server.domain.user.controller;

import com.kiosk.server.domain.user.data.UserRole;
import com.kiosk.server.domain.user.data.dto.in.DeleteUserDto;
import com.kiosk.server.domain.user.data.dto.in.LoginDto;
import com.kiosk.server.domain.user.data.dto.in.OAuthLoginDto;
import com.kiosk.server.domain.user.data.dto.in.UpdateUserDto;
import com.kiosk.server.domain.user.data.dto.out.GetUserDto;
import com.kiosk.server.domain.user.service.UserService;
import com.kiosk.server.global.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getUser(@RequestParam("userId") UUID userId) {
        GetUserDto getUserDto = userService.getUser(userId);
        boolean success = getUserDto != null;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "회원 조회 성공" : "회원 조회 실패");
        response.put("userInfo", getUserDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getUserAll() {
        List<GetUserDto> userDtoList = userService.getUserAll();
        boolean success = !userDtoList.isEmpty();

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "전체 회원 조회 성공" : "전체 회원 조회 실패");
        response.put("userList", userDtoList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        UUID userId = userService.updateUser(updateUserDto);
        boolean success = userId != null;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "회원 정보 수정 성공" : "회원 정보 수정 실패");
        response.put("userId", success ? userId : "00000000-0000-0000-0000-000000000000");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody DeleteUserDto deleteUserDto) {
        boolean success = userService.deleteUser(deleteUserDto);

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "회원 삭제 성공" : "회원 삭제 실패");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/oauth/google")
    public ResponseEntity<Map<String, Object>> oauthGoogleLogin(@RequestBody OAuthLoginDto oAuthLoginDto) {
        UUID userId = userService.oauthGoogleLogin(oAuthLoginDto);
        boolean success = userId != null;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "로그인 성공" : "로그인 실패");
        response.put("userId", success ? userId : "00000000-0000-0000-0000-000000000000");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto loginDto) {
        UUID userId = userService.login(loginDto);
        boolean success = userId != null;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "로그인 성공" : "로그인 실패");
        response.put("userId", success ? userId : "00000000-0000-0000-0000-000000000000");

        if (success) {
            UserRole role = userService.getUserRole(userId);
            String token = jwtUtil.generateToken(userId, role);

            return ResponseEntity.status(HttpStatus.OK).header("Authorization", "Bearer " + token).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
