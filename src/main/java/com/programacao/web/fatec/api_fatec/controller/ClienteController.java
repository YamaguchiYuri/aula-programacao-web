package com.programacao.web.fatec.api_fatec.controller;

import org.springframework.web.bind.annotation.RestController;

import com.programacao.web.fatec.api_fatec.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final List<Cliente> listaDeCliente = new ArrayList<>();
    
    public ClienteController(){
        listaDeCliente.add(new Cliente(1L, "Coisinho","rua"));
        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
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
    public Cliente createCliente(@RequestBody/*Constroi com o corpo (json) */ Cliente cliente) {

        listaDeCliente.add(cliente);
        return cliente;
    }
    @DeleteMapping("/{id}")
    public String deletarCliente(@PathVariable Long id){

        for (Cliente cliente : listaDeCliente){
            if(cliente.getId()==id){
                listaDeCliente.remove(cliente);
                return "Removido";
            }
        }
        return "Nao encontrado ID: "+ id;
    }

    @PutMapping("/{id}")
    public String atualizarCliente(@PathVariable Long id, @RequestBody Cliente entity) {
        for (Cliente cliente : listaDeCliente){
            if(cliente.getId()==id){
                cliente.setId(id);
                cliente.setNome(entity.getNome());
                return "Cliente atualizado";
            }
        }
        
        return "ID NAO ENCONTRADO: " + id;
    }
}
