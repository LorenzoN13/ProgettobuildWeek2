package it_epicode.buildweekFinale.service;

import it_epicode.buildweekFinale.exception.NotFoundException;
import it_epicode.buildweekFinale.model.Cliente;
import it_epicode.buildweekFinale.model.Comune;
import it_epicode.buildweekFinale.model.Indirizzo;
import it_epicode.buildweekFinale.repository.IndirizzoRepository;
import it_epicode.buildweekFinale.request.IndirizzoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    private DbService dbService;

    public Indirizzo getById(int id){
        return indirizzoRepository.findById(id).orElseThrow(() -> new NotFoundException("Indirizzo non trovato"));
    }

    public Indirizzo save(IndirizzoRequest indirizzoRequest, Cliente cliente){
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setComune(dbService.getComuneById(indirizzoRequest.getIdComune()));
        indirizzo.setCap(indirizzoRequest.getCap());
        indirizzo.setVia(indirizzoRequest.getVia());
        indirizzo.setCivico(indirizzoRequest.getCivico());
        indirizzo.setCliente(cliente);
        indirizzo.setSedeIndirizzo(indirizzoRequest.getSedeIndirizzo());
        return indirizzoRepository.save(indirizzo);
    }

    public Indirizzo update(int id, IndirizzoRequest indirizzoRequest, Cliente cliente){
        Indirizzo indirizzo = getById(id);
        indirizzo.setComune(dbService.getComuneById(indirizzoRequest.getIdComune()));
        indirizzo.setCap(indirizzoRequest.getCap());
        indirizzo.setVia(indirizzoRequest.getVia());
        indirizzo.setCivico(indirizzoRequest.getCivico());
        indirizzo.setCliente(cliente);
        indirizzo.setSedeIndirizzo(indirizzoRequest.getSedeIndirizzo());
        return indirizzoRepository.save(indirizzo);
    }

    public void delete(int id){
        indirizzoRepository.delete(getById(id));
    }
}
