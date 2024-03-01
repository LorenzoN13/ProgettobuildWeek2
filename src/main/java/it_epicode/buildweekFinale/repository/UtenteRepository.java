package it_epicode.buildweekFinale.repository;

import it_epicode.buildweekFinale.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer>, PagingAndSortingRepository<Utente,Integer> {

    public Optional<Utente> getUtenteByUsername(String username);
}
