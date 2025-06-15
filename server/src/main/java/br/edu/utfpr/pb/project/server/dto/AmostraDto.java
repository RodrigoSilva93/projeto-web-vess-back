package br.edu.utfpr.pb.project.server.dto;

import lombok.Data;

@Data
public class AmostraDto {
    private Long id;

    private String nomeAmostra;

    private Integer qtdCamadasAmostra;

    private String contentImageAmostra;

    private String typeImageAmostra;

    private String outrasInformacoesAmostra;

    private Double scoreAmostra; //retornar o score da amostra
}
