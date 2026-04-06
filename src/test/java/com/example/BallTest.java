package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BallTest {
    private double screenHeight = 600;
    private double screenWidth = 800;
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
        ball.update();

        // Assert
        assert ball.getCenterX() == (screenWidth/2 + initialVx);
        assert ball.getCenterY() == (screenHeight/2 + initialVy);
    }

    @Test
    public void ballUpdateShouldChangeVelocityXWhenHittingRightBoundary() {
        // Arrange
        Ball ball = new Ball((int)screenWidth-14, (int)screenHeight/2, 10, screenWidth, screenHeight);

        //Guard Act
        ball.update();

        // Guard Assert
        assertEquals(screenWidth - 10, ball.getCenterX());
        assertEquals(screenHeight/2 + 4, ball.getCenterY());

        // Act
        ball.update();

        // Assert
        assertEquals(screenWidth - 14, ball.getCenterX());
        assertEquals(screenHeight/2 + 8, ball.getCenterY());
    }

    @Test
    public void ballUpdateShouldChangeVelocityYWhenHittingBottomBoundary() {
        // Arrange
        Ball ball = new Ball((int)screenWidth/2, (int)screenHeight-14, 10, screenWidth, screenHeight);
     
        //Guard Act
        ball.update();

        // Guard Assert
        assertEquals(screenWidth/2 + 4, ball.getCenterX());
        assertEquals(screenHeight - 10, ball.getCenterY());

        // Act
        ball.update();

        // Assert
        assertEquals(screenWidth/2 + 8, ball.getCenterX());
        assertEquals(screenHeight - 14, ball.getCenterY());
    }

    @Test
    public void ballUpdateShouldChangeVelocityXYYWhenHittingTwoBoundares() {
        // Arrange
        Ball ball = new Ball((int)screenWidth-14, (int)screenHeight-14, 10, screenWidth, screenHeight);

        //Guard Act
        ball.update();

        // Guard Assert
        assertEquals(screenWidth - 10, ball.getCenterX());
        assertEquals(screenHeight - 10, ball.getCenterY());

        // Act
        ball.update();

        // Assert
        assertEquals(screenWidth - 14, ball.getCenterX());
        assertEquals(screenHeight - 14, ball.getCenterY());
    }
}