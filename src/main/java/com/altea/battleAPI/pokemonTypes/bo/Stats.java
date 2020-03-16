package com.altea.battleAPI.pokemonTypes.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats implements Serializable {
    private Integer speed;
    private Integer defense;
    private Integer attack;
    private Integer hp;
}
