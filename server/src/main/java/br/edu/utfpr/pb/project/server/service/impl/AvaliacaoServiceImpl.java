package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.Avaliacao;
import br.edu.utfpr.pb.project.server.repository.AvaliacaoRepository;
import br.edu.utfpr.pb.project.server.service.IAvaliacaoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoServiceImpl extends CrudServiceImpl<Avaliacao, Long> implements IAvaliacaoService {

    private final AvaliacaoRepository repository;

    public AvaliacaoServiceImpl(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Avaliacao, Long> getRepository() {
        return repository;
    }
}
