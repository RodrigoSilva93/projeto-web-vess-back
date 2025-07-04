package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CrudController<T, D, ID extends Serializable> {

    protected abstract ICrudService<T, ID> getService();
    protected abstract ModelMapper getModelMapper();

    private final Class<T> typeClass;
    private final Class<D> typeDtoClass;

    public CrudController(Class<T> typeClass, Class<D> typeDtoClass) {
        this.typeClass = typeClass;
        this.typeDtoClass = typeDtoClass;
    }

    private D convertToDto(T entity){
        return getModelMapper().map(entity, this.typeDtoClass);
    }
    private T convertToEntity(D dto){
        return getModelMapper().map(dto, this.typeClass);
    }

    @GetMapping
    public ResponseEntity<List<D>> findAll() {
        return ResponseEntity.ok(
                getService().findAll()
                        .stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<D> findOne(@PathVariable ID id) {
        T entity = getService().findOne(id);
        if ( entity != null) return ResponseEntity.ok(convertToDto(entity));
        else return ResponseEntity.noContent().build();
    }
}
