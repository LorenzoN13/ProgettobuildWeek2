package it_epicode.buildweekFinale.controller;

import it_epicode.buildweekFinale.exception.BadRequestException;
import it_epicode.buildweekFinale.exception.NotFoundException;
import it_epicode.buildweekFinale.model.DefaultResponse;
import it_epicode.buildweekFinale.request.FatturaRequest;
import it_epicode.buildweekFinale.request.UtenteRequest;
import it_epicode.buildweekFinale.service.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    public ResponseEntity<DefaultResponse> getAll(Pageable pageable){
        return DefaultResponse.noMessage(fatturaService.getFatture(pageable), HttpStatus.OK);
    }
    @GetMapping("/{numeroFattura}")
    public ResponseEntity<DefaultResponse> getByFatture(@PathVariable String numeroFattura) throws NotFoundException {
        return DefaultResponse.customMessage("Trovata",fatturaService.getByFattura(numeroFattura),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DefaultResponse> create(@PathVariable String partitaIva,@RequestBody @Validated FatturaRequest fatturaRequest, BindingResult bR){
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.customMessage("Creato",fatturaService.save(partitaIva,fatturaRequest),HttpStatus.CREATED);
    }
    @PutMapping("/{numeroFattura}")
    public ResponseEntity<DefaultResponse> update(@PathVariable String partitaIva,@PathVariable String numeroFattura,@RequestBody @Validated FatturaRequest d, BindingResult bR) throws NotFoundException {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.customMessage("Aggiornato",fatturaService.update(numeroFattura,d,partitaIva),HttpStatus.OK);
    }
    @DeleteMapping("/{numeroFattura}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable String numeroFattura) throws NotFoundException {
        fatturaService.delete(numeroFattura);
        return DefaultResponse.noObject("La fattura con il numero fattura =" + numeroFattura + "è stata eliminata",HttpStatus.OK);
    }
}
