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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

        ContactEntity contact = this.contactRepository
                .findById(id)
                .orElseThrow(()-> new NonExistentContactException("The contact doesn't exist yet"));

        this.contactRepository.delete(contact);

        return this.contactMapper
                .EntityToReadDTO(contact);
    }

        public Page<ContactReadDTO> getAll(Pageable pageable, String name, Integer phone) {
        return this.contactRepository.findAllByNameContainingOrPhone(name, phone, pageable)
                .map(this.contactMapper::EntityToReadDTO);
    }


    public ContactReadDTO updateById(Long id, ContactAddDTO contactAddDTO) {
        ContactEntity contactEntity = this.contactRepository.findById(id)
                .orElseThrow(()->new NonExistentContactException("The contact doesn't exist yet"));

        if(contactAddDTO.getName() != null && !contactAddDTO.getName().isBlank()){
            contactEntity.setName(contactAddDTO.getName());
        }
        if(contactAddDTO.getPhone() != null){
            contactEntity.setPhone(contactAddDTO.getPhone());
        }
        if (contactAddDTO.getAddress()!= null
                && contactAddDTO.getAddress().getStreet() != null
                && !contactAddDTO.getAddress().getStreet().isBlank()){
            contactEntity.getAddress().setStreet(contactAddDTO.getAddress().getStreet());
        }
        if (contactAddDTO.getAddress()!= null
                && contactAddDTO.getAddress().getNumber() != null
                && !contactAddDTO.getAddress().getNumber().isBlank()){
            contactEntity.getAddress().setNumber(contactAddDTO.getAddress().getNumber());
        }

        return this.contactMapper.EntityToReadDTO(this.contactRepository.save(contactEntity));


    }
}
