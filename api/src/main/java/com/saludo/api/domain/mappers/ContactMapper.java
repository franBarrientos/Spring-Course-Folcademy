package com.saludo.api.domain.mappers;

import com.saludo.api.domain.dtos.ContactAddDTO;
import com.saludo.api.domain.dtos.ContactReadDTO;
import com.saludo.api.domain.entities.AddressEntity;
import com.saludo.api.domain.entities.ContactEntity;
import org.springframework.stereotype.Component;


@Component
public class ContactMapper {
    public ContactReadDTO EntityToReadDTO(ContactEntity contactEntity){
        return ContactReadDTO.builder()
                .id(contactEntity.getId())
                .name(contactEntity.getName())
                .phone(contactEntity.getPhone())
                .addressStreet(contactEntity.getAddress().getStreet())
                .addressNumber(contactEntity.getAddress().getNumber())
                .build();
    }

    public ContactEntity addDTOToEntity(ContactAddDTO contactAddDTO){
        return ContactEntity.builder()
                .name(contactAddDTO.getName())
                .phone(contactAddDTO.getPhone())
                .build();
    }



}
