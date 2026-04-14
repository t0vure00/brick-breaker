package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BallTest {
    private double screenHeight = 600;
    private double screenWidth = 800;
    private Player player = new Player((int)screenWidth/2 - 50, (int)screenHeight - 30, 100, 20);

    @Test
    public void testBallInitialization() {
        Ball ball = new Ball((int)screenWidth/2, (int)screenHeight/2, 10, screenWidth, screenHeight);
        assert ball.getCenterX() == screenWidth/2;
        assert ball.getCenterY() == screenHeight/2;
    }

    @Test
    public void ballUpdateShouldUpdatePositionBasedOnVelocity() {
        // Arrange
        Ball ball = new Ball((int)screenWidth/2, (int)screenHeight/2, 10, screenWidth, screenHeight);
        double initialVx = 4.0;
        double initialVy = 4.0;

        //Act
        ball.update(this.player);

        // Assert
        assert ball.getCenterX() == (screenWidth/2 + initialVx);
        assert ball.getCenterY() == (screenHeight/2 + initialVy);
    }

    @Test
    public void ballUpdateShouldChangeVelocityXWhenHittingRightBoundary() {
        // Arrange
        Ball ball = new Ball((int)screenWidth-14, (int)screenHeight/2, 10, screenWidth, screenHeight);

        //Guard Act
        ball.update(this.player);

        // Guard Assert
        assertEquals(screenWidth - 10, ball.getCenterX());
        assertEquals(screenHeight/2 + 4, ball.getCenterY());

        // Act
        ball.update(this.player);

        // Assert
        assertEquals(screenWidth - 14, ball.getCenterX());
        assertEquals(screenHeight/2 + 8, ball.getCenterY());
    }

    @Test
    public void ballUpdateShouldNotMoveAfterHittingBottomBoundary() {
        // Arrange
        Ball ball = new Ball((int)screenWidth/2, (int)screenHeight-14, 10, screenWidth, screenHeight);
     
        //Guard Act
        ball.update(this.player);

        // Guard Assert
        assertEquals(screenWidth/2 + 4, ball.getCenterX());
        assertEquals(screenHeight - 10, ball.getCenterY());

        // Act
        ball.update(this.player);

        // Assert
        assertEquals(screenWidth/2 + 4, ball.getCenterX());
        assertEquals(screenHeight - 10, ball.getCenterY());
    }

    @Test
    public void ballUpdateShouldNotMoveAfterHittingBottomAndLeftBoundary() {
        // Arrange
        Ball ball = new Ball(14, (int)screenHeight-14, 10, screenWidth, screenHeight);
        ball.setVelocity(-4.0, 4.0);
     
        //Guard Act
        ball.update(this.player);

        // Guard Assert
        assertEquals(10, ball.getCenterX());
        assertEquals(screenHeight - 10, ball.getCenterY());

        // Act
        ball.update(this.player);

        // Assert
        assertEquals(10, ball.getCenterX());
        assertEquals(screenHeight - 10, ball.getCenterY());
    }

    @Test
    public void ballUpdateShouldNotMoveAfterHittingBottomAndRightBoundary() {
        // Arrange
        Ball ball = new Ball((int)screenWidth-14, (int)screenHeight-14, 10, screenWidth, screenHeight);
        ball.setVelocity(4.0, 4.0);
     
        //Guard Act
        ball.update(this.player);

        // Guard Assert
        assertEquals(screenWidth - 10, ball.getCenterX());
        assertEquals(screenHeight - 10, ball.getCenterY());

        // Act
        ball.update(this.player);

        // Assert
        assertEquals(screenWidth - 10, ball.getCenterX());
        assertEquals(screenHeight - 10, ball.getCenterY());
    }

    @Test
    public void ballUpdateShouldChangeVelocityXYWhenHittingTopAndLeftBorder() {
        // Arrange
        Ball ball = new Ball(14, 14, 10, screenWidth, screenHeight);
        ball.setVelocity(-4.0, -4.0);

        //Guard Act
        ball.update(this.player);

        // Guard Assert
        assertEquals(10, ball.getCenterX());
        assertEquals(10, ball.getCenterY());

        // Act
        ball.update(this.player);

        // Assert
        assertEquals(14, ball.getCenterX());
        assertEquals(14, ball.getCenterY());
    }

    @Test
    public void ballUpdateShouldChangeVelocityXYWhenHittingTopAndRightBorder() {
        // Arrange
        Ball ball = new Ball((int)screenWidth - 14, 14, 10, screenWidth, screenHeight);
        ball.setVelocity(4.0, -4.0);

        //Guard Act
        ball.update(this.player);

        // Guard Assert
        assertEquals(screenWidth - 10, ball.getCenterX());
        assertEquals(10, ball.getCenterY());

        // Act
        ball.update(this.player);

        // Assert
        assertEquals(screenWidth - 14, ball.getCenterX());
        assertEquals(14, ball.getCenterY());
    }
}