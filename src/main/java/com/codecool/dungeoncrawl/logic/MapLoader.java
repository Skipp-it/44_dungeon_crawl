package com.codecool.dungeoncrawl.logic;


import com.codecool.dungeoncrawl.logic.actors.Cowboy;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Shield;
import com.codecool.dungeoncrawl.logic.items.Sword;


import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    public static Cell doorCell;
    public static GameMap loadMap(String level) {

        InputStream is = MapLoader.class.getResourceAsStream(level);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine();

        GameMap map = new GameMap(width, height, CellType.EMPTY);


        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            new Cowboy(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case '+':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case '-':
                            cell.setType(CellType.FLOOR);
                            new Shield(cell);
                            break;
                        case '?':
                            cell.setType(CellType.FLOOR);
                            new Ghost(cell);
                            break;
                        case 'i':
                            cell.setType(CellType.CLOSE_DOOR);
                            doorCell=cell;
                            break;
                        case 'd':
                            cell.setType(CellType.OPEN_DOOR);
                            break;
                        case 'p':
                            cell.setType(CellType.TREE);
                            break;
                        case 'r':
                            cell.setType(CellType.RIVER);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
