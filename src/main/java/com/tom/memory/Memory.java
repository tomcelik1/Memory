package com.tom.memory;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Memory extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    private boolean playBack = true;

    private Random random = new Random();
    private List<MemoryCell> cells = new ArrayList<>();
    private int currentIndex = 0;

    private Stage stage;
    private GraphicsContext graphics;

    private void addNewCell() {
        cells.add(MemoryCell.valueOf(random.nextInt(4)));
    }

    private void onClick(MemoryCell cell) {
//        System.out.println("OnClick Called!");
        if (playBack) {
//            System.out.println("Cancelling click!");
            return;
        }
        Assets.playSound(cell);
        MemoryCell correct = cells.get(currentIndex);
        if (cell != correct) {
            onLose();
//            cells.clear();
//            addNewCell();
//            currentIndex = 0;
        } else {
            currentIndex++;
            if (currentIndex == cells.size()) {
                currentIndex = 0;
                addNewCell();
                playBack();
            }
        }
//        System.out.println("OnClick finished!");
    }

    private void onLose() {
        stage.close();
        System.out.println("You lost on round " + cells.size() + "!");
        System.out.println("Thanks for playing!");
    }

    private void playBack() {
//        System.out.println("PlayBack called!");
        playBack = true;
        drawAll();
        new Thread(() -> {
            sleep(1000);
            for (MemoryCell cell : cells) {
//                System.out.println("Drawing cell " + cell);
                Platform.runLater(() -> {
                    drawAll();
                    graphics.clearRect(Assets.getRectange(cell).getX(), Assets.getRectange(cell).getY(), Assets.getRectange(cell).getWidth(), Assets.getRectange(cell).getHeight());
                    graphics.drawImage(Assets.getImage(cell, true), Assets.getRectange(cell).getX(), Assets.getRectange(cell).getY());
                    Assets.playSound(cell);
//                    System.out.println("Finished drawing cell " + cell);
                });
                sleep(1000);
//                System.out.println("Finished nap");
            }
            drawAll();

            playBack = false;
        }).start();

//        System.out.println("Finished PlayBack!");
    }

    private void drawAll() {
//        System.out.println("DrawAll called!");
        MemoryCell.all().forEach(cell -> {
            graphics.clearRect(Assets.getRectange(cell).getX(), Assets.getRectange(cell).getY(), Assets.getRectange(cell).getWidth(), Assets.getRectange(cell).getHeight());
            graphics.drawImage(Assets.getImage(cell, false), Assets.getRectange(cell).getX(), Assets.getRectange(cell).getY());

        });
//        System.out.println("Drawn!");
    }

    @Override
    public void start(Stage stage) {
        Assets.load(this);

        this.stage = stage;

        addNewCell();

        stage.setTitle("Memory");

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(230, 230);
        root.getChildren().add(canvas);

        graphics = canvas.getGraphicsContext2D();

        scene.setOnMouseClicked(event -> {
            MemoryCell.all().stream()
                    .filter(cell -> Assets.getRectange(cell).contains(event.getX(), event.getY()))
                    .forEach(this::onClick);
        });

        stage.show();

        drawAll();

        new Thread(() -> {
            sleep(1500);
            Platform.runLater(this::playBack);
        }).start();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
