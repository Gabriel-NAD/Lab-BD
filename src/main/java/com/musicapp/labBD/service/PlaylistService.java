package com.musicapp.labBD.service;

import com.musicapp.labBD.dto.PlaylistRequest;
import com.musicapp.labBD.entity.Playlist;
import com.musicapp.labBD.entity.PlaylistId;
import com.musicapp.labBD.entity.Usuario;
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

    @Transactional
    public Playlist criar(PlaylistRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Playlist playlist = new Playlist(usuario, request.getNome());
        return playlistRepository.save(playlist);
    }

    public Playlist buscarPorId(Long playlistId, Long usuarioId) {
        PlaylistId id = new PlaylistId(playlistId, usuarioId);
        return playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist não encontrada"));
    }

    public List<Playlist> listarTodas() {
        return playlistRepository.findAll();
    }

    public Playlist atualizar(Long playlistId, Long usuarioId, PlaylistRequest request) {
        Playlist playlist = buscarPorId(playlistId, usuarioId);
        playlist.setNome(request.getNome());
        return playlistRepository.save(playlist);
    }

    public void deletar(Long playlistId, Long usuarioId) {
        Playlist playlist = buscarPorId(playlistId, usuarioId);
        playlistRepository.delete(playlist);
    }

    public List<Playlist> listarPlaylistsPorUsuario(String username) {
        return playlistRepository.findByUsuarioUsername(username);
    }

    public List<PlaylistRepository.PlaylistMusicaCountDTO> contarMusicasPorPlaylist() {
        return playlistRepository.countMusicasByPlaylist();
    }

    public List<PlaylistRepository.PlaylistTempo> listarTempoTotalPlaylists() {
        return playlistRepository.findTempoTotalPlaylists();
    }
}