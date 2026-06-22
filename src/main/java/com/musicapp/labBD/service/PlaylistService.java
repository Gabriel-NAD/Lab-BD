package com.musicapp.labBD.service;

import com.musicapp.labBD.dto.PlaylistRequest;
import com.musicapp.labBD.entity.*;
import com.musicapp.labBD.repository.MusicaRepository;
import com.musicapp.labBD.repository.PlaylistRepository;
import com.musicapp.labBD.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @Transactional
    public Playlist criar(PlaylistRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Playlist playlist = new Playlist(usuario, request.getNome());
        return playlistRepository.save(playlist);
    }


    public List<Playlist> listarTodas() {
        return playlistRepository.findAll();
    }

    @Transactional
    public Playlist adicionarMusica(Long playlistId, Long usuarioId, Long musicaId) {
        Playlist playlist = buscarPorId(playlistId, usuarioId);
        Musica musica = musicaRepository.findById(musicaId)
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));

        MusicaPlaylistId mpId = new MusicaPlaylistId(musicaId, playlistId, usuarioId);
        MusicaPlaylist mp = new MusicaPlaylist(mpId, musica, playlist, playlist.getMusicas().size() + 1);
        playlist.getMusicas().add(mp);
        return playlistRepository.save(playlist);
    }

    @Transactional
    public void removerMusica(Long playlistId, Long usuarioId, Long musicaId) {
        Playlist playlist = buscarPorId(playlistId, usuarioId);
        playlist.getMusicas().removeIf(mp -> mp.getId().getMusicaId().equals(musicaId));
        playlistRepository.save(playlist);
    }

    public Playlist buscarPorId(Long playlistId, Long usuarioId) {
        PlaylistId id = new PlaylistId(playlistId, usuarioId);

        return playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist não encontrada"));
    }

    public List<Playlist> listarPorUsuario(String username) {
        return playlistRepository.findByUsuarioUsername(username);
    }

    public List<PlaylistRepository.PlaylistMusicaCountDTO> contarMusicasPorPlaylist() {
        return playlistRepository.countMusicasByPlaylist();
    }

    @Transactional
    public void transferirMusica(Long usuarioId, Long musicaId, Long playlistOrigemId, Long playlistDestinoId) {
        removerMusica(playlistOrigemId, usuarioId, musicaId);
        adicionarMusica(playlistDestinoId, usuarioId, musicaId);
    }

}