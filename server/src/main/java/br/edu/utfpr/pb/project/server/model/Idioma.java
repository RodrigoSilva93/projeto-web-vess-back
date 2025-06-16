package br.edu.utfpr.pb.project.server.model;

import br.edu.utfpr.pb.project.server.enums.IdiomaType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_idioma")
@Getter     @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String nome;

    @Enumerated(EnumType.STRING)
    private IdiomaType tipo;
}
