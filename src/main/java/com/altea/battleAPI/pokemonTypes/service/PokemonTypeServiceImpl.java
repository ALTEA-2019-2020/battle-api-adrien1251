package com.altea.battleAPI.pokemonTypes.service;

import com.altea.battleAPI.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {
    private final String POKEMON_TYPE_PATH = "/pokemon-types/";

    private RestTemplate restTemplate;

    private String pokemonServiceUrl;

    @Override
    public List<PokemonType> listPokemonsTypes() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Accept-Language", LocaleContextHolder.getLocale().toLanguageTag());
        HttpEntity entity = new HttpEntity(headers);
        PokemonType[] pokemonTypes = restTemplate
                .exchange(pokemonServiceUrl + POKEMON_TYPE_PATH, HttpMethod.GET, entity, PokemonType[].class)
                .getBody();

        return Arrays.asList(pokemonTypes);
    }

    @Override
    public List<PokemonType> listPokemonsTypes(List<Integer> ids) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Accept-Language", LocaleContextHolder.getLocale().toLanguageTag());
        HttpEntity entity = new HttpEntity(headers);

        List<PokemonType> pokemonTypes = new ArrayList<>();
        ids.stream().forEach(pokemonId ->
                pokemonTypes.add(restTemplate.exchange(pokemonServiceUrl + POKEMON_TYPE_PATH + pokemonId, HttpMethod.GET, entity, PokemonType.class).getBody())
        );
        return pokemonTypes;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }

}
