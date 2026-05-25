package com.musicapp.labBD.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Artista() {
    }

    public Artista(Long id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @Column(nullable = false, unique = true)
    private String nome;

    private String nacionalidade;
}