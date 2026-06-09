package com.musicapp.labBD.service;

import com.musicapp.labBD.dto.MusicaRequest;
import com.musicapp.labBD.entity.Artista;
import com.musicapp.labBD.entity.Musica;
import com.musicapp.labBD.repository.ArtistaRepository;
import com.musicapp.labBD.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    public Musica criar(MusicaRequest request) {
        Artista artista = artistaRepository.findById(request.getArtistaId())
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));

        Musica musica = new Musica();
        musica.setTitulo(request.getTitulo());
        musica.setDuracaoSegundos(request.getDuracaoSegundos());
        musica.setArtista(artista);

        return musicaRepository.save(musica);
    }

    public Musica buscarPorId(Long id) {
        return musicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));
    }

    public List<Musica> listarTodas() {
        return musicaRepository.findAll();
    }

    public Musica atualizar(Long id, Musica dados) {
        Musica musica = buscarPorId(id);
        musica.setTitulo(dados.getTitulo());
        musica.setDuracaoSegundos(dados.getDuracaoSegundos());
        return musicaRepository.save(musica);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        musicaRepository.deleteById(id);
    }

    public List<Musica> listarMusicasMaisCurtasQueMedia() {
        return musicaRepository.findMusicasMaisCurtasQueAMediaDoArtista();
    }

    public Musica buscarPorIdOtimizado(Long id) {
        return musicaRepository.findByIdWithArtista(id)
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));
    }
}

