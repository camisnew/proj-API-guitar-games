package com.camisnew.guitar_gamesAPI.repositories;

import com.camisnew.guitar_gamesAPI.Model.Game;
import com.camisnew.guitar_gamesAPI.Model.Music;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends CrudRepository<Music, Long> {
    List<Music> findByGame(Game game);
}
