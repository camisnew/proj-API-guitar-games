package com.camisnew.guitar_gamesAPI.repositories;

import com.camisnew.guitar_gamesAPI.Model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
}
