package com.saludo.api.domain.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactAddDTO {
    @NotNull
    private String name;

    @NotNull
    private Integer phone;

    @NotNull
    private AddressAddDTO address;

}
