package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int actualHealth;
    private int attack;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor nextActor = nextCell.getActor();
        if (!nextCell.getTileName().equals("wall") && !nextCell.getTileName().equals("closeDoor")) {
            if (nextActor != null && cell.getActor().getTileName().equals("player")) {
                cell.getActor().setHealth(health - nextActor.attack);
                if (isDead())
                    System.out.println("E mort");
                System.out.println(nextActor.attack);
            }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        if (cell.getItem() != null) {
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

    public int getActualHealth() {
        return actualHealth;
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
