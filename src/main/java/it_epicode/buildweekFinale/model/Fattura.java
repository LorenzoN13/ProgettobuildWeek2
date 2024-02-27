package it_epicode.buildweekFinale.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "fatture")
public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numero;
    private LocalDate data;
    private int importo;
    private String numeroFattura;


    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
