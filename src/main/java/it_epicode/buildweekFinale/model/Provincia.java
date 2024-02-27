package it_epicode.buildweekFinale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "province")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_province")
    @SequenceGenerator(name = "sequenza_province", initialValue = 1, allocationSize = 1)
    private int id;
    private String sigla;
    private String nome;
    private String regione;
    @OneToMany(mappedBy = "provincia")
    @JsonIgnore
    private List<Comune> comuni;

    public Provincia(){}

    public Provincia(String sigla, String nome, String regione){
        this.sigla = sigla;
        this.nome = nome;
        this.regione = regione;
    }
}
