package it_epicode.buildweekFinale.controller;

import com.cloudinary.Cloudinary;
import it_epicode.buildweekFinale.exception.BadRequestException;
import it_epicode.buildweekFinale.exception.NotFoundException;
import it_epicode.buildweekFinale.model.DefaultResponse;
import it_epicode.buildweekFinale.request.UtenteRequest;
import it_epicode.buildweekFinale.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;


@RestController
public class UtenteController {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JavaMailSenderImpl JMS;

    @GetMapping
    public ResponseEntity<DefaultResponse> getAll(Pageable pageable){
        return DefaultResponse.noMessage(utenteService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{username}")
    public ResponseEntity<DefaultResponse> getByUsername(@PathVariable String username) throws NotFoundException {
        return DefaultResponse.customMessage("Trovato",utenteService.getByUsername(username),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DefaultResponse> create(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bR){
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        sendEmail(utenteRequest.getEmail());
        return DefaultResponse.customMessage("Creato",utenteService.save(utenteRequest),HttpStatus.CREATED);
    }
    @PutMapping("/{username}")
    public ResponseEntity<DefaultResponse> update(@PathVariable String username,@RequestBody @Validated UtenteRequest d, BindingResult bR) throws NotFoundException {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.customMessage("Aggiornato",utenteService.update(d,username),HttpStatus.OK);
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable String username) throws NotFoundException {
        utenteService.delete(username);
        return DefaultResponse.noObject("Utente con username=" + username + " eliminato",HttpStatus.OK);
    }
    @PatchMapping("/upload/{username}")
    public ResponseEntity<DefaultResponse> upload(@PathVariable String username,@RequestParam("upload") MultipartFile file) throws IOException, NotFoundException {
        String url=(String) cloudinary.uploader().upload(file.getBytes(),new HashMap()).get("url");
        return DefaultResponse.customMessage("ImmagineCaricata",utenteService.setAvatar(username,url),HttpStatus.OK);
    }
    public void sendEmail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Ciao!");
        message.setText("So che te l'ho già detto ma, ciao!");
        JMS.send(message);
    }
}
