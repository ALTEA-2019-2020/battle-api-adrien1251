package com.altea.battleAPI.pokemonTypes.service;

import com.altea.battleAPI.pokemonTypes.bo.PokemonType;

import java.util.List;

public interface PokemonTypeService {
    List<PokemonType> listPokemonsTypes();
    List<PokemonType> listPokemonsTypes(List<Integer> id);
}
