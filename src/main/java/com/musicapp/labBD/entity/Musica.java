package com.musicapp.labBD.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "musica")
public class Musica {
    public Musica() {
    }

    public Musica(Long id, String titulo, Integer duracaoSegundos, Artista artista) {
        this.id = id;
        this.titulo = titulo;
        this.duracaoSegundos = duracaoSegundos;
        this.artista = artista;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "duracao_segundos", nullable = false)
    private Integer duracaoSegundos;

    @ManyToOne
    @JoinColumn(name = "artista_id", nullable = false)
    private Artista artista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(Integer duracaoSegundos) {
        this.duracaoSegundos = duracaoSegundos;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}