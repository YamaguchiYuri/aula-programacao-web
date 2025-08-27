package com.programacao.web.fatec.api_fatec.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="clientes")
public class Cliente {    
    @Column(nullable = false, length = 60)
    private String endereco;

    @Column(nullable = false, length = 60)
    private String nome;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Cliente(Long id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Cliente() {
        
    }
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
