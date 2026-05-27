package com.musicapp.labBD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MusicaPlaylistId implements Serializable {

    @Column(name = "musica_id")
    private Long musicaId;

    @Column(name = "playlist_id")
    private Long playlistId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    public MusicaPlaylistId() {
    }

    public MusicaPlaylistId(Long musicaId, Long playlistId, Long usuarioId) {
        this.musicaId = musicaId;
        this.playlistId = playlistId;
        this.usuarioId = usuarioId;
    }

    public Long getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(Long musicaId) {
        this.musicaId = musicaId;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MusicaPlaylistId that = (MusicaPlaylistId) o;
        return Objects.equals(musicaId, that.musicaId) && Objects.equals(playlistId, that.playlistId) && Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(musicaId, playlistId, usuarioId);
    }
}