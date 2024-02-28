package it_epicode.buildweekFinale.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "clienti")
public class Cliente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private int id;
    private String ragioneSociale;
    @Column(unique = true)
    private String partitaIva;
    @Column(unique = true)
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private int fatturatoAnnuale;
    @Column(unique = true)
    private String pec;
    private String telefono;
    @Column(unique = true)
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;

    @OneToMany(mappedBy = "cliente")
    private List<Indirizzo> indirizzi;

    @OneToMany(mappedBy = "cliente")
    private List<Fattura> fatture;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;


}
