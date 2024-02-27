package it_epicode.buildweekFinale.model;

import jakarta.persistence.*;

@Entity
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    public Comune() {
    }

    public Comune(Long id, String nome, Provincia provincia) {
        this.id = id;
        this.nome = nome;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Comune{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", provincia=" + provincia +
                '}';
    }
}
