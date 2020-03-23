package com.altea.battleAPI.service.factory;

import com.altea.battleAPI.bo.Battle;
import com.altea.battleAPI.bo.TrainerWithPokemons;
import org.springframework.stereotype.Component;

@Component
public class BattleFactory {
    public Battle createBattle(TrainerWithPokemons trainer, TrainerWithPokemons opponent) {
        Battle battle = new Battle(trainer, opponent);

        int trainerSpeed = battle.getTrainer().getTeam().get(battle.getTrainer().getCurrentPokemon()).getSpeed();
        int opponentSpeed = battle.getOpponent().getTeam().get(battle.getOpponent().getCurrentPokemon()).getSpeed();

        if(trainerSpeed < opponentSpeed) {
            battle.getOpponent().setNextTurn(true);
        } else {
            battle.getTrainer().setNextTurn(true);
        }

        return battle;
    }
}
