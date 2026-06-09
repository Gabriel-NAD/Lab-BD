package com.musicapp.labBD.controller;

import com.musicapp.labBD.dto.MusicaRequest;
import com.musicapp.labBD.entity.Musica;
import com.musicapp.labBD.service.MusicaService;
import com.musicapp.labBD.service.MusicaPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaService service;

    @Autowired
    private MusicaPlaylistService musicaPlaylistService;

    @PostMapping
    public ResponseEntity<Musica> criar(@RequestBody MusicaRequest request) {
        return ResponseEntity.status(201).body(service.criar(request));
    }

    @GetMapping("/mais-curtas-que-media")
    public ResponseEntity<List<Musica>> buscarMaisCurtasQueMedia() {
        return ResponseEntity.ok(service.listarMusicasMaisCurtasQueMedia());
    }

    @GetMapping("/busca-avancada")
    public ResponseEntity<List<Musica>> buscarMusicasPorUsuarioEArtista(
            @RequestParam String username,
            @RequestParam String nomeArtista) {
        return ResponseEntity.ok(musicaPlaylistService.listarMusicasPorUsuarioEArtista(username, nomeArtista));
    }

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<Musica> buscarComDetalhes(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorIdOtimizado(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Musica>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(@PathVariable Long id, @RequestBody Musica musica) {
        return ResponseEntity.ok(service.atualizar(id, musica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}