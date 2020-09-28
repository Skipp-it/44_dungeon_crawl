package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int attack;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {

        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell != null) {
            Actor nextActor = nextCell.getActor();
            if (!nextCell.getTileName().equals("wall") && !nextCell.getTileName().equals("closeDoor")) {
                if (nextActor != null && cell.getActor().getTileName().equals("player")) {
                    if (nextActor instanceof Cowboy) {
                        Main.killCowboy((Cowboy) nextActor);
                    }
                    cell.getActor().setHealth(health - nextActor.attack);
                    nextCell.setActor(null);
                }
                if (nextActor instanceof Cowboy && nextCell != cell) {
                    Main.killCowboy((Cowboy) nextActor);
                }
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDead() {
        return this.health <= 0;
    }
}
