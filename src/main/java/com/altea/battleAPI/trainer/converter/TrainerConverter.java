package com.altea.battleAPI.trainer.converter;

import com.altea.battleAPI.bo.PokemonWithLvl;
import com.altea.battleAPI.bo.TrainerWithPokemons;
import com.altea.battleAPI.pokemonTypes.bo.PokemonType;
import com.altea.battleAPI.pokemonTypes.service.PokemonTypeService;
import com.altea.battleAPI.trainer.bo.Pokemon;
import com.altea.battleAPI.trainer.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainerConverter {
    @Autowired
    private PokemonTypeService pokemonTypeService;

    public TrainerWithPokemons trainerToTrainerWithPokemons(Trainer trainer) {
        List<Integer> ids = trainer.getTeam().stream().map(Pokemon::getPokemonTypeId).collect(Collectors.toList());

        List<PokemonType> pokemonTypes = pokemonTypeService.listPokemonsTypes(ids);

        List<PokemonWithLvl> pokemonWithLvls = new ArrayList<>();

        trainer.getTeam().stream().forEach(pokemon -> {
            pokemonWithLvls.add(
                    new PokemonWithLvl(

                            pokemon.getLevel(),
                            pokemonTypes.stream()
                                    .filter(pokemonType -> pokemon.getPokemonTypeId() == pokemonType.getId())
                                    .findAny().get()
                    )
            );
        });

        return TrainerWithPokemons.builder()
                .trainer(trainer)
                .name(trainer.getName())
                .team(pokemonWithLvls)
                .build();
    }
}
