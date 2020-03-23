package com.altea.battleAPI.service.factory;

import com.altea.battleAPI.bo.Pokemon;
import com.altea.battleAPI.pokemonTypes.bo.PokemonType;
import com.altea.battleAPI.pokemonTypes.bo.Stats;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PokemonFactoryTest {
    @Autowired
    private PokemonFactory pokemonFactory;

    @Test
    void testStatPickachuLvl18() {
        Stats stats = Stats.builder()
                .attack(55)
                .defense(40)
                .speed(90)
                .hp(35)
                .build();

        PokemonType pickachu = PokemonType.builder()
                .stats(stats)
                .id(25)
                .build();

        Pokemon pokemonInCombat = pokemonFactory.createPokemon(18, pickachu);
        assertEquals(24, pokemonInCombat.getAttack());
        assertEquals(19, pokemonInCombat.getDefense());
        assertEquals(37, pokemonInCombat.getSpeed());
        assertEquals(40, pokemonInCombat.getHp());
        assertEquals(pokemonInCombat.getHp(), pokemonInCombat.getMaxHp());
    }

    @Test
    void testStatStaryu() {
        Stats stats = Stats.builder()
                .attack(45)
                .defense(55)
                .speed(85)
                .hp(30)
                .build();

        PokemonType staryu = PokemonType.builder()
                .stats(stats)
                .id(25)
                .build();

        Pokemon pokemonInCombat = pokemonFactory.createPokemon(18, staryu);
        assertEquals(21, pokemonInCombat.getAttack());
        assertEquals(24, pokemonInCombat.getDefense());
        assertEquals(35, pokemonInCombat.getSpeed());
        assertEquals(38, pokemonInCombat.getHp());
        assertEquals(pokemonInCombat.getHp(), pokemonInCombat.getMaxHp());
    }
}
