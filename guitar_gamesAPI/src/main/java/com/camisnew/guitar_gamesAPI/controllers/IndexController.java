package com.camisnew.guitar_gamesAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "Bem-vind@ a central de jogos musicais!";
    }

}
