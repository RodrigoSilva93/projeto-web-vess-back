package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.service.ICrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class CrudServiceImpl<T, ID extends Serializable> implements ICrudService<T, ID> {
    protected abstract JpaRepository<T, ID> getAvaliacaoRepository();

    @Override
    public List<T> findAll() {
        return getAvaliacaoRepository().findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getAvaliacaoRepository().findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getAvaliacaoRepository().findAll(pageable);
    }

    @Override
    public T save(T entity) {
        return getAvaliacaoRepository().save(entity);
    }

    @Override
    public T saveAndFlush(T entity) {
        return getAvaliacaoRepository().saveAndFlush(entity);
    }

    @Override
    public Iterable<T> save(Iterable<T> iterable) {
        return getAvaliacaoRepository().saveAll(iterable);
    }

    @Override
    public void flush() {
        getAvaliacaoRepository().flush();
    }

    @Override
    public T findOne(ID id) {
        return getAvaliacaoRepository().findById(id).orElse(null);
    }

    @Override
    public boolean exists(ID id) {
        return getAvaliacaoRepository().existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return getAvaliacaoRepository().count();
    }

    @Override
    public void delete(ID id) {
        getAvaliacaoRepository().deleteById(id);
    }

    @Override
    public void delete(Iterable<? extends T> iterable) {
        getAvaliacaoRepository().deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        getAvaliacaoRepository().deleteAll();
    }
}
