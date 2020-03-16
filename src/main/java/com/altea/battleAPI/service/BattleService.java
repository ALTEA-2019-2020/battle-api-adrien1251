package com.altea.battleAPI.service;

import com.altea.battleAPI.bo.Battle;
import com.altea.battleAPI.bo.PokemonWithLvl;
import com.altea.battleAPI.bo.TrainerWithPokemons;
import com.altea.battleAPI.exceptions.ApplicationException;
import com.altea.battleAPI.repository.BattleRepository;
import com.altea.battleAPI.trainer.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;
import java.util.UUID;

@Service
public class BattleService {
    private final BattleRepository battleRepository;

    private final TrainerService trainerService;

    public BattleService(BattleRepository battleRepository, TrainerService trainerService) {
        this.battleRepository = battleRepository;
        this.trainerService = trainerService;
    }

    public UUID createBattle(String trainer, String opponent) {
        return battleRepository.createBattle(
                trainerService.getTrainer(trainer),
                trainerService.getTrainer(opponent)
        );
    }

    public Battle findBattle(UUID uuid) {
        return battleRepository.findBattle(uuid);
    }

    public Map<UUID, Battle> findBattles() {
        return battleRepository.findBattles();
    }

    public Battle attack(UUID uuid, String trainer) {
        Battle battle = battleRepository.findBattle(uuid);
        TrainerWithPokemons attackerTrainer;
        TrainerWithPokemons defenderTrainer;

        if(battle.getTrainer().getTrainer().getName().equals(trainer)) {
            attackerTrainer = battle.getTrainer();
            defenderTrainer = battle.getOpponent();
        } else {
            attackerTrainer = battle.getOpponent();
            defenderTrainer = battle.getTrainer();
        }


        if(!attackerTrainer.isNextTurn()) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "It's not your turn to play");
        }

        getCurrentPokemon(attackerTrainer).attack(getCurrentPokemon(defenderTrainer));

        if(getCurrentPokemon(defenderTrainer).isKo()) {
            defenderTrainer.setCurrentPokemon(defenderTrainer.getCurrentPokemon() + 1);
        }

        attackerTrainer.setNextTurn(false);
        defenderTrainer.setNextTurn(true);

        return battle;
    }

    public Battle heal(UUID uuid, String trainer) {
        Battle battle = battleRepository.findBattle(uuid);
        TrainerWithPokemons attackerTrainer;
        TrainerWithPokemons defenderTrainer;

        if(battle.getTrainer().getTrainer().getName().equals(trainer)) {
            attackerTrainer = battle.getTrainer();
            defenderTrainer = battle.getOpponent();
        } else {
            attackerTrainer = battle.getOpponent();
            defenderTrainer = battle.getTrainer();
        }

        if(!attackerTrainer.isNextTurn()) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "It's not your turn to play");
        }

        getCurrentPokemon(attackerTrainer).heal();

        attackerTrainer.setNextTurn(false);
        defenderTrainer.setNextTurn(true);

        return battle;
    }

    private PokemonWithLvl getCurrentPokemon(TrainerWithPokemons trainer) {
        try {
            return trainer.getTeam().get(trainer.getCurrentPokemon());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(trainer.getName() + " n'a plus de pokemon");
        }
    }
}
