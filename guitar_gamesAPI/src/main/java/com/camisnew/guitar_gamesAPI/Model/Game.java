package com.camisnew.guitar_gamesAPI.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Table(name = "games")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long game_id;

    @Column(name = "game_title")
    @Autowired
    private String title;

    @Column(name = "release")
    @Autowired
    private int release;

    @Column(name = "console")
    @Autowired
    private String console;

    @OneToMany(targetEntity = Music.class, mappedBy = "game", cascade = CascadeType.ALL)
    private Set<Music> musicList;

    public Game(String title, int release, String console) {
        this.title = title;
        this.release = release;
        this.console = console;
        this.musicList = new HashSet<>();
    }

    public void addMusic(Music newMusic) {
        this.musicList.add(newMusic);
        newMusic.setGame(this);
    }

    @Override
    public String toString() {
        return "Game{" +
                "game_id=" + game_id +
                ", title='" + title + '\'' +
                ", release=" + release +
                ", console='" + console + '\'' +
                ", musicList=" + musicList +
                '}';
    }
}
