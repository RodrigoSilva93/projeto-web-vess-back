package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.model.Usuario;
import lombok.Data;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class AvaliacaoDto {
    private Long id;

    private Usuario usuario;

    private String localAmostra;

    private Double scoreFinal;

    private String decisaoManejoAvaliacao;

    private String resumoAvaliacao;

    private Date dataInicioAvaliacao;

    private Date dataFimAvaliacao;

    private Long tempoDeAvaliacao;

    private List<AmostraDto> amostras;
}
