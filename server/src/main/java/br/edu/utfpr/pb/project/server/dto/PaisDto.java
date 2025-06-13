package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.enums.PaisType;
import lombok.Data;

@Data
public class PaisDto {
    private Long id;

    private String name;

    private PaisType type;
}
