package com.kiosk.server.domain.menu.repository;

import com.kiosk.server.domain.menu.data.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, UUID> {
    MenuEntity findByMenuIdAndHasDelete(UUID menuId, boolean hasDelete);

    List<MenuEntity> findAllByHasDelete(boolean hasDelete);
}
