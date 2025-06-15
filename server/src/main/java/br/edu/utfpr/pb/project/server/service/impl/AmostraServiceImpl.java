package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.Amostra;
import br.edu.utfpr.pb.project.server.repository.AmostraRepository;
import br.edu.utfpr.pb.project.server.service.IAmostraService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AmostraServiceImpl extends CrudServiceImpl<Amostra, Long> implements IAmostraService {

    private final AmostraRepository repository;

    public AmostraServiceImpl(AmostraRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Amostra, Long> getAvaliacaoRepository() {
        return repository;
    }
}
