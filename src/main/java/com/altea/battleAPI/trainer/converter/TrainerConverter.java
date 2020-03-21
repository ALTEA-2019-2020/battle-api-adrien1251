package com.altea.battleAPI.trainer.converter;

import com.altea.battleAPI.bo.Pokemon;
import com.altea.battleAPI.bo.TrainerWithPokemons;
import com.altea.battleAPI.pokemonTypes.bo.PokemonType;
import com.altea.battleAPI.pokemonTypes.service.PokemonTypeService;
import com.altea.battleAPI.service.factory.PokemonFactory;
import com.altea.battleAPI.trainer.bo.ShortPokemon;
import com.altea.battleAPI.trainer.bo.Trainer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainerConverter {
    private final PokemonTypeService pokemonTypeService;

    private final PokemonFactory pokemonFactory;

    public TrainerConverter(PokemonTypeService pokemonTypeService, PokemonFactory pokemonFactory) {
        this.pokemonTypeService = pokemonTypeService;
        this.pokemonFactory = pokemonFactory;
    }

    public TrainerWithPokemons trainerToTrainerWithPokemons(Trainer trainer) {
        List<Integer> ids = trainer.getTeam().stream().map(ShortPokemon::getPokemonTypeId).collect(Collectors.toList());

        List<PokemonType> pokemonTypes = pokemonTypeService.listPokemonsTypes(ids);

        List<Pokemon> pokemons = new ArrayList<>();

        trainer.getTeam().forEach(pokemon -> {
            pokemons.add(pokemonFactory.createPokemon(
                    pokemon.getLevel(),
                    pokemonTypes.stream()
                            .filter(pokemonType -> pokemon.getPokemonTypeId() == pokemonType.getId())
                            .findAny().get())
            );
        });

        return TrainerWithPokemons.builder()
                .trainer(trainer)
                .name(trainer.getName())
                .team(pokemons)
                .build();
    }
}
