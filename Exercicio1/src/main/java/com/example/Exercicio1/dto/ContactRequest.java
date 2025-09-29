package com.example.Exercicio1.dto;

import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRequest {
    
    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;
    
    @NotBlank(message = "O email não pode estar vazio")
    @Email(message = "Formato de email inválido")
    private String email;
    
    @NotBlank(message = "O telefone não pode estar vazio")
    @Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 caracteres")
    @Pattern(regexp = "\\d+", message = "O telefone deve conter apenas números")
    private String telefone;
    
    private List<AddressRequest> addresses = new ArrayList<>();

    public ContactRequest() {}
    
    public ContactRequest(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public List<AddressRequest> getAddresses() { return addresses; }
    public void setAddresses(List<AddressRequest> addresses) { this.addresses = addresses; }
}