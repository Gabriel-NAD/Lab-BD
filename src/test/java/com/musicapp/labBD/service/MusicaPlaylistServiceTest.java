package com.musicapp.labBD.service;

import com.musicapp.labBD.dto.MusicaPlaylistRequest;
import com.musicapp.labBD.entity.*;
import com.musicapp.labBD.repository.MusicaPlaylistRepository;
import com.musicapp.labBD.repository.MusicaRepository;
import com.musicapp.labBD.repository.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MusicaPlaylistServiceTest {

    @InjectMocks
    private MusicaPlaylistService service;

    @Mock
    private MusicaPlaylistRepository musicaPlaylistRepository;

    @Mock
    private MusicaRepository musicaRepository;

    @Mock
    private PlaylistRepository playlistRepository;

    @Test
    void deveAdicionarMusicaNaPlaylist() {
        Artista artista = new Artista();
        artista.setId(1L);

        Musica musica = new Musica();
        musica.setId(1L);
        musica.setTitulo("Bohemian Rhapsody");
        musica.setArtista(artista);

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        PlaylistId playlistId = new PlaylistId(1L, 1L);
        Playlist playlist = new Playlist();
        playlist.setId(playlistId);
        playlist.setNome("Rock do Pablo");
        playlist.setUsuario(usuario);

        MusicaPlaylistRequest request = new MusicaPlaylistRequest();
        request.setMusicaId(1L);
        request.setPlaylistId(1L);
        request.setUsuarioId(1L);
        request.setOrdemNaPlaylist(1);

        when(musicaRepository.findById(1L)).thenReturn(Optional.of(musica));
        when(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlist));
        when(musicaPlaylistRepository.save(any(MusicaPlaylist.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        MusicaPlaylist resultado = service.adicionarMusica(request);

        assertThat(resultado.getMusica().getTitulo()).isEqualTo("Bohemian Rhapsody");
        assertThat(resultado.getOrdemNaPlaylist()).isEqualTo(1);
        verify(musicaPlaylistRepository, times(1)).save(any(MusicaPlaylist.class));
    }

    @Test
    void deveLancarExcecaoQuandoMusicaNaoExisteAoAdicionar() {
        MusicaPlaylistRequest request = new MusicaPlaylistRequest();
        request.setMusicaId(99L);

        when(musicaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.adicionarMusica(request));
        verify(musicaPlaylistRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoPlaylistNaoExisteAoAdicionar() {
        Musica musica = new Musica();
        musica.setId(1L);

        MusicaPlaylistRequest request = new MusicaPlaylistRequest();
        request.setMusicaId(1L);
        request.setPlaylistId(99L);
        request.setUsuarioId(99L);

        PlaylistId playlistId = new PlaylistId(99L, 99L);

        when(musicaRepository.findById(1L)).thenReturn(Optional.of(musica));
        when(playlistRepository.findById(playlistId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.adicionarMusica(request));
        verify(musicaPlaylistRepository, never()).save(any());
    }

    @Test
    void deveRemoverMusicaDaPlaylist() {
        MusicaPlaylistId id = new MusicaPlaylistId(1L, 1L, 1L);

        when(musicaPlaylistRepository.existsById(id)).thenReturn(true);

        service.removerMusica(1L, 1L, 1L);

        verify(musicaPlaylistRepository, times(1)).deleteById(id);
    }

    @Test
    void deveLancarExcecaoAoRemoverMusicaQueNaoEstaNaPlaylist() {
        MusicaPlaylistId id = new MusicaPlaylistId(1L, 1L, 1L);

        when(musicaPlaylistRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> service.removerMusica(1L, 1L, 1L));
        verify(musicaPlaylistRepository, never()).deleteById(any());
    }
}