package com.example;

import javafx.scene.Scene;

public class MoveWithArrows {
    private Scene scene;

    public MoveWithArrows(Scene scene, Player player) {
        this.scene = scene;
        this.scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    moveLeft(player);
                    break;
                case RIGHT:
                    moveRight(player);
                    break;
            }
        });
    }

    public void moveLeft(Player player) {
        if(player.getLayoutX() > 15) {
            player.setLayoutX(player.getLayoutX() - 15);
        }
    }

    public void moveRight(Player player) {
        if(player.getLayoutX() < this.scene.getWidth() - 100) {
            player.setLayoutX(player.getLayoutX() + 15);
        }
    }
}