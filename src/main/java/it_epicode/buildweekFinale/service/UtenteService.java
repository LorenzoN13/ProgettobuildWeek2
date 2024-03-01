package it_epicode.buildweekFinale.service;

import it_epicode.buildweekFinale.exception.NotFoundException;
import it_epicode.buildweekFinale.exception.UnAuthorizedException;
import it_epicode.buildweekFinale.model.LoginRequest;
import it_epicode.buildweekFinale.model.LoginResponse;
import it_epicode.buildweekFinale.model.Ruolo;
import it_epicode.buildweekFinale.model.Utente;
import it_epicode.buildweekFinale.repository.UtenteRepository;
import it_epicode.buildweekFinale.request.UtenteRequest;
import it_epicode.buildweekFinale.security.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private PasswordEncoder encoder;

    public Utente getByUsername(String username) {
        return utenteRepository.getUtenteByUsername(username).orElseThrow(() -> new NotFoundException("utente non trovato"));
    }
    public Page<Utente> findAll(Pageable pageable){
        return utenteRepository.findAll(pageable);
    }

    public Utente save(UtenteRequest utenteRequest){
        Utente utente = new Utente();
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setEmail(utenteRequest.getEmail());
        utente.setRuolo(Ruolo.USER);
        utente.setUsername(utenteRequest.getUsername());
        utente.setPassword(encoder.encode(utenteRequest.getPassword()));


        return utenteRepository.save(utente);
    }

    public LoginResponse login (LoginRequest loginRequest){
        Utente utente = getByUsername(loginRequest.getUsermane());
        if (!encoder.matches(loginRequest.getPassword(), utente.getPassword())) throw new UnAuthorizedException("usename/password errati");

        return new LoginResponse(jwtTools.createToken(utente), utente);
    }

    public Utente update(UtenteRequest utenteRequest, String USername){
        Utente utente = getByUsername(USername);

        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setEmail(utenteRequest.getEmail());
        utente.setRuolo(Ruolo.USER);
        utente.setUsername(utenteRequest.getUsername());
        utente.setPassword(encoder.encode(utenteRequest.getPassword()));
        return utenteRepository.save(utente);
    }

    public void delete (String username){
        utenteRepository.delete(getByUsername(username));
    }

    public Utente setAvatar(String username,String s) throws NotFoundException {
        Utente utente = getByUsername(username);
        utente.setAvatar(s);
        return utenteRepository.save(utente);
    }
}
