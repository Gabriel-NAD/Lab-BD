package com.musicapp.labBD.repository;

import com.musicapp.labBD.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

    @Query("SELECT m FROM Musica m JOIN FETCH m.artista WHERE m.id = :id")
    Optional<Musica> findByIdWithArtista(Long id);

    @Query("SELECT m FROM Musica m WHERE m.duracaoSegundos < " +
            "(SELECT AVG(sub.duracaoSegundos) FROM Musica sub WHERE sub.artista.id = m.artista.id)")
    List<Musica> findMusicasMaisCurtasQueAMediaDoArtista();
}