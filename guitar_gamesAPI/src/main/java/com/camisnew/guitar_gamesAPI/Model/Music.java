package com.camisnew.guitar_gamesAPI.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@NoArgsConstructor
@Getter @Setter
@Table(name = "musics")
@Entity
@JsonIgnoreProperties({"game"})
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_id")
    private Long id;

    @Column(name = "music_title")
    @Autowired
    private String title;

    @Column(name = "artist")
    @Autowired
    private String artist;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game", nullable = false)
    private Game game;

    public Music(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
