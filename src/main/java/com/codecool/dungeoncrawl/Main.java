package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {

    ArrayList<String> inventory = new ArrayList<>();
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            ((int) (map.getWidth() / 2)) * Tiles.TILE_WIDTH,
            ((int) (map.getHeight() / 2)) * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label test = new Label();
    Button pickUpBtn = new Button("Pick Up Item");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(pickUpBtn, 0, 1);
        pickUpBtn.setDisable(true);
        ui.add(new Label("Inventory:"), 0, 2);
        ui.add(test, 0, 3);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);

        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
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
    }
    private void refresh() {

        int offsetX = 14;
        int offsetY = 10;

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int deltaX = map.getWidth() / 2 - map.getPlayer().getX()- offsetX;
        int deltaY = map.getHeight() / 2 - map.getPlayer().getY()-offsetY;

        if (map.getPlayer().getX()<offsetX){deltaX=0;}
        if (map.getPlayer().getY()<offsetY){deltaY=0;}

        if (deltaX>=8){deltaX=0;}
        if (deltaY>0){deltaY=0;}

        if (deltaX< -2*offsetX){deltaX = -2*offsetX;}
        if (deltaY< -2*offsetY){deltaY = -2*offsetY;}

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x + deltaX , y + deltaY);
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), x + deltaX , y + deltaY);
                    } else {
                        Tiles.drawTile(context, cell, x + deltaX , y + deltaY);
                    }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        pickUpBtn.setDisable(map.getPlayer().getCell().isItem());
        pickUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                inventory.add(map.getPlayer().getCell().getItem().getTileName());
                StringBuilder inventar = new StringBuilder();
                for (String i : inventory
                ) {
                    inventar.append("1x ").append(i).append("\n");
                }
                map.getPlayer().getCell().setItem(null);
                pickUpBtn.setDisable(true);
                test.setText(inventar.toString());
                refresh();
            }
        });
    }
}
