package com.example;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle {
    public Player(int x, int y, int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(Color.WHITE);
        this.setX(x);
        this.setY(y);
    }
}