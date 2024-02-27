package it_epicode.buildweekFinale.repository;


import it_epicode.buildweekFinale.model.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
}
