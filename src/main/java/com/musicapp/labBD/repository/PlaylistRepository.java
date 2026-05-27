package com.musicapp.labBD.repository;

import com.musicapp.labBD.entity.Playlist;
import com.musicapp.labBD.entity.PlaylistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, PlaylistId> {
}