package it_epicode.buildweekFinale.controller;

import com.cloudinary.Cloudinary;
import it_epicode.buildweekFinale.exception.BadRequestException;
import it_epicode.buildweekFinale.exception.NotFoundException;
import it_epicode.buildweekFinale.model.DefaultResponse;
import it_epicode.buildweekFinale.request.ClienteRequest;
import it_epicode.buildweekFinale.request.UtenteRequest;
import it_epicode.buildweekFinale.service.ClienteService;
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
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl JMS;

    @GetMapping
    public ResponseEntity<DefaultResponse> getAll(Pageable pageable){
        return DefaultResponse.noMessage(clienteService.getClienti(pageable), HttpStatus.OK);
    }

    @GetMapping("/{partitaIva}")
    public ResponseEntity<DefaultResponse> getByPartitaIva(@PathVariable String partitaIva) throws NotFoundException {
        return DefaultResponse.customMessage("Trovato",clienteService.getByPartitaIva(partitaIva),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> create(@RequestBody @Validated ClienteRequest clienteRequest, BindingResult bR) throws Exception {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        sendEmail(clienteRequest.getEmail());
        return DefaultResponse.customMessage("Creato",clienteService.save(clienteRequest),HttpStatus.CREATED);
    }

    @PutMapping("/{partitaIva}")
    public ResponseEntity<DefaultResponse> update(@PathVariable String partitaIva,@RequestBody @Validated ClienteRequest d, BindingResult bR) throws Exception {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.customMessage("Aggiornato",clienteService.update(partitaIva,d),HttpStatus.OK);
    }


    @PatchMapping("/upload/{partitaIva}")
    public ResponseEntity<DefaultResponse> upload(@PathVariable String partitaIva, @RequestParam("upload") MultipartFile file) throws IOException, NotFoundException {
        String url=(String) cloudinary.uploader().upload(file.getBytes(),new HashMap()).get("url");
        return DefaultResponse.customMessage("ImmagineCaricata",clienteService.setlogo(partitaIva,url), HttpStatus.OK);
    }
    @DeleteMapping("/{partitaIva}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable String partitaIva) throws NotFoundException {
        clienteService.delete(partitaIva);
        return DefaultResponse.noObject("Il cliente con la parititaIva =" + partitaIva + " eliminato",HttpStatus.OK);
    }


    public void sendEmail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Ciao!");
        message.setText("So che te l'ho già detto ma, ciao!");
        JMS.send(message);
    }
}
