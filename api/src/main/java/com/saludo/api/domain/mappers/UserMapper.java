package com.saludo.api.domain.mappers;

import com.saludo.api.domain.dtos.AuthCredentialsDTO;
import com.saludo.api.domain.dtos.UserReadDTO;
import com.saludo.api.domain.entities.UserEntity;

public class UserMapper {
    UserEntity toEntity(AuthCredentialsDTO authCredentialsDTO) {
        return UserEntity.builder()
                .username(authCredentialsDTO.getUsername())
                .password(authCredentialsDTO.getPassword())
                .build();
    }

    UserReadDTO toUserReadDTO(UserEntity userEntity) {
        return UserReadDTO.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .build();
    }
}
