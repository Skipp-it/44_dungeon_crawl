package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Actor {
    private final int SK_HEALTH = 10;
    private final int SK_ATTACK = 3;
    public Ghost(Cell cell) {
        super(cell);
        this.setHealth(SK_HEALTH);
        this.setAttack(SK_ATTACK);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
