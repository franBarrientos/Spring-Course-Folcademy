package com.saludo.api.domain.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ContactAddDTO {
    @NotNull
    private String name;

    @NotNull
    private Integer phone;
}
