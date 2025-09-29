package com.example.Exercicio1.dto;

import java.util.ArrayList;
import java.util.List;

public class ContactResponse {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private List<AddressResponse> addresses = new ArrayList<>();

    public ContactResponse() {}
    
    public ContactResponse(Long id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public List<AddressResponse> getAddresses() { return addresses; }
    public void setAddresses(List<AddressResponse> addresses) { this.addresses = addresses; }
}