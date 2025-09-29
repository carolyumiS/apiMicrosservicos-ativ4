package com.example.Exercicio1.controller;

import com.example.Exercicio1.dto.ContactRequest;
import com.example.Exercicio1.dto.ContactResponse;
import com.example.Exercicio1.dto.ContactMapper;
import com.example.Exercicio1.model.Contact;
import com.example.Exercicio1.repository.ContactRepository;
import com.example.Exercicio1.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public List<ContactResponse> getAllContacts() {
        return contactRepository.findAll().stream()
                .map(ContactMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ContactResponse getContactById(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));
        return ContactMapper.toResponse(contact);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponse createContact(@Valid @RequestBody ContactRequest contactRequest) {
        Contact contact = ContactMapper.toEntity(contactRequest);
        Contact savedContact = contactRepository.save(contact);
        return ContactMapper.toResponse(savedContact);
    }

    @PutMapping("/{id}")
    public ContactResponse updateContact(@PathVariable Long id, @Valid @RequestBody ContactRequest contactRequest) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));

        ContactMapper.updateEntityFromRequest(existingContact, contactRequest);
        Contact updatedContact = contactRepository.save(existingContact);
        return ContactMapper.toResponse(updatedContact);
    }

    @PatchMapping("/{id}")
    public ContactResponse updateContactPartial(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "nome":
                    contact.setNome(value);
                    break;
                case "telefone":
                    contact.setTelefone(value);
                    break;
                case "email":
                    contact.setEmail(value);
                    break;
            }
        });

        Contact updatedContact = contactRepository.save(contact);
        return ContactMapper.toResponse(updatedContact);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }

    @GetMapping("/search")
    public List<ContactResponse> searchContactsByName(@RequestParam String name) {
        return contactRepository.findByNomeContainingIgnoreCase(name).stream()
                .map(ContactMapper::toResponse)
                .collect(Collectors.toList());
    }
}