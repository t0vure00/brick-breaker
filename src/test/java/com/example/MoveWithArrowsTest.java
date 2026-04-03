package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MoveWithArrowsTest extends ApplicationTest {
    Scene scene;
    Player player;
    MoveWithArrows moveWithArrows;

    @Override
    public void start(Stage stage) {
        AnchorPane anchorPane = new AnchorPane();
        player = new Player(400, 550, 100, 10);
        anchorPane.getChildren().add(player);
        anchorPane.setBottomAnchor(player, 20.0);
        
        scene = new Scene(anchorPane, 800, 600);
        moveWithArrows = new MoveWithArrows(scene, player);
        stage.setScene(scene);
        stage.show();
    }


    @Test
    public void moveLeftShouldDecreaseLayoutXByFifteen() {
        // Act
        this.moveWithArrows.moveLeft(player);

        // Assert
        assertTrue(player.getLayoutX() == 385);
    }

    @Test
    public void moveRightShouldIncreaseLayoutXByFifteen() {
        // Act
        moveWithArrows.moveRight(player);

        // Assert
        assertTrue(player.getLayoutX() == 415);
    }

    @Test
    public void playerShouldMoveLeftByFifteenWhenLeftArrowKeyPressed()
    {
        // Act
        push(KeyCode.LEFT);

        // Assert
        assertTrue(player.getLayoutX() == 385);
    }

    @Test
    public void playerShouldMoveRightByFifteenWhenRightArrowKeyPressed()
    {
        // Act
        push(KeyCode.RIGHT);

        // Assert
        assertTrue(player.getLayoutX() == 415);
    }

    @Test
    public void playerShouldNotGoOutOfBoundsOfScreenFromLeft()
    {
        // Arrange
        player.setLayoutX(30);
        clickOn(scene);

        // Guard Act & Assert
        push(KeyCode.LEFT);
        assertEquals(15, player.getLayoutX());
        
        // Act
        push(KeyCode.LEFT);

        // Assert
        assertEquals(15, player.getLayoutX());
    }

    @Test
    public void playerShouldNotGoOutOfBoundsOfScreenFromRight()
    {
        // Arrange 
        double screenWidth = scene.getWidth();
        double maxRightX = screenWidth - player.getWidth();
        player.setLayoutX(maxRightX - 15);
        clickOn(scene);

        // Guard Act & Assert
        push(KeyCode.RIGHT);
        assertEquals(maxRightX, player.getLayoutX());
        
        // Act
        push(KeyCode.RIGHT);

        // Assert
        assertEquals(maxRightX, player.getLayoutX());
    }
}