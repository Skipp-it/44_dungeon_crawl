package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health =100;
    private int actualHealth;
    private int attack = 9;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor nextActor= nextCell.getActor();
        if(!nextCell.getTileName().equals("wall") && nextActor==null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        if(cell.getItem()!=null){
            System.out.println(cell.getItem());
        }

    }



    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
    public int getActualHealth(){
        return actualHealth;
    }

    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack){
        this.attack = attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
