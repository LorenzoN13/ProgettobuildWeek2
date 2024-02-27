package it_epicode.buildweekFinale.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtenteRequest {
    @NotBlank(message = "nome obbligatorio")
    private String nome;
    @NotBlank(message = "cognome obbligatorio")
    private String cognome;
    @Email(message = "formato email non valido")
    private String email;
    @NotBlank(message = "password obbligatoria")
    private String password;
    @NotBlank(message = "username obbligatorio")
    private String username;
}
