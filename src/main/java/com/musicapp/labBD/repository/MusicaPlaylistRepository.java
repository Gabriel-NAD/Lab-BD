package com.musicapp.labBD.repository;

import com.musicapp.labBD.entity.MusicaPlaylist;
import com.musicapp.labBD.entity.MusicaPlaylistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaPlaylistRepository extends JpaRepository<MusicaPlaylist, MusicaPlaylistId> {
}