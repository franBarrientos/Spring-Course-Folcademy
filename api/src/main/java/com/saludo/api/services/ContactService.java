package com.saludo.api.services;

import com.saludo.api.domain.dtos.ContactAddDTO;
import com.saludo.api.domain.dtos.ContactReadDTO;
import com.saludo.api.domain.entities.AddressEntity;
import com.saludo.api.domain.entities.ContactEntity;
import com.saludo.api.domain.mappers.ContactMapper;
import com.saludo.api.domain.repositories.AddressRepository;
import com.saludo.api.domain.repositories.ContactRepository;
import com.saludo.api.exceptions.exceptionKinds.ContactAlreadyExistException;
import com.saludo.api.exceptions.exceptionKinds.NonExistentContactException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final AddressRepository addressRepository;

    public ContactReadDTO add(ContactAddDTO contactAddDTO) {

        if (this.contactRepository.existsByName(contactAddDTO.getName()) ||
                this.contactRepository.existsByPhone(contactAddDTO.getPhone())) {
            throw new ContactAlreadyExistException("The name or phone is already exists");
        }
        AddressEntity address = AddressEntity.builder()
                .street(contactAddDTO.getAddress().getStreet())
                .number(contactAddDTO.getAddress().getNumber())
                .build();

        ContactEntity contact = this.contactMapper
                .addDTOToEntity(contactAddDTO);

        contact.setAddress(this.addressRepository.save(address));

        return this.contactMapper
                .EntityToReadDTO(
                        this.contactRepository
                                .save(contact));

    }

    public ContactReadDTO getById(Long id) {

        if (!this.contactRepository.existsById(id)) {
            throw new NonExistentContactException("The contact doesn't exist yet");
        };

        return this.contactMapper
                .EntityToReadDTO(this.contactRepository
                        .findById(id)
                        .get());
    }

    public ContactReadDTO deleteById(Long id) {

        Optional<ContactEntity> optionalContact = this.contactRepository
                .findById(id);

        if (optionalContact.isEmpty()) {
            throw new NonExistentContactException("The contact doesn't exist yet");
        };

        return this.contactMapper
                .EntityToReadDTO(optionalContact.get());
    }
}
