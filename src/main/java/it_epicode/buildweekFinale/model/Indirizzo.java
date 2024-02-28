package it_epicode.buildweekFinale.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "indirizzo")
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String via;
    private String civico;

    @ManyToOne
    @JoinColumn(name = "id_comune")
    private Comune comune;

    private String cap;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;



}
