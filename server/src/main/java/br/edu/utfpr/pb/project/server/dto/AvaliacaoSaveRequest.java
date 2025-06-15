package br.edu.utfpr.pb.project.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoSaveRequest {
    private AvaliacaoDto avaliacao;
    private List<CamadaDto> camadas;
    private ScoreAmostraDto scoreAmostra;
}
