package com.saludo.api.controllers;

import com.saludo.api.domain.dtos.ContactAddDTO;
import com.saludo.api.domain.dtos.ResponseWithMessage;
import com.saludo.api.services.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    @GetMapping()
    public ResponseEntity<ResponseWithMessage> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "phone", required = false, defaultValue = "") Integer phone
    ) {
        return ResponseEntity.ok(
                ResponseWithMessage.builder()
                        .resource(this.contactService.getAll(PageRequest.of(page, size), name, phone))
                        .message("search was completed succesfully!")
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
    @PutMapping("/{id}")
    public ResponseEntity<ResponseWithMessage> updateById(@PathVariable(value = "id") Long id,
                                                          @RequestBody ContactAddDTO contactAddDTO) {
        return ResponseEntity.ok(
                ResponseWithMessage.builder()
                        .resource(this.contactService.updateById(id, contactAddDTO))
                        .message("Updated Successfully!")
                        .build());
    }


}
