package it_epicode.buildweekFinale.repository;

import it_epicode.buildweekFinale.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
