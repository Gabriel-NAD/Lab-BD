package com.musicapp.labBD.repository;

import com.musicapp.labBD.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    interface ArtistaRankingDTO {
        String getNomeArtista();
        Integer getTotalPlaylists();
        Integer getRanking();
    }

    // 2.1   4
    @Query("SELECT a FROM Artista a WHERE a.id NOT IN (SELECT DISTINCT mp.musica.artista.id FROM MusicaPlaylist mp)")
    List<Artista> findArtistasSemMusicasEmPlaylists();

    // 2.4   10
    @Query("SELECT a.nome AS nomeArtista, " +
            "COUNT(DISTINCT mp.playlist) AS totalPlaylists, " +
            "RANK() OVER(ORDER BY COUNT(DISTINCT mp.playlist) DESC) AS ranking " +
            "FROM Artista a " +
            "LEFT JOIN Musica m ON m.artista = a " +
            "LEFT JOIN MusicaPlaylist mp ON mp.musica = m " +
            "GROUP BY a.id, a.nome")
    List<ArtistaRankingDTO> buscarRankingDeArtistas();
}