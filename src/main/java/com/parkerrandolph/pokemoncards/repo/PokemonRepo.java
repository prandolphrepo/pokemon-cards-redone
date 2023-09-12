package com.parkerrandolph.pokemoncards.repo;

import com.parkerrandolph.pokemoncards.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonRepo extends JpaRepository<Pokemon, Long>{
   Optional<Pokemon> findByName(String name);

}
