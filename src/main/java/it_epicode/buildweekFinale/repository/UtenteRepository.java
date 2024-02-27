package it_epicode.buildweekFinale.repository;

import it_epicode.buildweekFinale.model.Utente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UtenteRepository extends JpaRepository<Utente, Integer>, PagingAndSortingRepository<Utente,Integer> {
}
