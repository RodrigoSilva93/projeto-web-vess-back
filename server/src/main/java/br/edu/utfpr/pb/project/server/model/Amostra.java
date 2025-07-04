package br.edu.utfpr.pb.project.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_amostra")
@Getter     @Setter
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

    //@Column(name = "content_image_amostra")
    //private String contentImageAmostra;

    //@Column(name = "type_image_amostra")
    //private String typeImageAmostra;

    private String imagemNomeArquivo;

    private String outrasInformacoesAmostra;

    @OneToOne(mappedBy = "amostra", cascade = CascadeType.ALL)
    private ScoreAmostra scoreAmostra;

    @OneToMany(mappedBy = "amostra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Camada> camadas = new ArrayList<>();
}
