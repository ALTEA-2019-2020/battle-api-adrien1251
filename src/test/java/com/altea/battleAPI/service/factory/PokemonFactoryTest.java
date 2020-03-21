//package com.altea.battleAPI.service.factory;
//
//import com.altea.battleAPI.bo.Pokemon;
//import com.altea.battleAPI.pokemonTypes.bo.PokemonType;
//import com.altea.battleAPI.pokemonTypes.bo.Stats;
//import net.bytebuddy.asm.Advice;
//import org.junit.Ignore;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class PokemonFactoryTest {
//    @Autowired
//    private PokemonFactory pokemonFactory;
//
//    @Test
//    @Ignore
//    void testStatPickachuLvl6() {
//        Stats stats = Stats.builder()
//                .attack(55)
//                .defense(40)
//                .speed(90)
//                .hp(35)
//                .build();
//
//        PokemonType pickachu = PokemonType.builder()
//                .stats(stats)
//                .id(25)
//                .build();
//
//        Pokemon pokemonInCombat = pokemonFactory.createPokemon(6, pickachu);
//        assertEquals(11, pokemonInCombat.getAttack());
//        assertEquals(8, pokemonInCombat.getDefense());
//        assertEquals(15, pokemonInCombat.getSpeed());
//        assertEquals(20, pokemonInCombat.getHp());
//        assertEquals(pokemonInCombat.getHp(), pokemonInCombat.getMaxHp());
//    }
//}
