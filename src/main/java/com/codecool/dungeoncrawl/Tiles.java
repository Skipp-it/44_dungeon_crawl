package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    private static Actor player;


    public static void openDoor() {
        MapLoader.doorCell.setType(CellType.OPEN_DOOR);
        tileMap.put("openDoor", new Tile(2, 9));
    }

    private static int countSwords() {
        int nrSwords = 0;
        for (int i = 0; i < Main.inventory.size(); i++) {
            if (Main.inventory.get(i).equals("sword")) {
                nrSwords++;
            }
        }
        return nrSwords;
    }

    public static void updatePlayer() {

        if (player != null && player.isDead()) {
            tileMap.put("player", new Tile(1, 14));
        } else if (Main.inventory.contains("sword") && countSwords() > 1) {
            tileMap.put("player", new Tile(30, 2));
        } else if (Main.inventory.contains("sword") && Main.inventory.contains("shield")) {
            tileMap.put("player", new Tile(31, 0));
        } else if (Main.inventory.contains("sword")) {
            tileMap.put("player", new Tile(26, 0));
        } else if (Main.inventory.contains("shield")) {
            tileMap.put("player", new Tile(30, 0));
        } else {
            tileMap.put("player", new Tile(25, 0));
        }
    }

    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;


        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(15, 27));
        tileMap.put("player", new Tile(25, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("cowboy", new Tile(31, 2));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("sword", new Tile(0, 29));
        tileMap.put("shield", new Tile(5, 26));
        tileMap.put("ghost", new Tile(27, 6));
        tileMap.put("closeDoor", new Tile(0, 11));
        tileMap.put("tree", new Tile(1, 1));
        tileMap.put("river", new Tile(8, 5));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

    public static void setPlayer(Actor player) {
        Tiles.player = player;
    }

}
