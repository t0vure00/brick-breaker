package com.example;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Ball extends Ellipse{
    // Initial velocity of the ball
    private double vx = 4.0;
    private double vy = 4.0;
    private double screenWidth;
    private double screenHeight;
    private boolean gameOver = false;

    public Ball(int x, int y, int radius, double screenWidth, double screenHeight) {
        this.setRadiusX(radius);
        this.setRadiusY(radius);
        this.setFill(Color.WHITE);
        this.setCenterX(x);
        this.setCenterY(y);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void setVelocity(double vx, double vy){
        this.vx = vx;
        this.vy = vy;
    }

    public void update(Player player){
        if(gameOver){
            return;
        }
        // Update the position of the ball based on its velocity
        this.setCenterX(this.getCenterX() + this.vx);
        this.setCenterY(this.getCenterY() + this.vy);

        // Check for collisions with the walls and reverse velocity if needed 
        int r = (int)this.getRadiusX();
        if(this.getCenterX() <= r || this.getCenterX() >= this.screenWidth - r) {
            this.vx *= -1;
        }

        if(this.getCenterY() <= r) {
            this.vy *= -1;
        }else if(this.getCenterY() >= this.screenHeight - r){
            this.gameOver = true;
        }

        boolean isHOnLevelWPlayer = this.getCenterX() >= player.getLayoutX() && 
                        this.getCenterX() <= player.getLayoutX() + player.getWidth();
        boolean isVOnLevelWPlayer = this.getCenterY() >= player.getLayoutY() && 
                        this.getCenterY() <= player.getLayoutY() + player.getHeight();
        double ballBottom = this.getCenterY() + (this.getRadiusY()*0.7);
        double ballTop = this.getCenterY() - (this.getRadiusY()*0.7);
        boolean isCollidingWTop = isHOnLevelWPlayer && ballBottom >= player.getLayoutY() - 2 && ballBottom <= player.getLayoutY() + 2;
        boolean isCollidingWBottom = isHOnLevelWPlayer && ballTop == player.getLayoutY() + player.getHeight();
        boolean isCollidingWLSide = isVOnLevelWPlayer && this.getCenterX() + this.getRadiusX() >= player.getLayoutX() + 2 && this.getCenterX() + this.getRadiusX() <= player.getLayoutX() - 2;
        boolean isCollidingWRSide = isVOnLevelWPlayer && this.getCenterX() == player.getLayoutX() + player.getWidth();

        
        if(isCollidingWTop || isCollidingWBottom){
            this.vy *= -1;
        }

        if (isCollidingWLSide || isCollidingWRSide) {
            this.vx *= -1;
        }
    }

    public boolean checkStatus(){
        return this.gameOver;
    }
}