package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.model.Camada;
import lombok.Data;

import java.util.List;

@Data
public class AmostraDto {
    private Long id;

    private String nomeAmostra;

    private Integer qtdCamadasAmostra;

    private String contentImageAmostra;

    private String typeImageAmostra;

    private String outrasInformacoesAmostra;

    private ScoreAmostraDto scoreAmostra;

    private List<Camada> camadas;
}
