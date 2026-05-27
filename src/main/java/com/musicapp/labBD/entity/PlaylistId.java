package com.musicapp.labBD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlaylistId implements Serializable {

    @Column(name = "playlist_id")
    private Long playlistId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    public PlaylistId() {
    }

    public PlaylistId(Long usuarioId, Long playlistId) {
        this.usuarioId = usuarioId;
        this.playlistId = playlistId;
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
        PlaylistId that = (PlaylistId) o;
        return Objects.equals(playlistId, that.playlistId) && Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, usuarioId);
    }
}