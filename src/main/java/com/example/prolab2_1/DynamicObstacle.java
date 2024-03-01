package com.example.prolab2_1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DynamicObstacle extends ObstacleBase{
    enum DynamicObstacles {
        Bee,
        Bird
    }
    DynamicObstacle (String imagePath) throws FileNotFoundException {
        sizeX = 2;
        sizeY = 2;
        this.imagePath = new FileInputStream(imagePath);
        image = new Image(imagePath);
        imageView = new ImageView(image);
    }
}
