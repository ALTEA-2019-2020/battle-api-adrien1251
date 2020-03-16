package com.altea.battleAPI.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class Battle implements Serializable {
    private UUID uuid;
    private TrainerWithPokemons trainer;
    private TrainerWithPokemons opponent;

    public Battle(TrainerWithPokemons trainer, TrainerWithPokemons opponent) {
        this.uuid = UUID.randomUUID();
        this.trainer = trainer;
        this.opponent = opponent;
    }
}
