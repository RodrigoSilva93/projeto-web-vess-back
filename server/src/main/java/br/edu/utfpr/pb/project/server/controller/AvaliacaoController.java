package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.AvaliacaoDto;
import br.edu.utfpr.pb.project.server.model.Avaliacao;
import br.edu.utfpr.pb.project.server.service.IAvaliacaoService;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assessments")
public class AvaliacaoController extends CrudController<Avaliacao, AvaliacaoDto, Long> {

    private final IAvaliacaoService service;
    private final ModelMapper modelMapper;

    public AvaliacaoController(IAvaliacaoService service, ModelMapper modelMapper) {
        super(Avaliacao.class, AvaliacaoDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<Avaliacao, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}
