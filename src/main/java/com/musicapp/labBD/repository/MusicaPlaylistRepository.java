package com.musicapp.labBD.repository;

import com.musicapp.labBD.entity.Musica;
import com.musicapp.labBD.entity.MusicaPlaylist;
import com.musicapp.labBD.entity.MusicaPlaylistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicaPlaylistRepository extends JpaRepository<MusicaPlaylist, MusicaPlaylistId> {

    interface MusicaComOrdemDTO {
        String getTitulo();
        Integer getOrdemNaPlaylist();
    }

    // 2.1   2
    @Query("SELECT DISTINCT mp.musica FROM MusicaPlaylist mp WHERE mp.playlist.usuario.username = :username AND mp.musica.artista.nome = :nomeArtista")
    List<Musica> findMusicasByUsuarioAndArtista(@Param("username") String username, @Param("nomeArtista") String nomeArtista);

    // 2.3   8
    @Query("SELECT mp.musica.titulo AS titulo, mp.ordemNaPlaylist AS ordemNaPlaylist " +
            "FROM MusicaPlaylist mp WHERE mp.playlist.nome = :nomePlaylist " +
            "ORDER BY mp.ordemNaPlaylist ASC")
    List<MusicaComOrdemDTO> buscarMusicasComOrdemPorPlaylist(@Param("nomePlaylist") String nomePlaylist);

    // 2.3   9
    @Query("SELECT DISTINCT mp.playlist.usuario.username " +
            "FROM MusicaPlaylist mp " +
            "WHERE mp.musica.titulo = :tituloMusica")
    List<String> buscarUsernamesPorTituloMusica(@Param("tituloMusica") String tituloMusica);

}