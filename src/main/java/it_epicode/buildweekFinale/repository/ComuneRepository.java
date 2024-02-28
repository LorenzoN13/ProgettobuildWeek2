package it_epicode.buildweekFinale.repository;

import it_epicode.buildweekFinale.model.Comune;
import it_epicode.buildweekFinale.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Integer> {
    public Optional<Comune> getComuneByNome(String nome);

    public List<Comune> getComuneByProvincia(Provincia provincia);
}
