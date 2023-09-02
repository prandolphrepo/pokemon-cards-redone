package com.parkerrandolph.pokemoncards.service;

import com.parkerrandolph.pokemoncards.models.Pokemon;
import com.parkerrandolph.pokemoncards.models.PokemonInfo;
import com.parkerrandolph.pokemoncards.repo.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepo pokemonRepo;

    public PokemonInfo getPokemon(String name){
            String url = "https://pokeapi.co/api/v2/pokemon/" + name;
            RestTemplate restTemplate = new RestTemplate();

            PokemonInfo pokemonInfo = restTemplate.getForObject(url, PokemonInfo.class);

            Optional<Pokemon> pokemonExist = pokemonRepo.findByName(name);

            if(pokemonExist.isPresent()){
                Pokemon exists = pokemonExist.get();

                exists.setName(pokemonInfo.getName());
                exists.setWeight(pokemonInfo.getWeight());
                exists.setHeight(pokemonInfo.getHeight());
                exists.setBase_experience(pokemonInfo.getBase_experience());

            }else {
                Pokemon pokemon = new Pokemon();

                pokemon.setName(pokemonInfo.getName());
                pokemon.setWeight(pokemonInfo.getWeight());
                pokemon.setHeight(pokemonInfo.getHeight());
                pokemon.setBase_experience(pokemonInfo.getBase_experience());

                pokemonRepo.save(pokemon);
            }

            return pokemonInfo;
    }
}
