package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.AmostraDto;
import br.edu.utfpr.pb.project.server.minio.service.MinioService;
import br.edu.utfpr.pb.project.server.model.Amostra;
import br.edu.utfpr.pb.project.server.repository.AmostraRepository;
import br.edu.utfpr.pb.project.server.service.IAmostraService;
import br.edu.utfpr.pb.project.server.service.ICrudService;
import org.springframework.core.io.InputStreamResource;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.Optional;

@RestController
@RequestMapping("samples")
public class AmostraController extends CrudController<Amostra, AmostraDto, Long> {

    private final IAmostraService service;
    private final AmostraRepository amostraRepository;
    private final MinioService minioService;
    private final ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(AmostraController.class);


    public AmostraController(IAmostraService service,
                             AmostraRepository amostraRepository, // Adicionado ao construtor
                             MinioService minioService,
                             ModelMapper modelMapper) {
        super(Amostra.class, AmostraDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
        this.amostraRepository = amostraRepository;
        this.minioService = minioService;
    }

    @Override
    protected ICrudService<Amostra, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    @GetMapping("/{id}/imagem")
    public ResponseEntity<InputStreamResource> getImagemAmostra(@PathVariable Long id) {
        log.error("Recebida requisição para imagem da amostra ID: {}", id);

        Optional<Amostra> amostraOpt = amostraRepository.findById(id);

        if (amostraOpt.isEmpty()) {
            log.error("Amostra com ID: {} não encontrada.", id);
            return ResponseEntity.notFound().build();
        }

        Amostra amostra = amostraOpt.get();
        String nomeArquivo = amostra.getImagemNomeArquivo();

        if (!StringUtils.hasText(nomeArquivo)) {
            log.error("Amostra ID: {} não possui nome de arquivo de imagem.", id);
            return ResponseEntity.notFound().build();
        }

        try {
            log.info("Buscando arquivo '{}' no Minio para a amostra ID: {}", nomeArquivo, id);
            InputStream imagemStream = minioService.downloadObject(null, nomeArquivo);

            if (imagemStream == null) {
                log.error("Arquivo '{}' não encontrado no Minio para a amostra ID: {}.", nomeArquivo, id);
                return ResponseEntity.notFound().build();
            }

            log.info("Arquivo '{}' encontrado. Enviando para o cliente.", nomeArquivo);
            MediaType contentType = nomeArquivo.toLowerCase().endsWith(".png") ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;

            return ResponseEntity.ok()
                    .contentType(contentType)
                    .body(new InputStreamResource(imagemStream));

        } catch (Exception e) {
            // ESTE BLOCO VAI CAPTURAR O ERRO E REGISTRÁ-LO DETALHADAMENTE
            log.error("ERRO CRÍTICO ao tentar buscar o arquivo '{}' no Minio: {}", nomeArquivo, e.getMessage(), e);

            // Retorna uma resposta 500 para o frontend, como esperado.
            return ResponseEntity.internalServerError().build();
        }
    }
}
