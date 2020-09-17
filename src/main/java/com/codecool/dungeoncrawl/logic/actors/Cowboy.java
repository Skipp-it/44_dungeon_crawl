package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;


public class Cowboy extends Actor{

    private final int SK_HEALTH = 10;
    private final int SK_ATTACK = 10;
    public Cowboy(Cell cell) {
        super(cell);
        this.setHealth(SK_HEALTH);
        this.setAttack(SK_ATTACK);
    }

        @Override
    public String getTileName() { return "cowboy"; }


    @Override
    public void move(int dx, int dy) {
        if(this.getCell().getNeighbor(dx, dy).getActor() != null ){
          if(!this.getCell().getNeighbor(dx,dy).getActor().getTileName().equals("player")){
              dx=0;
              dy=0;
            } }


        super.move(dx,dy);
    }
}
