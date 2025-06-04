package br.edu.utfpr.pb.project.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Language {
    ESPANHOL("Espanhol"),
    PORTUGUES_BRASIL("Português (Brasil)"),
    PORTUGUES_PORTUGAL("Português (Portugal)"),
    INGLES("Inglês");

    private final String label;
}
