package com.programacao.web.fatec.api_fatec.domain.cliente.dto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.programacao.web.fatec.api_fatec.domain.cidade.CidadeRepository;
import com.programacao.web.fatec.api_fatec.domain.cliente.ClienteRepository;
import com.programacao.web.fatec.api_fatec.entities.Cidade;
import com.programacao.web.fatec.api_fatec.entities.Cliente;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
    public List<Cliente> buscaPorIdOuNomeGenerico(@PathVariable String search) {
        Long id = null;
        try {
            id = Long.parseLong(search);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }

        return clienteRepository.buscarPorIdOuNome(id, search);
    }


        public List<Cliente> buscaPorIdOuNome(BuscaPorIdOuNomeDto dto) {
        return clienteRepository.buscarPorIdOuNome(dto.getId(), dto.getNome());
    }


        public List<Cliente> buscarPorTexto(String texto) {
        // Tenta converter o texto para Long para buscar por ID
        Long idLong = null;
        try {
            idLong = Long.parseLong(texto);
        } catch (NumberFormatException e) {
            // Se não for possível converter para Long, deixa idLong como null
            System.out.println("Texto não pode ser convertido para Long: " + texto);
        }

        // Chama o método do repositório com o texto e o possível ID
        return clienteRepository.buscarPorIdOuNomeComCidade(texto, idLong);
    }

        public Cliente createCliente(Cliente cliente) {
        cliente.setId(null);

        // Verifica se o cliente tem uma cidade associada
        if (cliente.getCidade() != null && cliente.getCidade().getId() != null) {
            // Busca a cidade pelo ID
            Optional<Cidade> cidadeOpt = cidadeRepository.findById(cliente.getCidade().getId());

            // Se a cidade existir, associa ao cliente
            if (cidadeOpt.isPresent()) {
                cliente.setCidade(cidadeOpt.get());
            } else {
                // Se a cidade não existir, remove a associação
                cliente.setCidade(null);
            }
        }

        Cliente clienteCreated = clienteRepository.save(cliente);
        return clienteCreated;
    }


    public String deletarCliente(Long id) {
        //1º exemplo 
        //clienteRepository.deleteById(id);

        //2 exemplo
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);
        if (clienteEncontrado.isPresent()) {
            clienteRepository.deleteById(id);
            return "Cliente Deletado";
        }

        return "NÃO ENCONTRADO ID:"+id;
    }

    public String alterarCliente(Long id,Cliente entity) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);
        if (!clienteEncontrado.isPresent()) {
            return String.format("NÃO ENCONTRADO ID: %s", id);
        }

        entity.setId(id);

        // Verifica se o cliente tem uma cidade associada
        if (entity.getCidade() != null && entity.getCidade().getId() != null) {
            // Busca a cidade pelo ID
            Optional<Cidade> cidadeOpt = cidadeRepository.findById(entity.getCidade().getId());

            // Se a cidade existir, associa ao cliente
            if (cidadeOpt.isPresent()) {
                entity.setCidade(cidadeOpt.get());
            } else {
                // Se a cidade não existir, remove a associação
                entity.setCidade(null);
            }
        }

        clienteRepository.save(entity);
        return "Cliente Atualizado com sucesso";
    }
}
