package com.musicapp.labBD.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {

    @EmbeddedId
    private PlaylistId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MusicaPlaylist> musicas;

    @PrePersist
    public void prePersist() {
        if (dataCriacao == null) {
            dataCriacao = LocalDateTime.now();
        }
    }

    public Playlist() {
    }

    public Playlist(PlaylistId id, Usuario usuario, String nome, LocalDateTime dataCriacao, List<MusicaPlaylist> musicas) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.musicas = musicas;
    }

    public PlaylistId getId() {
        return id;
    }

    public void setId(PlaylistId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<MusicaPlaylist> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<MusicaPlaylist> musicas) {
        this.musicas = musicas;
    }
}