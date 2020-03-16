package com.altea.battleAPI.repository;

import com.altea.battleAPI.bo.Battle;
import com.altea.battleAPI.bo.TrainerWithPokemons;
import com.altea.battleAPI.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class BattleRepository {
    private Map<UUID, Battle> battles = new HashMap<>();

    public UUID createBattle(TrainerWithPokemons trainer, TrainerWithPokemons opponent) {
        Battle battle = new Battle(trainer, opponent);

        int trainerSpeed = battle.getTrainer().getTeam().get(battle.getTrainer().getCurrentPokemon()).getSpeed();
        int opponentSpeed = battle.getOpponent().getTeam().get(battle.getOpponent().getCurrentPokemon()).getSpeed();

        if(trainerSpeed < opponentSpeed) {
            battle.getOpponent().setNextTurn(true);
        } else {
            battle.getTrainer().setNextTurn(true);
        }
        battles.put(battle.getUuid(), battle);

        return battle.getUuid();
    }

    public Battle findBattle(UUID uuid) {
        if(battles.containsKey(uuid)) {
            return battles.get(uuid);
        }
        throw new ApplicationException(HttpStatus.NOT_FOUND, "No battle found for uuid: " + uuid.toString());
    }

    public Map<UUID, Battle> findBattles() {
        return battles;
    }
}
