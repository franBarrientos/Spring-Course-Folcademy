package com.saludo.api.domain.repositories;

import com.saludo.api.domain.entities.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    boolean existsByName(String name);


    Page<ContactEntity> findAllByNameContainingOrPhone(String name, Integer phone, Pageable pageable);


    boolean existsByPhone(Integer phone);
}
