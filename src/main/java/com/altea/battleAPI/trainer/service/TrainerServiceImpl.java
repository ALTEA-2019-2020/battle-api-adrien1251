package com.altea.battleAPI.trainer.service;

import com.altea.battleAPI.bo.TrainerWithPokemons;
import com.altea.battleAPI.pokemonTypes.service.PokemonTypeService;
import com.altea.battleAPI.trainer.bo.Trainer;
import com.altea.battleAPI.trainer.converter.TrainerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {
    private final String TRAINER_PATH = "/trainers/";

    private RestTemplate restTemplate;

    private String trainerServiceUrl;

    private PokemonTypeService pokemonTypeService;

    @Autowired
    private TrainerConverter trainerConverter;

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @Value("${trainers.service.url}")
    void setTrainerServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl = trainerServiceUrl;
    }

    @Override
    public List<TrainerWithPokemons> getAllTrainers() {
        Trainer[] trainers = restTemplate.getForObject(trainerServiceUrl + TRAINER_PATH, Trainer[].class);

        return Arrays.stream(trainers)
                .map(trainerConverter::trainerToTrainerWithPokemons)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainerWithPokemons> getAllTrainers(String actualTrainer) {
        Trainer[] trainers = restTemplate.getForObject(trainerServiceUrl + TRAINER_PATH, Trainer[].class);

        return Arrays.stream(trainers)
                .filter(trainer -> !trainer.getName().equals(actualTrainer))
                .map(trainerConverter::trainerToTrainerWithPokemons)
                .collect(Collectors.toList());
    }

    @Override
    public TrainerWithPokemons getTrainer(String name) {
        return trainerConverter.trainerToTrainerWithPokemons(
                restTemplate.getForObject(trainerServiceUrl + TRAINER_PATH + name, Trainer.class)
        );
    }
}
