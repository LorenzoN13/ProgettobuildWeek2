package it_epicode.buildweekFinale.repository;

import it_epicode.buildweekFinale.model.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Integer>, PagingAndSortingRepository<Indirizzo,Integer> {

}
