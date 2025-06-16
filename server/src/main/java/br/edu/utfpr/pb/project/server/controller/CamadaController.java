package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.CamadaDto;
import br.edu.utfpr.pb.project.server.model.Camada;
import br.edu.utfpr.pb.project.server.service.ICamadaService;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("layers")
public class CamadaController extends CrudController<Camada, CamadaDto, Long> {

    private final ICamadaService service;
    private final ModelMapper modelMapper;

    public CamadaController(ICamadaService service, ModelMapper modelMapper) {
        super(Camada.class, CamadaDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<Camada, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}
