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

    @GetMapping("/{playlistId}/usuario/{usuarioId}")
    public ResponseEntity<Playlist> buscar(@PathVariable Long playlistId,
                                           @PathVariable Long usuarioId) {
        return ResponseEntity.ok(playlistService.buscarPorId(playlistId, usuarioId));
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> listarTodas() {
        return ResponseEntity.ok(playlistService.listarTodas());
    }

    @PutMapping("/{playlistId}/usuario/{usuarioId}")
    public ResponseEntity<Playlist> atualizar(@PathVariable Long playlistId,
                                              @PathVariable Long usuarioId,
                                              @RequestBody PlaylistRequest request) {
        return ResponseEntity.ok(playlistService.atualizar(playlistId, usuarioId, request));
    }

    @DeleteMapping("/{playlistId}/usuario/{usuarioId}")
    public ResponseEntity<Void> deletar(@PathVariable Long playlistId,
                                        @PathVariable Long usuarioId) {
        playlistService.deletar(playlistId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{username}")
    public ResponseEntity<List<Playlist>> playlistsPorUsuario(@PathVariable String username) {
        return ResponseEntity.ok(playlistService.listarPlaylistsPorUsuario(username));
    }

    @GetMapping("/contagem")
    public ResponseEntity<List<PlaylistRepository.PlaylistMusicaCountDTO>> contagemMusicasPorPlaylist() {
        return ResponseEntity.ok(playlistService.contarMusicasPorPlaylist());
    }

    @GetMapping("/tempo-total")
    public ResponseEntity<List<PlaylistRepository.PlaylistTempo>> obterTempoTotal() {
        return ResponseEntity.ok(playlistService.listarTempoTotalPlaylists());
    }
}