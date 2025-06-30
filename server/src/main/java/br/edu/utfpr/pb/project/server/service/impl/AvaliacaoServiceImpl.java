package br.edu.utfpr.pb.project.server.service.impl;

import br.edu.utfpr.pb.project.server.dto.*;
import br.edu.utfpr.pb.project.server.minio.payload.FileResponse;
import br.edu.utfpr.pb.project.server.minio.service.MinioService;
import br.edu.utfpr.pb.project.server.model.*;
import br.edu.utfpr.pb.project.server.repository.*;
import br.edu.utfpr.pb.project.server.service.IAvaliacaoService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.UUID;

@Service
@Transactional
public class AvaliacaoServiceImpl extends CrudServiceImpl<Avaliacao, Long> implements IAvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AmostraRepository amostraRepository;
    private final CamadaRepository camadaRepository;
    private final ScoreAmostraRepository scoreAmostraRepository;
    private final MinioService minioService;

    public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository, UsuarioRepository usuarioRepository,
                                AmostraRepository amostraRepository, CamadaRepository camadaRepository,
                                ScoreAmostraRepository scoreAmostraRepository, MinioService minioService) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.amostraRepository = amostraRepository;
        this.camadaRepository = camadaRepository;
        this.scoreAmostraRepository = scoreAmostraRepository;
        this.minioService = minioService;
    }

    @Override
    protected JpaRepository<Avaliacao, Long> getAvaliacaoRepository() {
        return avaliacaoRepository;
    }

    @Override
    public AvaliacaoDto createAvaliacao(AvaliacaoDto dto, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        Avaliacao avaliacao = getAvaliacao(dto, usuario);
        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);

        for (AmostraDto amostraDto : dto.getAmostras()) {
            Amostra amostra = new Amostra();
            amostra.setNomeAmostra(amostraDto.getNomeAmostra());
            amostra.setQtdCamadasAmostra(amostraDto.getQtdCamadasAmostra());
            amostra.setOutrasInformacoesAmostra(amostraDto.getOutrasInformacoesAmostra());

            // --- INÍCIO DA LÓGICA DE UPLOAD PARA O MINIO ---

            // Verifica se o DTO contém a string da imagem
            if (StringUtils.hasText(amostraDto.getContentImageAmostra())) {

                // 1. Decodificar a string Base64 para bytes
                String base64Content = amostraDto.getContentImageAmostra().substring(amostraDto.getContentImageAmostra().indexOf(",") + 1);
                byte[] imageBytes = Base64.getDecoder().decode(base64Content);

                String contentType = amostraDto.getTypeImageAmostra();

                // 2. Gerar um nome de arquivo único
                String extensao = "." + contentType.split("/")[1];
                String nomeArquivo = UUID.randomUUID().toString().replaceAll("-", "") + extensao;

                // 3. Chamar o serviço do Minio para fazer o upload
                FileResponse minioResponse = minioService.putObject(imageBytes, nomeArquivo, contentType, null); // Usa o bucket padrão

                // 4. Salvar o nome do arquivo retornado pelo Minio na entidade
                amostra.setImagemNomeArquivo(minioResponse.getFilename());
            }

            // --- FIM DA LÓGICA DE UPLOAD PARA O MINIO ---

            Amostra amostraSalva = amostraRepository.save(amostra);

            ScoreAmostra scoreAmostra = new ScoreAmostra();
            scoreAmostra.setAmostra(amostraSalva);
            scoreAmostra.setScore(amostraDto.getScoreAmostra().getScore());
            scoreAmostra.setDecisaoManejoScoreAmostra(amostraDto.getScoreAmostra().getDecisaoManejoScoreAmostra());
            scoreAmostra.setResumoScoreAmostra(amostraDto.getScoreAmostra().getResumoScoreAmostra());
            scoreAmostra.setInfoScoreAmostra(amostraDto.getScoreAmostra().getInfoScoreAmostra());
            scoreAmostraRepository.save(scoreAmostra);

            for (Camada camadaAmostra : amostraDto.getCamadas()) {
                camadaAmostra.setAmostra(amostraSalva);
                camadaRepository.save(camadaAmostra);
            }


            AmostraAvaliacao amostraAvaliacao = new AmostraAvaliacao();
            amostraAvaliacao.setAvaliacao(avaliacaoSalva);
            amostraAvaliacao.setAmostra(amostraSalva);
            avaliacaoSalva.getAmostrasAvaliacao().add(amostraAvaliacao);
        }

        avaliacaoRepository.save(avaliacaoSalva);

        return convertToDto(avaliacaoSalva);
    }

    private static Avaliacao getAvaliacao(AvaliacaoDto dto, Usuario usuario) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setUsuario(usuario);
        avaliacao.setLocalAmostra(dto.getLocalAmostra());
        avaliacao.setScoreFinal(dto.getScoreFinal());
        avaliacao.setDecisaoManejoAvaliacao(dto.getDecisaoManejoAvaliacao());
        avaliacao.setResumoAvaliacao(dto.getResumoAvaliacao());
        avaliacao.setDataInicioAvaliacao(dto.getDataInicioAvaliacao());
        avaliacao.setDataFimAvaliacao(dto.getDataFimAvaliacao());
        avaliacao.setTempoDeAvaliacao(dto.getTempoDeAvaliacao());
        return avaliacao;
    }

    private AvaliacaoDto convertToDto(Avaliacao avaliacao) {
        AvaliacaoDto dto = new AvaliacaoDto();
        dto.setId(avaliacao.getId());
        dto.setLocalAmostra(avaliacao.getLocalAmostra());
        dto.setScoreFinal(avaliacao.getScoreFinal());
        dto.setDecisaoManejoAvaliacao(avaliacao.getDecisaoManejoAvaliacao());
        dto.setResumoAvaliacao(avaliacao.getResumoAvaliacao());
        dto.setDataInicioAvaliacao(avaliacao.getDataInicioAvaliacao());
        dto.setDataFimAvaliacao(avaliacao.getDataFimAvaliacao());
        dto.setTempoDeAvaliacao(avaliacao.getTempoDeAvaliacao());
        return dto;
    }
}

