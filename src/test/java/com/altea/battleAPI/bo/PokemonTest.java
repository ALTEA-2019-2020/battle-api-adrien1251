package com.altea.battleAPI.bo;

import com.altea.battleAPI.pokemonTypes.bo.PokemonType;
import com.altea.battleAPI.pokemonTypes.bo.Stats;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PokemonTest {
    private Pokemon createPokemon(int level, int attack, int defense, int speed, int hp, int maxHp) {
        return Pokemon.builder()
                .level(level)
                .attack(attack)
                .defense(defense)
                .speed(speed)
                .hp(hp)
                .maxHp(maxHp)
                .build();
    }

    @Test
    void testAttackMinusHp() {
        Pokemon pokemonAttacker = createPokemon(50, 10, 20, 30, 40, 40);
        Pokemon pokemonDefender = createPokemon(50, 10, 20, 30, 40, 40);

        pokemonAttacker.attack(pokemonDefender);

        assertTrue(pokemonDefender.getHp() < pokemonDefender.getMaxHp());
    }

    @Test
    void testAttackDefenderKOAndHp0() {
        Pokemon pokemonAttacker = createPokemon(50, 10, 20, 30, 40, 40);
        Pokemon pokemonDefender = createPokemon(50, 10, 20, 30, 1, 40);

        pokemonAttacker.attack(pokemonDefender);

        assertTrue(pokemonDefender.isKo());
        assertEquals(pokemonDefender.getHp(), 0);
    }

    @Test
    void testHealMax() {
        Pokemon pokemonAttacker = createPokemon(50, 10, 20, 30, 40, 40);
        pokemonAttacker.heal();
        assertEquals(pokemonAttacker.getHp(), 40);
    }

    @Test
    void testHealMax2() {
        Pokemon pokemonAttacker = createPokemon(50, 10, 20, 30, 39, 40);
        pokemonAttacker.heal();
        assertEquals(pokemonAttacker.getHp(), 40);
    }

    @Test
    void testHealMax3() {
        Pokemon pokemonAttacker = createPokemon(50, 10, 20, 30, 1, 40);
        pokemonAttacker.heal();
        assertTrue(pokemonAttacker.getHp() > 1);
    }
}
