package dev.pokemonracer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.pokemonracer.dto.RaceDTO;

@RestController
@RequestMapping("/api/race")
public class RaceController {

    @GetMapping("")
    public RaceDTO getRaces(@RequestParam("player") String auth0Id, @RequestParam String status) {
        return null;
    }

}
