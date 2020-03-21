package com.altea.battleAPI.service.factory;

import com.altea.battleAPI.bo.Pokemon;
import com.altea.battleAPI.pokemonTypes.bo.PokemonType;
import org.springframework.stereotype.Service;

@Service
public class PokemonFactory {
    public Pokemon createPokemon(int level, PokemonType pokemonType) {
        return Pokemon.builder()
                .level(level)
                .type(pokemonType)
                .id(pokemonType.getId())
                .attack(getStat(pokemonType.getStats().getAttack(), level))
                .defense(getStat(pokemonType.getStats().getDefense(), level))
                .speed(getStat(pokemonType.getStats().getSpeed(), level))
                .hp(getHp(pokemonType.getStats().getHp(), level))
                .maxHp(getHp(pokemonType.getStats().getHp(), level))
                .build();
    }

    private int getStat(int stat, int level) {
        return (int) (5.0 + (double) stat * ((double) level / 50.0));
    }

    private int getHp(int stat, int level) {
        return (int) (10.0 + (double) level + (double) stat * ((double) level / 50.0));
    }
}
