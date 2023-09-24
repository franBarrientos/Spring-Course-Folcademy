package com.saludo.api.domain.repositories;

import com.saludo.api.domain.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
