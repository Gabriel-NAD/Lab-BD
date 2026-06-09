package com.musicapp.labBD.repository;

import com.musicapp.labBD.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    @Query("SELECT a FROM Artista a WHERE a.id NOT IN (SELECT DISTINCT mp.musica.artista.id FROM MusicaPlaylist mp)")
    List<Artista> findArtistasSemMusicasEmPlaylists();
}