package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.AmostraDto;
import br.edu.utfpr.pb.project.server.model.Amostra;
import br.edu.utfpr.pb.project.server.service.IAmostraService;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("samples")
public class AmostraController extends CrudController<Amostra, AmostraDto, Long> {

    private final IAmostraService service;
    private final ModelMapper modelMapper;

    public AmostraController(IAmostraService service, ModelMapper modelMapper) {
        super(Amostra.class, AmostraDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<Amostra, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}
