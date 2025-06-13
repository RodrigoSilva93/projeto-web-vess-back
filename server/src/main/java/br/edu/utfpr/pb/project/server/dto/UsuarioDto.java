package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.model.Idioma;
import br.edu.utfpr.pb.project.server.model.Pais;
import lombok.Data;

@Data
public class UsuarioDto {

    private Long id;

    private String nome;

    private String email;

    private Pais pais;

    private String endereco;

    private Idioma idioma;
}
