package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.model.Idioma;
import br.edu.utfpr.pb.project.server.repository.IdiomaRepository;
import br.edu.utfpr.pb.project.server.service.IIdiomaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class IdiomaServiceImpl extends CrudServiceImpl<Idioma, Long> implements IIdiomaService {

    private final IdiomaRepository repository;

    public IdiomaServiceImpl(IdiomaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Idioma, Long> getRepository() {
        return repository;
    }
}
