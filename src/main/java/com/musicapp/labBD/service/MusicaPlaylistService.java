package com.musicapp.labBD.service;

import com.musicapp.labBD.dto.MusicaPlaylistRequest;
import com.musicapp.labBD.entity.*;
import com.musicapp.labBD.repository.MusicaPlaylistRepository;
import com.musicapp.labBD.repository.MusicaRepository;
import com.musicapp.labBD.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaPlaylistService {

    @Autowired
    private MusicaPlaylistRepository musicaPlaylistRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    public MusicaPlaylist adicionarMusica(MusicaPlaylistRequest request) {
        Musica musica = musicaRepository.findById(request.getMusicaId())
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));

        PlaylistId playlistId = new PlaylistId(request.getPlaylistId(), request.getUsuarioId());
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist não encontrada"));

        MusicaPlaylistId id = new MusicaPlaylistId(
                musica.getId(),
                playlist.getPlaylistId(),
                playlist.getUsuarioId()
        );

        MusicaPlaylist musicaPlaylist = new MusicaPlaylist();
        musicaPlaylist.setId(id);
        musicaPlaylist.setMusica(musica);
        musicaPlaylist.setPlaylist(playlist);
        musicaPlaylist.setOrdemNaPlaylist(request.getOrdemNaPlaylist());

        return musicaPlaylistRepository.save(musicaPlaylist);
    }

    public void removerMusica(Long musicaId, Long playlistId, Long usuarioId) {
        MusicaPlaylistId id = new MusicaPlaylistId(musicaId, playlistId, usuarioId);
        if (!musicaPlaylistRepository.existsById(id)) {
            throw new RuntimeException("Música não está nesta playlist");
        }
        musicaPlaylistRepository.deleteById(id);
    }

    public List<Musica> listarMusicasPorUsuarioEArtista(String username, String nomeArtista) {
        return musicaPlaylistRepository.findMusicasByUsuarioAndArtista(username, nomeArtista);
    }
}