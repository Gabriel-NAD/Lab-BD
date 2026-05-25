package com.musicapp.labBD.repository;

import com.musicapp.labBD.entity.Musica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
}

