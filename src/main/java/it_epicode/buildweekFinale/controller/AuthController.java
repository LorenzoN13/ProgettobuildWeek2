package it_epicode.buildweekFinale.controller;

import it_epicode.buildweekFinale.exception.BadRequestException;
import it_epicode.buildweekFinale.exception.LoginFaultException;
import it_epicode.buildweekFinale.model.LoginRequest;
import it_epicode.buildweekFinale.model.Utente;
import it_epicode.buildweekFinale.request.UtenteRequest;
import it_epicode.buildweekFinale.security.JwtTools;
import it_epicode.buildweekFinale.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JwtTools jwtTools;
    @PostMapping("/auth/register")
    public Utente register(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return utenteService.save(utenteRequest);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        Utente utente = utenteService.getByUsername(loginRequest.getUsermane());

        if(utente.getPassword().equals(loginRequest.getPassword())){
            return jwtTools.createToken(utente);
        }
        else{
            throw new LoginFaultException("username/password errate");
        }

    }
}
