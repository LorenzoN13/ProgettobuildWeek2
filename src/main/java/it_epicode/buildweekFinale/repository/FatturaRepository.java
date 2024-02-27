package it_epicode.buildweekFinale.repository;


import it_epicode.buildweekFinale.model.Fattura;
import it_epicode.buildweekFinale.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FatturaRepository extends JpaRepository<Fattura, Integer>, PagingAndSortingRepository<Fattura,Integer> {
}
