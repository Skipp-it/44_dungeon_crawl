package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Cowboy extends Actor{

    public Cowboy(Cell cell) { super(cell); }


        @Override
    public String getTileName() { return "cowboy"; }


    @Override
    public void move(int dx, int dy) {
        System.out.println("intra aic?");
        int max=1;
        int min=-1;
        Random random1=new Random();
        Random random2=new Random();
       int rand1=random1.nextInt(((max-min) +1)+ min);
        int rand2=random2.nextInt(((max-min) +1)+ min);



        System.out.println(rand1);
        System.out.println(rand2);
        super.move(rand1,rand2);
    }
}
