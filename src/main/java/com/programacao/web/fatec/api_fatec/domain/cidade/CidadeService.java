package com.programacao.web.fatec.api_fatec.domain.cidade;

import org.springframework.stereotype.Service;

@Service
public class CidadeService {
    @Autowired 
    private CidadeRepository cidadeRepository;

    public List<Cidade> listarCidades(){
        return cidadeRepository.findAll();
    }
}
