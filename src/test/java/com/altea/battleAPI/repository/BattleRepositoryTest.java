package com.altea.battleAPI.repository;

import com.altea.battleAPI.bo.Battle;
import com.altea.battleAPI.bo.TrainerWithPokemons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BattleRepositoryTest {
    @Autowired
    private BattleRepository battleRepository;

    @Test
    void createBattleSaveInBase() {
        Battle battle = new Battle(TrainerWithPokemons.builder().build(), TrainerWithPokemons.builder().build());
        battleRepository.save(battle);

        Assertions.assertEquals(battleRepository.findBattle(battle.getUuid()), battle);
    }

}
