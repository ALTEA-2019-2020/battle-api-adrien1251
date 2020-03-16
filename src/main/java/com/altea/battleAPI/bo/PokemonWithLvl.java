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
public class PokemonWithLvl implements Serializable {
    private int id;
    private PokemonType type;
    private int maxHp;
    private int attack;
    private int defense;
    private int speed;
    private int level;
    private int hp;
    private boolean ko;

    public PokemonWithLvl(int level, PokemonType pokemonType) {
        this.level = level;
        this.type = pokemonType;
        this.id = pokemonType.getId();
        this.attack = getStat(pokemonType.getStats().getAttack(), level);
        this.defense = getStat(pokemonType.getStats().getAttack(), level);
        this.speed = getStat(pokemonType.getStats().getAttack(), level);
        this.hp = this.maxHp = (int) (10 + level + pokemonType.getStats().getHp() * (level / 50.0));
    }

    private int getStat(int state, int level) {
        return (int) (5 + state * (level / 50.0));
    }

    public void attack(PokemonWithLvl enemy) {
        enemy.takeDamage(
                (
                        2 * attack / 5 +
                                2 * (attack / enemy.getDefense())) +
                        2
        );

        if(enemy.getHp() <= 0) {
            enemy.setKo(true);
        }

    }

    public void heal() {
       this.hp += 20;
       if(this.hp > this.maxHp) this.hp = this.maxHp;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }
}
