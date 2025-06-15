package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.model.Amostra;
import lombok.Data;

@Data
public class ScoreAmostraDto {

    private Long id;

    private Amostra amostra;

    private Double score;

    private String decisaoManejoScoreAmostra;

    private String resumoScoreAmostra;

    private String infoScoreAmostra;
}
