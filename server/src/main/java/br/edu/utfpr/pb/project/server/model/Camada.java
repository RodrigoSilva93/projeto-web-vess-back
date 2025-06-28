package br.edu.utfpr.pb.project.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_camada")
@Getter     @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Camada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "amostra_id")
    @JsonIgnore
    private Amostra amostra;

    @NotNull
    private Double comprimento;

    @NotNull
    private Double nota;
}
