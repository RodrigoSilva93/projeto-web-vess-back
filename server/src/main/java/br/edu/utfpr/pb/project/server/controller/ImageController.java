package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.minio.service.MinioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final MinioService minioService;
    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    public ImageController(MinioService minioService) {
        this.minioService = minioService;
    }

    @GetMapping("/{filename}")
    public ResponseEntity<InputStreamResource> getPublicImage(@PathVariable String filename) {
        log.info("Recebida requisição para imagem pública: {}", filename);
        try {
            InputStream imagemStream = minioService.downloadObject(null, filename); // Usa o bucket padrão

            if (imagemStream == null) {
                log.error("Imagem pública '{}' não encontrada no Minio.", filename);
                return ResponseEntity.notFound().build();
            }
            MediaType contentType = filename.toLowerCase().endsWith(".png") ? MediaType.IMAGE_PNG :
                    filename.toLowerCase().endsWith(".svg") ? MediaType.valueOf("image/svg+xml") :
                            MediaType.IMAGE_JPEG;
            return ResponseEntity.ok()
                    .contentType(contentType)
                    .body(new InputStreamResource(imagemStream));
        } catch (Exception e) {
            log.error("Erro ao buscar imagem pública '{}': {}", filename, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}