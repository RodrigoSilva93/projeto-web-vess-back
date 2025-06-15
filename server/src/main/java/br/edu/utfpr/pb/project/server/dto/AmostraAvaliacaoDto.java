package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.model.Amostra;
import br.edu.utfpr.pb.project.server.model.Avaliacao;
import lombok.Data;

@Data
public class AmostraAvaliacaoDto {
    private Long id;

    private Amostra amostra;

    private Avaliacao avaliacao;
}
