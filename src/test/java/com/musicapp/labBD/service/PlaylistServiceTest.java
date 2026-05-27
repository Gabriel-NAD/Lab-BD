package com.musicapp.labBD.service;

import com.musicapp.labBD.dto.PlaylistRequest;
import com.musicapp.labBD.entity.Playlist;
import com.musicapp.labBD.entity.PlaylistId;
import com.musicapp.labBD.entity.Usuario;
import com.musicapp.labBD.repository.PlaylistRepository;
import com.musicapp.labBD.repository.UsuarioRepository;
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
public class PlaylistServiceTest {

    @InjectMocks
    private PlaylistService service;

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void deveCriarPlaylist() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("Pablo");

        PlaylistRequest request = new PlaylistRequest();
        request.setNome("Rock do Pablo");
        request.setUsuarioId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(playlistRepository.count()).thenReturn(0L);
        when(playlistRepository.save(any(Playlist.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Playlist resultado = service.criar(request);

        assertThat(resultado.getNome()).isEqualTo("Rock do Pablo");
        assertThat(resultado.getUsuario().getUsername()).isEqualTo("Pablo");
        verify(playlistRepository, times(1)).save(any(Playlist.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        PlaylistRequest request = new PlaylistRequest();
        request.setUsuarioId(99L);

        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.criar(request));
        verify(playlistRepository, never()).save(any());
    }

    @Test
    void deveBuscarPlaylistPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        PlaylistId id = new PlaylistId(1L, 1L);
        Playlist playlist = new Playlist();
        playlist.setId(id);
        playlist.setNome("Rock do Pablo");
        playlist.setUsuario(usuario);

        when(playlistRepository.findById(id)).thenReturn(Optional.of(playlist));

        Playlist resultado = service.buscarPorId(1L, 1L);

        assertThat(resultado.getNome()).isEqualTo("Rock do Pablo");
        verify(playlistRepository, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoPlaylistNaoExiste() {
        PlaylistId id = new PlaylistId(99L, 99L);
        when(playlistRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.buscarPorId(99L, 99L));
    }

    @Test
    void deveAtualizarPlaylist() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        PlaylistId id = new PlaylistId(1L, 1L);
        Playlist existente = new Playlist();
        existente.setId(id);
        existente.setNome("Rock do Pablo");
        existente.setUsuario(usuario);

        PlaylistRequest request = new PlaylistRequest();
        request.setNome("Rock do Pablo Atualizado");

        when(playlistRepository.findById(id)).thenReturn(Optional.of(existente));
        when(playlistRepository.save(any(Playlist.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Playlist resultado = service.atualizar(1L, 1L, request);

        assertThat(resultado.getNome()).isEqualTo("Rock do Pablo Atualizado");
        verify(playlistRepository, times(1)).save(any(Playlist.class));
    }

    @Test
    void deveDeletarPlaylist() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        PlaylistId id = new PlaylistId(1L, 1L);
        Playlist playlist = new Playlist();
        playlist.setId(id);
        playlist.setUsuario(usuario);

        when(playlistRepository.findById(id)).thenReturn(Optional.of(playlist));

        service.deletar(1L, 1L);

        verify(playlistRepository, times(1)).delete(playlist);
    }
}