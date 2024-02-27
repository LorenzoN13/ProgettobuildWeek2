package it_epicode.buildweekFinale.model;

import jakarta.persistence.*;

@Entity
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String via;
    private String civico;
    private String localita;
    private String cap;
    @ManyToOne
    @JoinColumn(name = "comune_id")
  private Comune comune;

    public Indirizzo() {
    }

    public Indirizzo(Long id, String via, String civico, String localita, String cap, Comune comune) {
        this.id = id;
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.comune = comune;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public Comune getComune() {
        return comune;
    }

    public void setComune(Comune comune) {
        this.comune = comune;
    }

    @Override
    public String toString() {
        return "Indirizzo{" +
                "id=" + id +
                ", via='" + via + '\'' +
                ", civico='" + civico + '\'' +
                ", localita='" + localita + '\'' +
                ", cap='" + cap + '\'' +
                ", comune=" + comune +
                '}';
    }
}
