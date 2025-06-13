package br.edu.utfpr.pb.project.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_amostra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Amostra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nomeAmostra;

    @NotNull
    private Integer qtdCamadasAmostra;

    @Column(name = "content_image_amostra")
    private String contentImageAmostra;

    @Column(name = "type_image_amostra")
    private String typeImageAmostra;

    private String outrasInformacoesAmostra;
}
