package it_epicode.buildweekFinale.service;

import it_epicode.buildweekFinale.exception.NotFoundException;
import it_epicode.buildweekFinale.model.Cliente;
import it_epicode.buildweekFinale.model.Fattura;
import it_epicode.buildweekFinale.repository.ClienteRepository;
import it_epicode.buildweekFinale.repository.FatturaRepository;
import it_epicode.buildweekFinale.request.FatturaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Fattura> getFatture(Pageable pageable){
        return fatturaRepository.findAll(pageable);
    }

    public Fattura getByFattura(String numeroFattura){
        return fatturaRepository.getFatturaByNumeroFattura(numeroFattura).orElseThrow(() -> new NotFoundException("numeroFattura non trovata"));
    }


    public Fattura save(FatturaRequest fatturaRequest, String partitaIva){

        Cliente cliente = clienteService.getByPartitaIva(partitaIva);
        Fattura fattura = new Fattura();

        fattura.setCliente(cliente);
        fattura.setData(fatturaRequest.getData());
        fattura.setImporto(fatturaRequest.getImporto());
        fattura.setNumeroFattura(fatturaRequest.getNumeroFattura());
        fattura.setNumero(fatturaRequest.getNumero());

        return fatturaRepository.save(fattura);
    }

    public Fattura update(String partitaIva, FatturaRequest fatturaRequest, String numeroFattura){

        Cliente cliente = clienteService.getByPartitaIva(partitaIva);
        Fattura fattura = getByFattura(numeroFattura);

        fattura.setCliente(cliente);
        fattura.setData(fatturaRequest.getData());
        fattura.setImporto(fatturaRequest.getImporto());
        fattura.setNumeroFattura(fatturaRequest.getNumeroFattura());
        fattura.setNumero(fatturaRequest.getNumero());
        return fatturaRepository.save(fattura);
    }

    public void delete(String numeroFattura){
        Fattura fattura = getByFattura(numeroFattura);
        fatturaRepository.delete(fattura);
    }

}
