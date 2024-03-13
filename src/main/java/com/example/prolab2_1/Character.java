package com.example.prolab2_1;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

enum MotionDirection{
    RIGHT,
    LEFT,
    UP,
    DOWN
}

public class Character {
    int locationX;
    int locationY;
    int lastLocationX;
    int lastLocationY;
    int characterSizeX;
    int characterSizeY;
    int maxStraigthWay;
    int minStraigthWay;
    InputStream imagePath;
    ImageView imageView;
    Image image;


    public Character(String imagePath, int locationX, int locationY, int characterSizeX, int characterSizeY, int rectangleAndGapSize, int maxStraigthWay, int minStraigthWay) throws FileNotFoundException {
        this.locationX = locationX;
        this.locationY = locationY;
        this.lastLocationX = locationX * rectangleAndGapSize;
        this.lastLocationY = locationY * rectangleAndGapSize;
        this.characterSizeX = characterSizeX * rectangleAndGapSize;
        this.characterSizeY = characterSizeY * rectangleAndGapSize;
        this.imagePath = new FileInputStream(imagePath);
        image = new Image(this.imagePath);
        imageView = new ImageView(image);
        imageView.setX(locationX * rectangleAndGapSize);
        imageView.setY(locationY * rectangleAndGapSize);
        imageView.setFitHeight(this.characterSizeY);
        imageView.setFitWidth(this.characterSizeX);
        this.maxStraigthWay = maxStraigthWay;
        this.minStraigthWay = minStraigthWay;
    }
}