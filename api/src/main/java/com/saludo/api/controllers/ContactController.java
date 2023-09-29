package com.saludo.api.controllers;

import com.saludo.api.domain.dtos.ContactAddDTO;
import com.saludo.api.domain.dtos.ContactReadDTO;
import com.saludo.api.domain.dtos.ResponseWithMessage;
import com.saludo.api.services.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ResponseWithMessage> add(@Valid @RequestBody ContactAddDTO contactAddDTO) {
        return ResponseEntity.ok(
                ResponseWithMessage.builder()
                        .resource(this.contactService.add(contactAddDTO))
                        .message("Added succesfully!")
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithMessage> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(
                ResponseWithMessage.builder()
                        .resource(this.contactService.getById(id))
                        .message("found succesfully!")
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWithMessage> deleteById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(
                ResponseWithMessage.builder()
                        .resource(this.contactService.deleteById(id))
                        .message("Deleted Successfully!")
                        .build());
    }


}
