package it_epicode.buildweekFinale.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "username obbligatoria")
    private String usermane;
    @NotBlank(message = "password obbligatoria")
    private String password;
}
