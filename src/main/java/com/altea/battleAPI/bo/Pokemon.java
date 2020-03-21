package com.altea.battleAPI.bo;

import com.altea.battleAPI.pokemonTypes.bo.PokemonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon implements Serializable {
    private int id;
    private PokemonType type;
    private int maxHp;
    private int attack;
    private int defense;
    private int speed;
    private int level;
    private int hp;
    private boolean ko;

    public void attack(Pokemon enemy) {
        enemy.takeDamage(
                (
                        2 * attack / 5 +
                                2 * (attack / enemy.getDefense())) +
                        2
        );
    }

    public void heal() {
       this.hp += 20;
       if(this.hp > this.maxHp) this.hp = this.maxHp;
    }

    private void takeDamage(int damage) {
        hp -= damage;

        if(hp <= 0) {
            setKo(true);
            setHp(0);
        }

    }
}
