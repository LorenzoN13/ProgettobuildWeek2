package it_epicode.buildweekFinale.model;

import lombok.Data;

@Data
public class LoginResponse {
    private String jwt;
    private Utente utente;

    public LoginResponse(String jwt, Utente utente) {
        this.jwt = jwt;
        this.utente = utente;
    }
}
