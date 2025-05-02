package com.kiosk.server.domain.user.repository;

import com.kiosk.server.domain.user.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUserIdAndHasDelete(UUID userId, boolean hasDelete);

    UserEntity findByEmailAndHasDelete(String email, boolean hasDelete);

    List<UserEntity> findAllByHasDelete(boolean hasDelete);
}
