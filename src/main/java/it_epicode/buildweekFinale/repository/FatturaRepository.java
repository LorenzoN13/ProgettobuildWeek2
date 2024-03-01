package it_epicode.buildweekFinale.repository;


import it_epicode.buildweekFinale.model.Fattura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer>, PagingAndSortingRepository<Fattura,Integer> {

    public Optional<Fattura> getFatturaByNumeroFattura(String numeroFattura);
}
