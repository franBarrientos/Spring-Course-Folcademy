package com.saludo.api.domain.repositories;

import com.saludo.api.domain.entities.AddressEntity;
import com.saludo.api.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
