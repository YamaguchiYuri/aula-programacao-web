package com.programacao.web.fatec.api_fatec.domain.cidade.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadePostDto {
    private String nome;
    private String estado;
}
