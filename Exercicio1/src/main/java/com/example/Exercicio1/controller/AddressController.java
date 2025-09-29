package com.example.Exercicio1.controller;

import com.example.Exercicio1.dto.AddressRequest;
import com.example.Exercicio1.dto.AddressResponse;
import com.example.Exercicio1.dto.ContactMapper;
import com.example.Exercicio1.model.Address;
import com.example.Exercicio1.model.Contact;
import com.example.Exercicio1.repository.ContactRepository;
import com.example.Exercicio1.repository.AddressRepository;
import com.example.Exercicio1.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;
    
    @GetMapping("/contacts/{contactId}")
    public List<AddressResponse> getAddressesByContact(@PathVariable Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + contactId));
        
        return contact.getAddresses().stream()
                .map(ContactMapper::toAddressResponse)
                .collect(Collectors.toList());
    }
    
    @PostMapping("/contacts/{contactId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse createAddress(@PathVariable Long contactId, @RequestBody @Valid AddressRequest addressRequest) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + contactId));
        
        Address address = ContactMapper.toAddressEntity(addressRequest);
        address.setContact(contact);
        Address savedAddress = addressRepository.save(address);
        return ContactMapper.toAddressResponse(savedAddress);
    }
}