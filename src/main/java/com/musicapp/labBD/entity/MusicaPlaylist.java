package com.musicapp.labBD.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "musica_playlist")
public class MusicaPlaylist {

    @EmbeddedId
    private MusicaPlaylistId id;

    @ManyToOne
    @MapsId("musicaId")
    @JoinColumn(name = "musica_id")
    private Musica musica;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "playlist_id", referencedColumnName = "playlist_id", insertable = false, updatable = false),
            @JoinColumn(name = "usuario_id",  referencedColumnName = "usuario_id",  insertable = false, updatable = false)
    })
    private Playlist playlist;
    @Column(name = "ordem_na_playlist", nullable = false)
    private Integer ordemNaPlaylist;

    public MusicaPlaylist() {}

    public MusicaPlaylist(MusicaPlaylistId id, Musica musica, Playlist playlist, Integer ordemNaPlaylist) {
        this.id = id;
        this.musica = musica;
        this.playlist = playlist;
        this.ordemNaPlaylist = ordemNaPlaylist;
    }

    public MusicaPlaylistId getId() { return id; }
    public void setId(MusicaPlaylistId id) { this.id = id; }
    public Musica getMusica() { return musica; }
    public void setMusica(Musica musica) { this.musica = musica; }
    public Playlist getPlaylist() { return playlist; }
    public void setPlaylist(Playlist playlist) { this.playlist = playlist; }
    public Integer getOrdemNaPlaylist() { return ordemNaPlaylist; }
    public void setOrdemNaPlaylist(Integer ordemNaPlaylist) { this.ordemNaPlaylist = ordemNaPlaylist; }
}