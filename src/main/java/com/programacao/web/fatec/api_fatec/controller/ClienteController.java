package com.programacao.web.fatec.api_fatec.controller;

import org.springframework.web.bind.annotation.RestController;

import com.programacao.web.fatec.api_fatec.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final List<Cliente> listaDeCliente = new ArrayList<>();
    
    public ClienteController(){
        listaDeCliente.add(new Cliente(1, "Coisinho"));
        Cliente cliente2 = new Cliente();
        cliente2.setId(2);
        cliente2.setNome("Shin");
        listaDeCliente.add(cliente2);

    }
    @GetMapping("/testeCliente1") //-> /api/clientes/testeCliente1
    public String testeCliente() {
        return "Teste Client";
    }

    @GetMapping("/testeCliente2/{nome}") //-> /api/clientes/testeCliente2/
    public String testeCliente2(@PathVariable String nome) {
        return nome;
    }
    @GetMapping("/listarClientes")
    public List<Cliente> listarClientes() {
        return listaDeCliente;
    }
    
    @PostMapping("")
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return cliente;
    }
    
}
