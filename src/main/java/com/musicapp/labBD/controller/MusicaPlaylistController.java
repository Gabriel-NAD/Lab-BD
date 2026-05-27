package com.musicapp.labBD.controller;

import com.musicapp.labBD.dto.MusicaPlaylistRequest;
import com.musicapp.labBD.entity.MusicaPlaylist;
import com.musicapp.labBD.service.MusicaPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/musica-playlist")
public class MusicaPlaylistController {

    @Autowired
    private MusicaPlaylistService service;

    @PostMapping
    public ResponseEntity<MusicaPlaylist> adicionar(@RequestBody MusicaPlaylistRequest request) {
        return ResponseEntity.status(201).body(service.adicionarMusica(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> remover(@RequestBody MusicaPlaylistRequest request) {
        service.removerMusica(request.getMusicaId(), request.getPlaylistId(), request.getUsuarioId());
        return ResponseEntity.noContent().build();
    }
}