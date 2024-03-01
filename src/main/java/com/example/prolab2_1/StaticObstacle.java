package com.example.prolab2_1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StaticObstacle extends ObstacleBase{
    private String season;
    enum SummerObstacles {
        Tree2x2,
        Tree3x3,
        Tree4x4,
        Tree5x5,
        Rock2x2,
        Rock3x3,
        Wall,
        Mountain
    }
    enum WinterObstacles {
        Tree2x2,
        Tree3x3,
        Tree4x4,
        Tree5x5,
        Rock2x2,
        Rock3x3,
        Wall,
        Mountain
    }
    StaticObstacle(String imagePath, int sizeX, int sizeY, String season) throws FileNotFoundException {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.season = season;
        this.imagePath = new FileInputStream(imagePath);
        image = new Image(this.imagePath);
        imageView = new ImageView(image);
        imageView.setFitHeight(sizeY * 10);
        imageView.setFitWidth(sizeX * 10);
    }

    public String getSeason(){
        return season;
    }
}
