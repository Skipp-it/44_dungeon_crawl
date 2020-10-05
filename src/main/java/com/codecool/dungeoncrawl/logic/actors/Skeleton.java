package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    private final int SK_HEALTH = 1000;
    private final int SK_ATTACK = 5;

    public Skeleton(Cell cell) {
        super(cell);
        this.setHealth(SK_HEALTH);
        this.setAttack(SK_ATTACK);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}

