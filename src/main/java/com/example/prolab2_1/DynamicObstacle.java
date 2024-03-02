package com.example.prolab2_1;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DynamicObstacle extends ObstacleBase{
    private String species;
    enum DynamicObstacles {
        Bee,
        Bird
    }
    DynamicObstacle (String imagePath, String species, int sizeX, int sizeY) throws FileNotFoundException {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.species = species;
        this.imagePath = new FileInputStream(imagePath);
        image = new Image(this.imagePath);
        imageView = new ImageView(image);
        imageView.setFitHeight(2 * 10);
        imageView.setFitWidth(2 * 10);
    }
}
