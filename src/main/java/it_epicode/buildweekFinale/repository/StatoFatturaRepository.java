package it_epicode.buildweekFinale.repository;

import it_epicode.buildweekFinale.model.StatoFattura;
import it_epicode.buildweekFinale.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Integer>, PagingAndSortingRepository<StatoFattura,Integer> {
}
