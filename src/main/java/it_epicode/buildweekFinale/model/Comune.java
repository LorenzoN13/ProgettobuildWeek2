package it_epicode.buildweekFinale.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "comuni")
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_comuni")
    @SequenceGenerator(name = "sequenza_comuni", initialValue = 1, allocationSize = 1)
    private int id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    @OneToMany(mappedBy = "comune")
    private List<Indirizzo> indirizzi;

    public Comune(){}

    public Comune(String nome, Provincia provincia){
        this.nome = nome;
        this.provincia = provincia;
    }
}
