package it_epicode.buildweekFinale.service;

import it_epicode.buildweekFinale.exception.NotFoundException;
import it_epicode.buildweekFinale.model.Cliente;
import it_epicode.buildweekFinale.model.Indirizzo;
import it_epicode.buildweekFinale.repository.ClienteRepository;
import it_epicode.buildweekFinale.repository.IndirizzoRepository;
import it_epicode.buildweekFinale.request.ClienteRequest;
import it_epicode.buildweekFinale.request.IndirizzoRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DbService dbService;

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    public Page<Cliente> getClienti(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    public Cliente getByPartitaIva(String partitaIva){
        return clienteRepository.getClienteByPartitaIva(partitaIva).orElseThrow(() -> new NotFoundException("Partita Iva non trovata."));
    }

    @Transactional
    public Cliente save(ClienteRequest clienteRequest) throws Exception {

        Cliente cliente = new Cliente();

        if (cliente.getIndirizzi().size() > 2) throw new Exception("Puoi inserire massimo due indirizzi.");

        cliente.setNomeContatto(clienteRequest.getNomeContatto());
        cliente.setCognomeContatto(clienteRequest.getNomeContatto());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setPec(clienteRequest.getPec());
        cliente.setFatturatoAnnuale(clienteRequest.getFatturatoAnnuale());
        cliente.setTipo(clienteRequest.getTipo());
        cliente.setTelefono(clienteRequest.getTelefono());
        cliente.setDataInserimento(clienteRequest.getDataInserimento());
        cliente.setDataInserimento(clienteRequest.getDataInserimento());
        cliente.setLogoAziendale(clienteRequest.getLogoAziendale());
        cliente.setPartitaIva(clienteRequest.getPartitaIva());
        cliente.setTelefonoContatto(clienteRequest.getTelefonoContatto());

        clienteRepository.save(cliente);

        for (IndirizzoRequest indirizzoRequest :clienteRequest.getIndirizzo()){

           Indirizzo indirizzo = new Indirizzo();

           indirizzo.setCliente(cliente);
           indirizzo.setComune(dbService.getComuneById(indirizzoRequest.getIdComune()));
           indirizzo.setCap(indirizzoRequest.getCap());
           indirizzo.setVia(indirizzoRequest.getVia());
           indirizzo.setCivico(indirizzoRequest.getCivico());
           indirizzoRepository.save(indirizzo);
        }

        return cliente;
    }

    public Cliente update(String partitaIva, ClienteRequest clienteRequest) throws Exception{
        Cliente cliente = getByPartitaIva(partitaIva);

        if (cliente.getIndirizzi().size() > 2) throw new Exception("Puoi inserire massimo due indirizzi.");

        cliente.setNomeContatto(clienteRequest.getNomeContatto());
        cliente.setCognomeContatto(clienteRequest.getNomeContatto());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setPec(clienteRequest.getPec());
        cliente.setFatturatoAnnuale(clienteRequest.getFatturatoAnnuale());
        cliente.setTipo(clienteRequest.getTipo());
        cliente.setTelefono(clienteRequest.getTelefono());
        cliente.setDataInserimento(clienteRequest.getDataInserimento());
        cliente.setDataInserimento(clienteRequest.getDataInserimento());
        cliente.setLogoAziendale(clienteRequest.getLogoAziendale());
        cliente.setPartitaIva(clienteRequest.getPartitaIva());
        cliente.setTelefonoContatto(clienteRequest.getTelefonoContatto());

        clienteRepository.save(cliente);

        for (int i = 0; i < clienteRequest.getIndirizzo().size(); i++){
            Indirizzo indirizzo = cliente.getIndirizzi().get(i) == null ? new Indirizzo() : null;
        }

        return null;
    }

    public void delete(String partitaIva){
        Cliente cliente = getByPartitaIva(partitaIva);
        clienteRepository.delete(cliente);
    }


}
