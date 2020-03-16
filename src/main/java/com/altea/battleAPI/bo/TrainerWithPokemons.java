package com.altea.battleAPI.bo;

import com.altea.battleAPI.trainer.bo.Trainer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainerWithPokemons implements Serializable {
    private Trainer trainer;
    private String name;
    private int currentPokemon = 0;
    private List<PokemonWithLvl> team = new ArrayList<>();
    private boolean nextTurn = false;
}
