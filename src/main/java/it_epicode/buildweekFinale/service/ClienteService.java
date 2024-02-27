package it_epicode.buildweekFinale.service;

import it_epicode.buildweekFinale.exception.NotFoundException;
import it_epicode.buildweekFinale.model.Cliente;
import it_epicode.buildweekFinale.repository.ClienteRepository;
import it_epicode.buildweekFinale.request.ClienteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public Page<Cliente> getClienti(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    public Cliente getByPartitaIva(String partitaIva){
        return clienteRepository.getClienteByPartitaIva(partitaIva).orElseThrow(() -> new NotFoundException("Partita Iva non trovata."));
    }

    public Cliente save(ClienteRequest clienteRequest) throws Exception {

        Cliente cliente = new Cliente();

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
        cliente.setIndirizzi(clienteRequest.getIndirizzo());

        if (cliente.getIndirizzi().size() > 2) throw new Exception("Puoi inserire massimo due indirizzi.");

        return clienteRepository.save(cliente);
    }

    public Cliente update(String partitaIva, ClienteRequest clienteRequest) throws Exception{
        Cliente cliente = getByPartitaIva(partitaIva);

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
        cliente.setIndirizzi(clienteRequest.getIndirizzo());

        if (cliente.getIndirizzi().size() > 2) throw new Exception("Puoi inserire massimo due indirizzi.");

        return clienteRepository.save(cliente);
    }

    public void delete(String partitaIva){
        Cliente cliente = getByPartitaIva(partitaIva);
        clienteRepository.delete(cliente);
    }


}
