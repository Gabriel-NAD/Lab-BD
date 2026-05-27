package com.musicapp.labBD.dto;

public class MusicaPlaylistRequest {
    private Long musicaId;
    private Long playlistId;
    private Long usuarioId;
    private Integer ordemNaPlaylist;

    public MusicaPlaylistRequest() {
    }

    public MusicaPlaylistRequest(Long musicaId, Long playlistId, Long usuarioId, Integer ordemNaPlaylist) {
        this.musicaId = musicaId;
        this.playlistId = playlistId;
        this.usuarioId = usuarioId;
        this.ordemNaPlaylist = ordemNaPlaylist;
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

    public Integer getOrdemNaPlaylist() {
        return ordemNaPlaylist;
    }

    public void setOrdemNaPlaylist(Integer ordemNaPlaylist) {
        this.ordemNaPlaylist = ordemNaPlaylist;
    }
}