package it_epicode.buildweekFinale.repository;


import it_epicode.buildweekFinale.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
}
