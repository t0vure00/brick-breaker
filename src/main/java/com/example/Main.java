package com.example;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.Toolkit;


public class Main extends Application {
    private Dictionary<String, Brick> bricks = new Hashtable<String, Brick>();
    private Player player;
    private Ball ball;
    private double screenHeight;
    private double screenWidth;

    public Main() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        // to account for the upper and lower bars
        this.screenHeight = dimension.getHeight()-72;
        this.screenWidth = dimension.getWidth();

        this.player = new Player((int)screenWidth/2 - 50, (int)screenHeight-20, 100, 10);
        this.ball = new Ball((int)screenWidth/2, (int)screenHeight/2, 10, screenWidth, screenHeight);
    }

    @Override
    public void start(Stage stage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: black;");
        
        anchorPane.getChildren().add(this.player);
        anchorPane.setBottomAnchor(this.player, 20.0);
        
        anchorPane.getChildren().add(this.ball);
        
        setFirstLevel(anchorPane);
        
        Scene scene = new Scene(anchorPane, this.screenWidth, this.screenHeight);
        MoveWithArrows moveArrows = new MoveWithArrows(scene, this.player);

        Player player1 = this.player;
        // To update the ball's position every frame
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                ball.update(player1);

                checkCollisionWithPlayer();

                if (ball.getStatus()) {
                    this.stop();
                    ball.setFill(Color.BLACK);
                }
            }
        }.start();

        stage.setTitle("Brick breaker game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void setFirstLevel(AnchorPane anchorPane){
        int brickWidth = 60;
        int brickHeight = 20;
        int brickSpaceVertical = 15;
        int brickSpaceHorizontal = 15;
        // Countign how many bricks fit in a row
        int bricksPerRow = (int) (this.screenWidth/(brickWidth+brickSpaceHorizontal));
        // Countign how many pixels are left over
        int pixels_left = (int) this.screenWidth%(brickWidth+brickSpaceHorizontal);

        for (int row = 0; row < 5; row++) {
            // Countign how many pixels are left over from between the bricks and dividing by 2
            // to get the space on the left and right of the row
            brickSpaceHorizontal = 15 +(pixels_left%(bricksPerRow-1))/2;
            for (int col = 0; col < bricksPerRow; col++) {
                Brick brick = new Brick(brickSpaceHorizontal, brickSpaceVertical, brickWidth, brickHeight);
                bricks.put(Integer.toString(row) + Integer.toString(col), brick);
                anchorPane.getChildren().add(brick);
                anchorPane.setLeftAnchor(brick, (double) brickSpaceHorizontal);
                anchorPane.setTopAnchor(brick, (double) brickSpaceVertical);
                brickSpaceHorizontal += brickWidth + 15;
            }
            // Upping vertical height for the next row
            brickSpaceVertical += brickHeight + 15;
        }
    }

    
    private void checkCollisionWithPlayer(){
        boolean isHOnLevelWPlayer = ball.getCenterX() >= player.getLayoutX() && 
                        ball.getCenterX() <= player.getLayoutX() + player.getWidth();
        boolean isVOnLevelWPlayer = ball.getCenterY() >= player.getLayoutY() && 
                        ball.getCenterY() <= player.getLayoutY() + player.getHeight();
        double ballBottom = ball.getCenterY() + (ball.getRadiusY()*0.7);
        double ballTop = ball.getCenterY() - (ball.getRadiusY()*0.7);
        boolean isCollidingWTop = isHOnLevelWPlayer && ballBottom >= player.getLayoutY() - 2 && ballBottom <= player.getLayoutY() + 2;
        boolean isCollidingWBottom = isHOnLevelWPlayer && ballTop == player.getLayoutY() + player.getHeight();
        boolean isCollidingWLSide = isVOnLevelWPlayer && ball.getCenterX() + ball.getRadiusX() >= player.getLayoutX() + 2 && ball.getCenterX() + ball.getRadiusX() <= player.getLayoutX() - 2;
        boolean isCollidingWRSide = isVOnLevelWPlayer && ball.getCenterX() == player.getLayoutX() + player.getWidth();
        
        if(isCollidingWTop || isCollidingWBottom){
            double[] vxvy = ball.getVelocity(); 
            ball.setVelocity(vxvy[0], vxvy[1]*-1);
        }

        if (isCollidingWLSide || isCollidingWRSide) {
            double[] vxvy = ball.getVelocity(); 
            ball.setVelocity(vxvy[0]*-1, vxvy[1]);
        }
    }
}