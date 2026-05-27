package com.musicapp.labBD.dto;


public class PlaylistRequest {
    private String nome;
    private Long usuarioId;

    public PlaylistRequest() {
    }

    public PlaylistRequest(String nome, Long usuarioId) {
        this.nome = nome;
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}