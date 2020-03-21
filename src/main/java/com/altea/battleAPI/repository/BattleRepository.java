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

    public UUID save(Battle battle) {
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
