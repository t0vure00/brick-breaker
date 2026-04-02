package com.example;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Ball extends Ellipse{
    public Ball(int x, int y, int radius) {
        this.setRadiusX(radius);
        this.setRadiusY(radius);
        this.setFill(Color.WHITE);
        this.setCenterX(x);
        this.setCenterY(y);
    }   
}
