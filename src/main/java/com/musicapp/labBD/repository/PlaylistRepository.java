package com.musicapp.labBD.repository;

import com.musicapp.labBD.entity.Playlist;
import com.musicapp.labBD.entity.PlaylistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, PlaylistId> {

    interface PlaylistMusicaCountDTO {
        String getNome();
        Long getTotal();
    }

    interface PlaylistTempo {
        String getNomePlaylist();
        String getUsernameDono();
        Long getTempoTotalSegundos();
    }

    // 2.1   1
    @Query("SELECT p FROM Playlist p WHERE p.usuario.username = :username")
    List<Playlist> findByUsuarioUsername(@Param("username") String username);

    // 2.1   3
    @Query("SELECT p.nome AS nome, COUNT(mp) AS total FROM Playlist p LEFT JOIN p.musicas mp GROUP BY p.playlistId, p.usuario, p.nome ORDER BY COUNT(mp) DESC")
    List<PlaylistMusicaCountDTO> countMusicasByPlaylist();

    // 2.2   6
    @Query("SELECT p.nome as nomePlaylist, u.username as usernameDono, COALESCE(SUM(m.duracaoSegundos), 0) as tempoTotalSegundos " +
            "FROM Playlist p " +
            "JOIN p.usuario u " +
            "LEFT JOIN p.musicas mp " +
            "LEFT JOIN mp.musica m " +
            "GROUP BY p.playlistId, p.nome, u.username")
    List<PlaylistTempo> findTempoTotalPlaylists();
}