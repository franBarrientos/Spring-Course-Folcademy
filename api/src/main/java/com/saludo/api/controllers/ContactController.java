package com.saludo.api.controllers;

import com.saludo.api.domain.dtos.ContactAddDTO;
import com.saludo.api.domain.dtos.ContactReadDTO;
import com.saludo.api.services.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactReadDTO> add(@Valid @RequestBody ContactAddDTO contactAddDTO) {
        return ResponseEntity
                .ok(this.contactService.add(contactAddDTO));
    }

    /* Alternativa!!!         @PostMapping
    public ResponseEntity add(@Valid @RequestBody ContactAddDTO contactAddDTO){
        ContactReadDTO contactReadDTO = this.contactService.add(contactAddDTO);
        return ResponseEntity
                .ok(new HashMap<String ,  Object>()
                {{  put("id", contactReadDTO.getId());
                    put("mensaje", "añadido exitosamente");
                }});
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<ContactReadDTO> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity
                .ok(this.contactService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") Long id) {
        this.contactService.deleteById(id);
        return ResponseEntity
                .ok("Contact " + id + " Deleted Successfully");
    }


}
