package it_epicode.buildweekFinale.controller;

import com.cloudinary.Cloudinary;
import it_epicode.buildweekFinale.exception.NotFoundException;
import it_epicode.buildweekFinale.model.DefaultResponse;
import it_epicode.buildweekFinale.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private Cloudinary cloudinary;
    @PatchMapping("/upload/{id}")
    public ResponseEntity<DefaultResponse> upload(@PathVariable String partitaIva, @RequestParam("upload") MultipartFile file) throws IOException, NotFoundException {
        String url=(String) cloudinary.uploader().upload(file.getBytes(),new HashMap()).get("url");
        return DefaultResponse.customMessage("ImmagineCaricata",clienteService.setlogo(partitaIva,url), HttpStatus.OK);
    }
}
