package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.security.Timestamp;
import java.util.Date;

@Data
public class AvaliacaoDto {
    private Long id;

    private Usuario usuario;

    private String localAmostra;

    private Double scoreAvaliacao;

    private String decisaoManejoAvaliacao;

    private String resumoAvaliacao;

    private Date dataInicioAvaliacao;

    private Date dataFimAvaliacao;

    private Timestamp tempoDeAvaliacao;
}
