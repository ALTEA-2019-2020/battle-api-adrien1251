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
public class Sprites implements Serializable {
    private String back_default;
    private String front_default;
}
