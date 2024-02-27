package com.example.prolab2_1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StaticObstacle extends ObstacleBase{
    StaticObstacle(String imagePath, int sizeX, int sizeY,int coordX,int coordY) throws FileNotFoundException {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.coordX = coordX;
        this.coordY = coordY;
        this.imagePath = new FileInputStream(imagePath);
        image = new Image(this.imagePath);
        imageView = new ImageView(image);
        imageView.setX(coordX*20);
        imageView.setY(coordY*20);
        imageView.setFitHeight(sizeY * 19.6);
        imageView.setFitWidth(sizeX * 19.6);
    }
}
