package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.Pais;
import br.edu.utfpr.pb.project.server.repository.PaisRepository;
import br.edu.utfpr.pb.project.server.service.IPaisService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PaisServiceImpl extends CrudServiceImpl<Pais, Long> implements IPaisService {
    private final PaisRepository repository;

    public PaisServiceImpl(PaisRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Pais, Long> getRepository() {
        return repository;
    }
}
