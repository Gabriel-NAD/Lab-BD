package com.musicapp.labBD.service;

import com.musicapp.labBD.dto.ArtistaRequest;
import com.musicapp.labBD.entity.Artista;
import com.musicapp.labBD.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository repository;

    public Artista criar(ArtistaRequest request) {
        Artista artista = new Artista();
        artista.setNome(request.getNome());
        artista.setNacionalidade(request.getNacionalidade());
        return repository.save(artista);
    }

    public Artista buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));
    }

    public List<Artista> listarTodos() {
        return repository.findAll();
    }

    public Artista atualizar(Long id, ArtistaRequest request) {
        Artista artista = buscarPorId(id);
        artista.setNome(request.getNome());
        artista.setNacionalidade(request.getNacionalidade());
        return repository.save(artista);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public List<Artista> listarArtistasSemMusicasEmPlaylists() {
        return repository.findArtistasSemMusicasEmPlaylists();
    }
}