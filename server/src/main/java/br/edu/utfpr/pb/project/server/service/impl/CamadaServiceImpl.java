package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.Camada;
import br.edu.utfpr.pb.project.server.repository.CamadaRepository;
import br.edu.utfpr.pb.project.server.service.ICamadaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CamadaServiceImpl extends CrudServiceImpl<Camada, Long> implements ICamadaService {
    private final CamadaRepository repository;

    public CamadaServiceImpl(CamadaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Camada, Long> getRepository() {
        return repository;
    }
}
