package br.edu.utfpr.pb.project.server.model;

import br.edu.utfpr.pb.project.server.enums.Country;
import br.edu.utfpr.pb.project.server.enums.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.name.NotNull}")
    @NotEmpty
    private String name;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.email.NotNull}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{br.edu.utfpr.pb.project.server.user.email.Pattern}")
    private String email;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.country.NotNull}")
    private Country country;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.address.NotNull}")
    private String address;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.language.NotNull}")
    private Language language;
}
