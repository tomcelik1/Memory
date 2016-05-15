package com.tom.memory;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;

public class Assets {

    private static Memory memory;

    private static Image red;
    private static Image yellow;
    private static Image blue;
    private static Image green;

    private static Image redDark;
    private static Image yellowDark;
    private static Image blueDark;
    private static Image greenDark;

    private static Rectangle redRectangle;
    private static Rectangle yellowRectangle;
    private static Rectangle blueRectangle;
    private static Rectangle greenRectangle;


    private static MediaPlayer redPlayer;
    private static MediaPlayer yellowPlayer;
    private static MediaPlayer bluePlayer;
    private static MediaPlayer greenPlayer;

    public static void load(Memory memory) {
        Assets.memory = memory;

        red = new Image("red.jpg");
        yellow = new Image("yellow.jpg");
        blue = new Image("blue.jpg");
        green = new Image("green.jpg");

        redDark = new Image("red-dark.jpg");
        yellowDark = new Image("yellow-dark.jpg");
        blueDark = new Image("blue-dark.jpg");
        greenDark = new Image("green-dark.jpg");

        redRectangle = new Rectangle(10, 10, 100, 100);
        yellowRectangle = new Rectangle(120, 10, 100, 100);
        blueRectangle = new Rectangle(10, 120, 100, 100);
        greenRectangle = new Rectangle(120, 120, 100, 100);

        Media redSound = new Media(memory.getHostServices().getDocumentBase() + "/assets/sounds/" + "low-c.wav");
        redPlayer = new MediaPlayer(redSound);
        Media yellowSound = new Media(memory.getHostServices().getDocumentBase() + "/assets/sounds/" + "e.wav");
        yellowPlayer = new MediaPlayer(yellowSound);
        Media blueSound = new Media(memory.getHostServices().getDocumentBase() + "/assets/sounds/" + "g.wav");
        bluePlayer = new MediaPlayer(blueSound);
        Media greenSound = new Media(memory.getHostServices().getDocumentBase() + "/assets/sounds/" + "high-c.wav");
        greenPlayer = new MediaPlayer(greenSound);
    }

    public static void playSound(MemoryCell cell) {
        stopPlaying();
        switch (cell) {
            case RED:
                redPlayer.play();
                break;
            case YELLOW:
                yellowPlayer.play();
                break;
            case BLUE:
                bluePlayer.play();
                break;
            case GREEN:
                greenPlayer.play();
                break;
        }
    }

    private static void stopPlaying() {
        redPlayer.stop();
        yellowPlayer.stop();
        bluePlayer.stop();
        greenPlayer.stop();
    }

    public static Image getImage(MemoryCell cell, boolean dark) {
        switch (cell) {
            case RED:
                return dark ? redDark : red;
            case YELLOW:
                return dark ? yellowDark : yellow;
            case BLUE:
                return dark ? blueDark : blue;
            case GREEN:
                return dark ? greenDark : green;
            default:
                return null;
        }
    }

    public static Rectangle getRectange(MemoryCell cell) {
        switch (cell) {
            case RED:
                return redRectangle;
            case YELLOW:
                return yellowRectangle;
            case BLUE:
                return blueRectangle;
            case GREEN:
                return greenRectangle;
            default:
                return redRectangle;
        }
    }

}
