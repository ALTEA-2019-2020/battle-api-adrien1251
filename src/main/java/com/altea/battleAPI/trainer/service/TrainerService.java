package com.altea.battleAPI.trainer.service;

import com.altea.battleAPI.bo.TrainerWithPokemons;

import java.util.List;

public interface TrainerService {
    List<TrainerWithPokemons> getAllTrainers();
    List<TrainerWithPokemons> getAllTrainers(String actualTrainer);
    TrainerWithPokemons getTrainer(String name);
}
