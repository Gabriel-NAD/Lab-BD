package com.musicapp.labBD.entity;

import java.io.Serializable;
import java.util.Objects;

public class PlaylistId implements Serializable {

    private Long playlistId;
    private Long usuario;

    public PlaylistId() {}

    public PlaylistId(Long playlistId, Long usuario) {
        this.playlistId = playlistId;
        this.usuario = usuario;
    }

    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }

    public Long getUsuario() { return usuario; }
    public void setUsuario(Long usuario) { this.usuario = usuario; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistId that = (PlaylistId) o;
        return Objects.equals(playlistId, that.playlistId) &&
                Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, usuario);
    }
}