package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.PaisDto;
import br.edu.utfpr.pb.project.server.model.Pais;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import br.edu.utfpr.pb.project.server.service.IPaisService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pais")
public class PaisController extends CrudController<Pais, PaisDto, Long>{

    private final IPaisService service;
    private final ModelMapper modelMapper;

    public PaisController(IPaisService service, ModelMapper modelMapper) {
        super(Pais.class, PaisDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<Pais, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}
