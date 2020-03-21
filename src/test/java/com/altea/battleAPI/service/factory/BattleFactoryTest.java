package com.altea.battleAPI.service.factory;


import com.altea.battleAPI.bo.Battle;
import com.altea.battleAPI.bo.Pokemon;
import com.altea.battleAPI.bo.TrainerWithPokemons;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BattleFactoryTest {
    @Autowired
    private BattleFactory battleFactory;

    private TrainerWithPokemons createTrainer(int speed) {
        List<Pokemon> pokemons = Arrays.asList(Pokemon.builder().speed(speed).build());

        return TrainerWithPokemons.builder()
                .team(pokemons)
                .build();
    }

    @Test
    void createBattleSetSpeed() {
        TrainerWithPokemons trainer1 = createTrainer(50);
        TrainerWithPokemons trainer2 = createTrainer(60);

        Battle battle = battleFactory.createBattle(trainer1, trainer2);
        assertFalse(battle.getTrainer().isNextTurn());
        assertTrue(battle.getOpponent().isNextTurn());
    }

    @Test
    void createBattleSetSpeed2() {
        TrainerWithPokemons trainer1 = createTrainer(50);
        TrainerWithPokemons trainer2 = createTrainer(40);

        Battle battle = battleFactory.createBattle(trainer1, trainer2);
        assertTrue(battle.getTrainer().isNextTurn());
        assertFalse(battle.getOpponent().isNextTurn());
    }
}
