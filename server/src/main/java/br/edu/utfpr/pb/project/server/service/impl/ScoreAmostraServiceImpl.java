package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.ScoreAmostra;
import br.edu.utfpr.pb.project.server.repository.ScoreAmostraRepository;
import br.edu.utfpr.pb.project.server.service.IScoreAmostraService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ScoreAmostraServiceImpl extends CrudServiceImpl<ScoreAmostra, Long> implements IScoreAmostraService {

    private final ScoreAmostraRepository repository;

    public ScoreAmostraServiceImpl(ScoreAmostraRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<ScoreAmostra, Long> getAvaliacaoRepository() {
        return repository;
    }
}
