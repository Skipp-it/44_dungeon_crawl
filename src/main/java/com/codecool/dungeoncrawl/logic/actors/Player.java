package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    private final int PLAYER_HEALTH = 20;
    private final int PLAYER_ATTACK = 5;
    public Player(Cell cell) {
        super(cell);
        this.setHealth(PLAYER_HEALTH);
        this.setAttack(PLAYER_ATTACK);
    }

    public String getTileName() {
        return "player";
    }
}
