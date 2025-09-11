package com.programacao.web.fatec.api_fatec.domain.cidade.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadePutDto {
    private Long id;
    private String nome;
    private String estado;
}
