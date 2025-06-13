package br.edu.utfpr.pb.project.server.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AmostraDto {
    private Long id;

    private String nomeAmostra;

    private Integer qtdCamadasAmostra;

    private String contentImageAmostra;

    private String typeImageAmostra;

    private String outrasInformacoesAmostra;
}
