package br.edu.utfpr.pb.project.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.usuario.nome.NotNull}")
    @NotEmpty
    private String nome;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.usuario.email.NotNull}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{br.edu.utfpr.pb.project.server.user.email.Pattern}")
    private String email;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.usuario.pais.NotNull}")
    @JoinColumn(name = "pais_id")
    @ManyToOne
    private Pais pais;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.usuario.endereco.NotNull}")
    private String endereco;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.usuario.idioma.NotNull}")
    @JoinColumn(name = "idioma_id")
    @ManyToOne
    private Idioma idioma;
}
