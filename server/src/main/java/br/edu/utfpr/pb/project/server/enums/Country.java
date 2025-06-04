package br.edu.utfpr.pb.project.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Country {
    ARGENTINA("Argentina"),
    BOLIVIA("Bolivia"),
    BRASIL("Brasil"),
    CHILE("Chile"),
    COLOMBIA("Colômbia"),
    EQUADOR("Equador"),
    GUIANA("Guiana"),
    PARAGUAI("Paraguai"),
    PERU("Peru"),
    SURINAME("Suriname"),
    URUGUAI("Uruguai"),
    VENEZUELA("Venezuela");

    private final String label;
}
