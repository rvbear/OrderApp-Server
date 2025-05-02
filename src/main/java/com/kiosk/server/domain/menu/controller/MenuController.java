package com.kiosk.server.domain.menu.controller;

import com.kiosk.server.domain.menu.data.dto.in.CreateMenuDto;
import com.kiosk.server.domain.menu.data.dto.in.DeleteMenuDto;
import com.kiosk.server.domain.menu.data.dto.in.UpdateMenuDto;
import com.kiosk.server.domain.menu.data.dto.out.GetMenuDto;
import com.kiosk.server.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/menu")
@RestController
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createMenu(@RequestBody CreateMenuDto createMenuDto) {
        UUID menuId = menuService.createMenu(createMenuDto);
        boolean success = menuId != null;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "메뉴 생성 완료" : "메뉴 생성 실패");
        response.put("menuId", success ? menuId : "00000000-0000-0000-0000-000000000000");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateMenu(@RequestBody UpdateMenuDto updateMenuDto) {
        UUID menuId = menuService.updateMenu(updateMenuDto);

        boolean success = menuId != null;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "메뉴 수정 완료" : "메뉴 수정 실패");
        response.put("menuId", success ? menuId : "00000000-0000-0000-0000-000000000000");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteMenu(@RequestBody DeleteMenuDto deleteMenuDto) {
        boolean success = menuService.deleteMenu(deleteMenuDto);

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "메뉴 삭제 완료" : "메뉴 삭제 실패");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getMenu(@RequestParam("menuId") UUID menuId) {
        GetMenuDto getMenuDto = menuService.getMenu(menuId);
        boolean success = getMenuDto != null;

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "메뉴 조회 완료" : "메뉴 조회 실패");
        response.put("menuInfo", success ? getMenuDto : null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getMenuAll() {
        List<GetMenuDto> menuList = menuService.getMenuAll();
        boolean success = !menuList.isEmpty();

        Map<String, Object> response = new HashMap<>();
        response.put("hasSuccess", success);
        response.put("message", success ? "메뉴 전체 조회 완료" : "메뉴 전체 조회 실패");
        response.put("menuList", menuList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
