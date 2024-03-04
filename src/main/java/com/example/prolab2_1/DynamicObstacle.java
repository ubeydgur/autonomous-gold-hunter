package com.example.prolab2_1;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

enum DynamicObstacles {
    BEE,
    BIRD
}

public class DynamicObstacle extends ObstacleBase{
    private TranslateTransition translateObstacle = new TranslateTransition();
    private String species;

    DynamicObstacle (String imagePath, String species, int sizeX, int sizeY) throws FileNotFoundException {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.species = species;
        this.imagePath = new FileInputStream(imagePath);
        image = new Image(this.imagePath);
        imageView = new ImageView(image);
        imageView.setFitHeight(2 * 10);
        imageView.setFitWidth(2 * 10);

        if (species == "Bee"){
            translateObstacle.setNode(imageView);
            translateObstacle.setDuration(Duration.millis(1000));
            translateObstacle.setCycleCount(TranslateTransition.INDEFINITE);
            translateObstacle.setByX(10);
            translateObstacle.setAutoReverse(true);
            translateObstacle.play();
        }
        else {
            translateObstacle.setNode(imageView);
            translateObstacle.setDuration(Duration.millis(1000));
            translateObstacle.setCycleCount(TranslateTransition.INDEFINITE);
            translateObstacle.setByY(30);
            translateObstacle.setAutoReverse(true);
            translateObstacle.play();
        }
    }
}
