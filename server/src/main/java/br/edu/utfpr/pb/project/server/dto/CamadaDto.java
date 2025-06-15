package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.model.Amostra;
import lombok.Data;

@Data
public class CamadaDto {
    private Long id;

    private Amostra amostra;

    private Double comprimento;

    private Double nota;
}
