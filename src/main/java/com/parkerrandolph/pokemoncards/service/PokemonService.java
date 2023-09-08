package com.parkerrandolph.pokemoncards.service;

import com.parkerrandolph.pokemoncards.models.Pokemon;
import com.parkerrandolph.pokemoncards.models.PokemonInfo;
import com.parkerrandolph.pokemoncards.repo.PokemonRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepo pokemonRepo;

    public ResponseEntity<PokemonInfo> getPokemon(String name){
            String url = "https://pokeapi.co/api/v2/pokemon/" + name;
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<PokemonInfo> pokemonInfo = restTemplate.getForEntity(url, PokemonInfo.class);
            
            Optional<Pokemon> pokemonExist = pokemonRepo.findByName(name);

            if(pokemonExist.isPresent()){
                Pokemon exists = pokemonExist.get();

                exists.setName(pokemonInfo.getBody().getName());
                exists.setWeight(pokemonInfo.getBody().getWeight());
                exists.setHeight(pokemonInfo.getBody().getHeight());
                exists.setBase_experience(pokemonInfo.getBody().getBase_experience());
                

            }else {
                Pokemon pokemon = new Pokemon();

                pokemon.setName(pokemonInfo.getBody().getName());
                pokemon.setWeight(pokemonInfo.getBody().getWeight());
                pokemon.setHeight(pokemonInfo.getBody().getHeight());
                pokemon.setBase_experience(pokemonInfo.getBody().getBase_experience());

                pokemonRepo.save(pokemon);
            }

            return pokemonInfo;
    }
}
