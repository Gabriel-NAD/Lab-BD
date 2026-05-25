package com.musicapp.labBD.dto;

public class MusicaRequest {
    private String titulo;
    private Integer duracaoSegundos;

    public MusicaRequest() {
    }

    public MusicaRequest(String titulo, Integer duracaoSegundos, Long artistaId) {
        this.titulo = titulo;
        this.duracaoSegundos = duracaoSegundos;
        this.artistaId = artistaId;
    }

    private Long artistaId;

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

    public Long getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(Long artistaId) {
        this.artistaId = artistaId;
    }
// getters e setters
}