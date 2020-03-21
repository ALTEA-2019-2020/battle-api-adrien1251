package com.altea.battleAPI.controller;

import com.altea.battleAPI.bo.Battle;
import com.altea.battleAPI.exceptionHandler.ExceptionCatcher;
import com.altea.battleAPI.service.BattleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/battles")
public class BattleController extends ExceptionCatcher {
    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping
    public ResponseEntity<UUID> createBattle(@RequestParam String dresseur1, @RequestParam String dresseur2) {
        return ResponseEntity.ok(battleService.createBattle(dresseur1, dresseur2));
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Battle> findBattle(@PathVariable UUID uuid) {
        return ResponseEntity.ok(battleService.findBattle(uuid));
    }

    @GetMapping
    public ResponseEntity<Collection<Battle>> findBattles() {
        return ResponseEntity.ok(battleService.findBattles().values());
    }

    @PostMapping(value = "/{uuid}/{trainerName}/attack")
    public ResponseEntity<Battle> attack(@PathVariable UUID uuid, @PathVariable String trainerName) {
        return ResponseEntity.ok(battleService.attack(uuid, trainerName));
    }

    @PostMapping(value = "/{uuid}/{trainerName}/heal")
    public ResponseEntity<Battle> heal(@PathVariable UUID uuid, @PathVariable String trainerName) {
        return ResponseEntity.ok(battleService.heal(uuid, trainerName));
    }
}
