package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.IdiomaDto;
import br.edu.utfpr.pb.project.server.model.Idioma;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import br.edu.utfpr.pb.project.server.service.IIdiomaService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("language")
public class IdiomaController extends CrudController<Idioma, IdiomaDto, Long> {

    private final IIdiomaService service;
    private final ModelMapper modelMapper;

    public IdiomaController(IIdiomaService service, ModelMapper modelMapper) {
        super(Idioma.class, IdiomaDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<Idioma, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

}
