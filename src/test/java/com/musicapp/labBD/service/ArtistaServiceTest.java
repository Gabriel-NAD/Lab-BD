package com.musicapp.labBD.service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musicapp.labBD.entity.Artista;
import com.musicapp.labBD.repository.ArtistaRepository;

@ExtendWith(MockitoExtension.class)
public class ArtistaServiceTest {

    @InjectMocks
    private ArtistaService service;

    @Mock
    private ArtistaRepository repository;

    @Test
    void deveBuscarArtistaPorId() {
        Artista artista = new Artista();
        artista.setId(1L);
        artista.setNome("Queen");

        when(repository.findById(1L)).thenReturn(Optional.of(artista));

        Artista resultado = service.buscarPorId(1L);

        assertThat(resultado.getNome()).isEqualTo("Queen");
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoArtistaNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void deveAtualizarArtista() {
        Artista existente = new Artista();
        existente.setId(1L);
        existente.setNome("Queen");
        existente.setNacionalidade("Britânica");

        Artista dadosAlterados = new Artista();
        dadosAlterados.setNome("Queen Updated");
        dadosAlterados.setNacionalidade("Inglesa");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any(Artista.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Artista resultado = service.atualizar(1L, dadosAlterados);

        assertThat(resultado.getNome()).isEqualTo("Queen Updated");
        assertThat(resultado.getNacionalidade()).isEqualTo("Inglesa");
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Artista.class));
    }

    @Test
    void deveDeletarArtista() {
        Artista artista = new Artista();
        artista.setId(1L);
        artista.setNome("Queen");

        when(repository.findById(1L)).thenReturn(Optional.of(artista));

        service.deletar(1L);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deveCriarArtista() {
        Artista artista = new Artista();
        artista.setNome("Queen");
        artista.setNacionalidade("Britânica");

        when(repository.save(any(Artista.class))).thenAnswer(invocation -> {
            Artista artistaEnviado = invocation.getArgument(0);
            artistaEnviado.setId(1L);
            return artistaEnviado;
        });

        Artista resultado = service.criar(artista);

        assertThat(resultado.getNome()).isEqualTo("Queen");
        assertThat(resultado.getNacionalidade()).isEqualTo("Britânica");
        verify(repository, times(1)).save(any(Artista.class));
    }
}