package com.example;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {
    private boolean destroyed;

    public Brick(int x, int y, int width, int height) {
        this.destroyed = false;

        this.setWidth(width);
        this.setHeight(height);
        this.setFill(Color.WHITE);
        this.setX(x);
        this.setY(y);
    }

    // Getters and setters
    public boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
        this.destroyed = true;
    }
}
