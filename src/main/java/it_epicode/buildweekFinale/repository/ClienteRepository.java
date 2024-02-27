package it_epicode.buildweekFinale.repository;


import it_epicode.buildweekFinale.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Integer>, PagingAndSortingRepository<Cliente,Integer> {

    public Optional<Cliente> getClienteByPartitaIva(String partitaIva);
}
