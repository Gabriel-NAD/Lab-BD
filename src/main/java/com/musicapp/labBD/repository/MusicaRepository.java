package com.musicapp.labBD.repository;

import com.musicapp.labBD.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

    // 2.2  5
    @Query("SELECT m FROM Musica m JOIN FETCH m.artista WHERE m.id = :id")
    Optional<Musica> findByIdWithArtista(Long id);

    // 2.2  7
    @Query("SELECT m FROM Musica m WHERE m.duracaoSegundos < " +
            "(SELECT AVG(sub.duracaoSegundos) FROM Musica sub WHERE sub.artista.id = m.artista.id)")
    List<Musica> findMusicasMaisCurtasQueAMediaDoArtista();


    // 2.4  11
    @Query("SELECT m FROM Musica m " +
            "WHERE m.artista.nome = :artistaPrincipal " +
            "AND m.duracaoSegundos > " +
            "(SELECT MAX(m2.duracaoSegundos) FROM Musica m2 WHERE m2.artista.nome = :artistaComparacao)")
    List<Musica> buscarMaioresQueMaximoOutroArtista(
            @Param("artistaPrincipal") String artistaPrincipal,
            @Param("artistaComparacao") String artistaComparacao);
}