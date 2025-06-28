package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.AvaliacaoDto;
import br.edu.utfpr.pb.project.server.model.Avaliacao;
import br.edu.utfpr.pb.project.server.repository.AvaliacaoRepository;
import br.edu.utfpr.pb.project.server.service.IAvaliacaoService;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assessments")
public class AvaliacaoController extends CrudController<Avaliacao, AvaliacaoDto, Long> {

    private final AvaliacaoRepository avaliacaoRepository;
    private final IAvaliacaoService service;
    private final ModelMapper modelMapper;

    public AvaliacaoController(IAvaliacaoService service, ModelMapper modelMapper,
                               AvaliacaoRepository avaliacaoRepository) {
        super(Avaliacao.class, AvaliacaoDto.class);
        this.avaliacaoRepository = avaliacaoRepository;
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

    @PostMapping
    @RequestMapping("save")
    public ResponseEntity<?> save(@RequestBody AvaliacaoDto request, @RequestParam String email) {
        AvaliacaoDto result = service.createAvaliacao(request, email);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    @RequestMapping("history")
    public ResponseEntity<?> history(@RequestParam String email, @RequestParam String nome) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByUsuarioEmailAndUsuarioNomeContaining(email, nome);

        if (avaliacoes.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma avaliação encontrada para " + nome);


        return ResponseEntity.ok(avaliacoes);
    }
}
