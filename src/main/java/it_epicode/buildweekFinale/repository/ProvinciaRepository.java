package it_epicode.buildweekFinale.repository;

import it_epicode.buildweekFinale.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    public Optional<Provincia> getProvinciaByNome(String nome);

    public Optional<Provincia> getProvinciaBySigla(String sigla);

    public List<Provincia> getProvinciaByRegione(String regione);
}
