package it_epicode.buildweekFinale.controller;

import it_epicode.buildweekFinale.exception.BadRequestException;
import it_epicode.buildweekFinale.exception.NotFoundException;
import it_epicode.buildweekFinale.model.Cliente;
import it_epicode.buildweekFinale.model.DefaultResponse;
import it_epicode.buildweekFinale.request.IndirizzoRequest;
import it_epicode.buildweekFinale.service.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.customMessage("Trovato",indirizzoService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> save(@RequestBody @Validated IndirizzoRequest  indirizzoRequest, @RequestBody @Validated Cliente cliente, BindingResult bR) throws Exception {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.customMessage("Creato",indirizzoService.save(indirizzoRequest,cliente),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> update(@PathVariable int id,@RequestBody @Validated IndirizzoRequest d,@RequestBody @Validated Cliente cliente, BindingResult bR) throws Exception {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.customMessage("Aggiornato",indirizzoService.update(id,d, cliente),HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable int id) throws NotFoundException {
        indirizzoService.delete(id);
        return DefaultResponse.noObject("L'id =" + id + " è stato eliminato",HttpStatus.OK);
    }

}
