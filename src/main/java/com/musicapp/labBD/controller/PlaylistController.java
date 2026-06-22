package com.musicapp.labBD.controller;

import com.musicapp.labBD.dto.PlaylistRequest;
import com.musicapp.labBD.entity.Playlist;
import com.musicapp.labBD.repository.PlaylistRepository;
import com.musicapp.labBD.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<Playlist> criar(@RequestBody PlaylistRequest request) {
        return ResponseEntity.status(201).body(playlistService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> listarTodas() {
        return ResponseEntity.ok(playlistService.listarTodas());
    }

    @PostMapping("/{playlistId}/usuario/{usuarioId}/musicas/{musicaId}")
    public ResponseEntity<Playlist> adicionarMusica(@PathVariable Long playlistId,
                                                    @PathVariable Long usuarioId,
                                                    @PathVariable Long musicaId) {
        return ResponseEntity.ok(playlistService.adicionarMusica(playlistId, usuarioId, musicaId));
    }

    @DeleteMapping("/{playlistId}/usuario/{usuarioId}/musicas/{musicaId}")
    public ResponseEntity<Void> removerMusica(@PathVariable Long playlistId,
                                              @PathVariable Long usuarioId,
                                              @PathVariable Long musicaId) {
        playlistService.removerMusica(playlistId, usuarioId, musicaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-usuario")
    public ResponseEntity<List<Playlist>> listarPorUsuario(@RequestParam String username) {
        return ResponseEntity.ok(playlistService.listarPorUsuario(username));
    }

    @GetMapping("/contagem-musicas")
    public ResponseEntity<List<PlaylistRepository.PlaylistMusicaCountDTO>> contarMusicas() {
        return ResponseEntity.ok(playlistService.contarMusicasPorPlaylist());
    }

    @PostMapping("/transferir-musica")
    public ResponseEntity<Void> transferir(
            @RequestParam Long usuarioId,
            @RequestParam Long musicaId,
            @RequestParam Long playlistOrigemId,
            @RequestParam Long playlistDestinoId) {
        playlistService.transferirMusica(usuarioId, musicaId, playlistOrigemId, playlistDestinoId);
        return ResponseEntity.noContent().build();
    }
}