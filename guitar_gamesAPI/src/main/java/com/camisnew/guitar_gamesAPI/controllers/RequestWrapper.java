package com.camisnew.guitar_gamesAPI.controllers;

import com.camisnew.guitar_gamesAPI.Model.Game;
import com.camisnew.guitar_gamesAPI.Model.Music;
import lombok.*;

import java.util.Set;

@Getter @Setter
@ToString
public class RequestWrapper {

    private Game newGame;
    private Set<Music> songlist;

}
