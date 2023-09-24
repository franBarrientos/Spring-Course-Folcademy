package com.saludo.api.services;

import com.saludo.api.domain.dtos.ContactAddDTO;
import com.saludo.api.domain.dtos.ContactReadDTO;
import com.saludo.api.domain.mappers.ContactMapper;
import com.saludo.api.domain.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactReadDTO add(ContactAddDTO contactAddDTO) {
        return this.contactMapper
                .EntityToReadDTO(
                        this.contactRepository
                        .save(this.contactMapper
                                .addDTOToEntity(contactAddDTO)));

    }

    public ContactReadDTO getById(Long id) {
        return this.contactMapper
                .EntityToReadDTO(this.contactRepository
                        .findById(id)
                        .orElseThrow(()->new NoSuchElementException("Contact "+id+" Not Found!")));
    }

    public void deleteById(Long id) {
         this.contactRepository.deleteById(id);
    }
}
