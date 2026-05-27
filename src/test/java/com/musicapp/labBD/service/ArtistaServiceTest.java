package com.musicapp.labBD.service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    }

    @Test
    void deveLancarExcecaoQuandoArtistaNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void deveAtualizarArtista() {
        Artista existente = new Artista();
        existente.setId(1L);
        existente.setNome("Queen");
        existente.setNacionalidade("Britânica");

        Artista dados = new Artista();
        dados.setNome("Queen Updated");
        dados.setNacionalidade("Inglesa");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any())).thenReturn(existente);

        Artista resultado = service.atualizar(1L, dados);

        assertThat(resultado.getNome()).isEqualTo("Queen Updated");
    }

    @Test
    void deveDeletarArtista() {
        Artista artista = new Artista();
        artista.setId(1L);
        artista.setNome("Queen");

        when(repository.findById(1L)).thenReturn(Optional.of(artista));

        service.deletar(1L);

        assertThrows(RuntimeException.class, () -> {
            when(repository.findById(1L)).thenReturn(Optional.empty());
            service.buscarPorId(1L);
        });
    }
}