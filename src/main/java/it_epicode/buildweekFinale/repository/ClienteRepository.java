package it_epicode.buildweekFinale.repository;


import it_epicode.buildweekFinale.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteRepository  extends JpaRepository<Cliente, Integer>, PagingAndSortingRepository<Cliente,Integer> {
}
