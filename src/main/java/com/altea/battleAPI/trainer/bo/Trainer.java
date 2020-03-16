package com.altea.battleAPI.trainer.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Trainer implements Serializable {
    private String name;

    private String password;

    private List<Pokemon> team = new ArrayList<>();
}
