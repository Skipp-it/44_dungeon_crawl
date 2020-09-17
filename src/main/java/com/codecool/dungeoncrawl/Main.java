package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Cowboy;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
    private ArrayList<Cowboy> cowboys;
    private static ArrayList<Cowboy> killedCowboys = new ArrayList<>();
    static String[] levels = new String[]{"/map.txt", "/map2.txt"};
    int level = 0;
    static ArrayList<String> inventory = new ArrayList<>();

    GameMap map = MapLoader.loadMap(levels[level]);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label attackLabel = new Label();
    Label test = new Label();
    Button pickUpBtn = new Button("Pick Up Item");


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        findCowboys();

        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(new Label("Attack: "), 0, 1);
        ui.add(healthLabel, 1, 0);
        ui.add(attackLabel, 1, 1);
        ui.add(pickUpBtn, 0, 2);
        pickUpBtn.setDisable(true);
        ui.add(new Label("Inventory:"), 0, 3);
        ui.add(test, 0, 4);


        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");

        healthLabel.setText("" + map.getPlayer().getHealth());
        attackLabel.setText("" + map.getPlayer().getAttack());
        pickUpBtn.setDisable(map.getPlayer().getCell().isItem());
        pickUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                inventory.add(map.getPlayer().getCell().getItem().getTileName());
                if (inventory.contains("key")) {
                    Tiles.openDoor();
                }
                Tiles.updatePlayer();
                StringBuilder inventar = new StringBuilder();
                for (String i : inventory) {
                    inventar.append("1x ").append(i).append("\n");
                }
                if (map.getPlayer().getCell().getItem().getTileName().equals("sword")) {
                    map.getPlayer().setAttack(map.getPlayer().getAttack() + 10);
                    attackLabel.setText(String.valueOf(map.getPlayer().getAttack()));
                }
                if (map.getPlayer().getCell().getItem().getTileName().equals("shield")) {
                    map.getPlayer().setHealth(map.getPlayer().getHealth() + 20);
                    healthLabel.setText(String.valueOf(map.getPlayer().getHealth()));
                }
                map.getPlayer().getCell().setItem(null);
                pickUpBtn.setDisable(true);
                test.setText(inventar.toString());
                refresh();


            }
        });

        primaryStage.show();
    }


    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);

                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
        }
        moveCowboys();
        interactWithCell();

    }

    private void interactWithCell() {

        pickUpBtn.setDisable(map.getPlayer().getCell().isItem());
        attackLabel.setText("" + map.getPlayer().getAttack());
        healthLabel.setText("" + map.getPlayer().getHealth());

        Cell cell = map.getPlayer().getCell();
        if (cell.getTileName().equals("openDoor")) {
            Player oldPlayer = map.getPlayer();
            map = MapLoader.loadMap(levels[++level]);
            findCowboys();
            map.getPlayer().setHealth(oldPlayer.getHealth());
            map.getPlayer().setAttack(oldPlayer.getAttack());
            inventory.remove("key");
            StringBuilder inventar = new StringBuilder();
            for (String i : inventory) {
                inventar.append("1x ").append(i).append("\n");
            }
            test.setText(inventar.toString());
            refresh();
        }
    }


    private void findCowboys() {
        cowboys = new ArrayList<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null && cell.getActor() instanceof Cowboy) {
                    cowboys.add((Cowboy) cell.getActor());
                }
            }
        }
    }

    private void moveCowboys() {
        int max = 1;
        int min = -1;
        int range = max - min + 1;
        for (Cowboy cowboy : cowboys) {
            int rand1 = (int) (Math.random() * range) + min;
            int rand2 = (int) (Math.random() * range) + min;
            cowboy.move(rand1, rand2);
        }
        for (Cowboy killedCowboy : killedCowboys) {

            cowboys.remove(killedCowboy);
        }
        killedCowboys = new ArrayList<>();
    }

    public static void killCowboy(Cowboy cowboy) {
        killedCowboys.add(cowboy);
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }

    }

}
