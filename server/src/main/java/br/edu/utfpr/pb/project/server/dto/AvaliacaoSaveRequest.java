package br.edu.utfpr.pb.project.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoSaveRequest {
    private AvaliacaoDto avaliacao;
    private ScoreAmostraDto scoreAmostra;
}
