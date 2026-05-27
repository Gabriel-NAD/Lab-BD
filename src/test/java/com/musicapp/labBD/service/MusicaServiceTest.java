package com.musicapp.labBD.service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musicapp.labBD.dto.MusicaRequest;
import com.musicapp.labBD.entity.Artista;
import com.musicapp.labBD.entity.Musica;
import com.musicapp.labBD.repository.ArtistaRepository;
import com.musicapp.labBD.repository.MusicaRepository;

@ExtendWith(MockitoExtension.class)
public class MusicaServiceTest {

    @InjectMocks
    private MusicaService service;

    @Mock
    private MusicaRepository musicaRepository;

    @Mock
    private ArtistaRepository artistaRepository;

    @Test
    void deveCriarMusica() {
        Artista artista = new Artista();
        artista.setId(1L);
        artista.setNome("Queen");

        MusicaRequest request = new MusicaRequest();
        request.setTitulo("Bohemian Rhapsody");
        request.setDuracaoSegundos(354);
        request.setArtistaId(1L);

        when(artistaRepository.findById(1L)).thenReturn(Optional.of(artista));
        when(musicaRepository.save(any(Musica.class))).thenAnswer(invocation -> {
            Musica musicaEnviada = invocation.getArgument(0);
            musicaEnviada.setId(1L);
            return musicaEnviada;
        });

        Musica resultado = service.criar(request);

        assertThat(resultado.getTitulo()).isEqualTo("Bohemian Rhapsody");
        assertThat(resultado.getDuracaoSegundos()).isEqualTo(354);
        assertThat(resultado.getArtista().getNome()).isEqualTo("Queen");
        verify(musicaRepository, times(1)).save(any(Musica.class));
    }

    @Test
    void deveLancarExcecaoQuandoArtistaNaoExiste() {
        MusicaRequest request = new MusicaRequest();
        request.setArtistaId(99L);

        when(artistaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.criar(request));
        verify(musicaRepository, never()).save(any());
    }

    @Test
    void deveBuscarMusicaPorId() {
        Musica musica = new Musica();
        musica.setId(1L);
        musica.setTitulo("Bohemian Rhapsody");

        when(musicaRepository.findById(1L)).thenReturn(Optional.of(musica));

        Musica resultado = service.buscarPorId(1L);

        assertThat(resultado.getTitulo()).isEqualTo("Bohemian Rhapsody");
        verify(musicaRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoMusicaNaoExiste() {
        when(musicaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void deveAtualizarMusica() {
        Artista artista = new Artista();
        artista.setId(1L);

        Musica existente = new Musica();
        existente.setId(1L);
        existente.setTitulo("Bohemian Rhapsody");
        existente.setDuracaoSegundos(354);
        existente.setArtista(artista);

        Musica dados = new Musica();
        dados.setTitulo("Bohemian Rhapsody - Remaster");
        dados.setDuracaoSegundos(360);

        when(musicaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(musicaRepository.save(any(Musica.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Musica resultado = service.atualizar(1L, dados);

        assertThat(resultado.getTitulo()).isEqualTo("Bohemian Rhapsody - Remaster");
        assertThat(resultado.getDuracaoSegundos()).isEqualTo(360);
        verify(musicaRepository, times(1)).save(any(Musica.class));
    }
}