package br.edu.utfpr.pb.project.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_score_amostra")
@Getter     @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("amostra")
public class ScoreAmostra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "amostra_id")
    private Amostra amostra;

    @NotNull
    private Double score;

    private String decisaoManejoScoreAmostra;

    @NotNull
    private String resumoScoreAmostra;

    private String infoScoreAmostra;
}
