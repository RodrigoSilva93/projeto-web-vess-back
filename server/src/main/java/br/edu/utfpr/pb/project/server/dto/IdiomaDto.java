package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.enums.IdiomaType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IdiomaDto {
    private Long id;

    private String name;

    private IdiomaType type;
}
