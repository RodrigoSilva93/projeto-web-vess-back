package br.edu.utfpr.pb.project.server.service;

import br.edu.utfpr.pb.project.server.dto.AvaliacaoDto;
import br.edu.utfpr.pb.project.server.dto.CamadaDto;
import br.edu.utfpr.pb.project.server.dto.ScoreAmostraDto;
import br.edu.utfpr.pb.project.server.model.Avaliacao;

import java.util.List;

public interface IAvaliacaoService extends ICrudService<Avaliacao, Long> {
    AvaliacaoDto createAvaliacao(AvaliacaoDto dto, ScoreAmostraDto scoreAmostraDto);
}
