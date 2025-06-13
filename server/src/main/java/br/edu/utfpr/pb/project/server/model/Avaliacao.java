package br.edu.utfpr.pb.project.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "tb_avaliacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @NotNull
    private String localAmostra;

    @NotNull
    private Double scoreAvaliacao;

    @NotNull
    private String decisaoManejoAvaliacao;

    @NotNull
    private String resumoAvaliacao;

    @NotNull
    private Date dataInicioAvaliacao;

    @NotNull
    private Date dataFimAvaliacao;

    @NotNull
    private Timestamp tempoDeAvaliacao;
}
