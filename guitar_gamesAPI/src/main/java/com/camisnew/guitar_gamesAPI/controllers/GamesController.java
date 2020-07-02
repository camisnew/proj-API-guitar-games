package com.camisnew.guitar_gamesAPI.controllers;

import com.camisnew.guitar_gamesAPI.Model.Game;
import com.camisnew.guitar_gamesAPI.Model.Music;
import com.camisnew.guitar_gamesAPI.repositories.GameRepository;
import com.camisnew.guitar_gamesAPI.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/games")
@RestController
public class GamesController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private MusicRepository musicRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Game> registerNewGame(@RequestBody RequestWrapper requestWrapper) {
        Set<Music> songs = new HashSet<>();
        requestWrapper.getSonglist().forEach(song -> songs.add(new Music(song.getTitle(), song.getArtist())));
        Game newGame = new Game(requestWrapper.getNewGame().getTitle(), requestWrapper.getNewGame().getRelease(), requestWrapper.getNewGame().getConsole());
        songs.forEach(newGame::addMusic);
        songs.forEach(musicRepository::save);
        return new ResponseEntity<Game>(
                this.gameRepository.save(newGame),
                new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Game>> allGames() {
        return new ResponseEntity<List<Game>> (
                (List<Game>) this.gameRepository.findAll(),
                new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> gamesById(@PathVariable ("id") Long id) {
        if(this.gameRepository.findById(id).isPresent()) {
            return new ResponseEntity<Game>(
                    this.gameRepository.findById(id).get(),
                    new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//
//    UPDATE NÃO FUNCIONAAA
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Game> update(@PathVariable("id") Long id,
//                                       @RequestBody Game game) throws Exception {
//        if (id == 0 || !this.gameRepository.existsById(id)) {
//            throw new Exception("id não encontrado ou inexistente!");
//        }
//        return new ResponseEntity<Game>(
//                this.gameRepository.save(game),
//                new HttpHeaders(), HttpStatus.OK);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Game> delete(@PathVariable("id") Long id) {
        this.gameRepository.deleteById(id);
        return new ResponseEntity<Game>(new HttpHeaders(), HttpStatus.OK);
    }

}
