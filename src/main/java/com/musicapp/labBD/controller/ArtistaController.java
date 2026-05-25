package com.musicapp.labBD.controller;

import com.musicapp.labBD.service.ArtistaService;
import com.musicapp.labBD.entity.Artista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")

public class ArtistaController {

    @Autowired
    private ArtistaService service;

    @PostMapping
    public ResponseEntity<Artista> criar(@RequestBody Artista artista) {
        return ResponseEntity.status(201).body(service.criar(artista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Artista>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> atualizar(@PathVariable Long id, @RequestBody Artista artista) {
        return ResponseEntity.ok(service.atualizar(id, artista));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}