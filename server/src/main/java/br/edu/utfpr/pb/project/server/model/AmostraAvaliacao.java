package br.edu.utfpr.pb.project.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_amostra_avaliacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmostraAvaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "amostra_id")
    private Amostra amostra;

    @ManyToOne
    @JoinColumn(name = "avaliacao_id")
    @JsonIgnore
    private Avaliacao avaliacao;
}
