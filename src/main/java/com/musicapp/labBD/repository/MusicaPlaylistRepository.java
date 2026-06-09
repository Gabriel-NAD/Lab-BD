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

    @Query("SELECT DISTINCT mp.musica FROM MusicaPlaylist mp WHERE mp.playlist.usuario.username = :username AND mp.musica.artista.nome = :nomeArtista")
    List<Musica> findMusicasByUsuarioAndArtista(@Param("username") String username, @Param("nomeArtista") String nomeArtista);

}