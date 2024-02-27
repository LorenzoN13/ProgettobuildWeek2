package it_epicode.buildweekFinale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Fattura {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
private Long id;
private String numero;
private LocalDate data;
private BigDecimal importo;
private String numeroFattura;

public Fattura() {
    }

    public Fattura(Long id, String numero, LocalDate data, BigDecimal importo, String numeroFattura) {
        this.id = id;
        this.numero = numero;
        this.data = data;
        this.importo = importo;
        this.numeroFattura = numeroFattura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public String getNumeroFattura() {
        return numeroFattura;
    }

    public void setNumeroFattura(String numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    @Override
    public String toString() {
        return "Fattura{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", data=" + data +
                ", importo=" + importo +
                ", numeroFattura='" + numeroFattura + '\'' +
                '}';
    }
}
