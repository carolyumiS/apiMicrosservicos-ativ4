package com.example.Exercicio1.dto;

import com.example.Exercicio1.model.Address;
import com.example.Exercicio1.model.Contact;
import java.util.List;
import java.util.stream.Collectors;

public class ContactMapper {
    public static Contact toEntity(ContactRequest contactRequest) {
        Contact contact = new Contact();
        contact.setNome(contactRequest.getNome());
        contact.setEmail(contactRequest.getEmail());
        contact.setTelefone(contactRequest.getTelefone());
        
        if (contactRequest.getAddresses() != null) {
            List<Address> addresses = contactRequest.getAddresses().stream()
                .map(ContactMapper::toAddressEntity)
                .collect(Collectors.toList());
            contact.setAddresses(addresses);
        }
        
        return contact;
    }
    
    public static ContactResponse toResponse(Contact contact) {
        ContactResponse response = new ContactResponse();
        response.setId(contact.getId());
        response.setNome(contact.getNome());
        response.setEmail(contact.getEmail());
        response.setTelefone(contact.getTelefone());
        
        if (contact.getAddresses() != null) {
            List<AddressResponse> addressResponses = contact.getAddresses().stream()
                .map(ContactMapper::toAddressResponse)
                .collect(Collectors.toList());
            response.setAddresses(addressResponses);
        }
        
        return response;
    }
    
    public static Address toAddressEntity(AddressRequest addressRequest) {
        Address address = new Address();
        address.setRua(addressRequest.getRua());
        address.setCidade(addressRequest.getCidade());
        address.setEstado(addressRequest.getEstado());
        address.setCep(addressRequest.getCep());
        return address;
    }
    
    public static AddressResponse toAddressResponse(Address address) {
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setRua(address.getRua());
        response.setCidade(address.getCidade());
        response.setEstado(address.getEstado());
        response.setCep(address.getCep());
        response.setContactId(address.getContact().getId());
        return response;
    }
   
    public static void updateEntityFromRequest(Contact contact, ContactRequest contactRequest) {
        if (contactRequest.getNome() != null) {
            contact.setNome(contactRequest.getNome());
        }
        if (contactRequest.getEmail() != null) {
            contact.setEmail(contactRequest.getEmail());
        }
        if (contactRequest.getTelefone() != null) {
            contact.setTelefone(contactRequest.getTelefone());
        }
    }
}