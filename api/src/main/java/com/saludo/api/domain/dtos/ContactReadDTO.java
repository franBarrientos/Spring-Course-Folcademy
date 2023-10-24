package com.saludo.api.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactReadDTO {

    private Long id;

    private String name;

    private Integer phone;

    private String addressStreet;

    private String addressNumber;
}
