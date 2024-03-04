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
    int characterSizeX;
    int characterSizeY;
    InputStream imagePath;
    ImageView imageView;
    Image image;
    MotionDirection direction;

    public Character(String imagePath, int locationX, int locationY, int characterSizeX, int characterSizeY) throws FileNotFoundException {
        this.locationX = locationX;
        this.locationY = locationY;
        this.characterSizeX = characterSizeX;
        this.characterSizeY = characterSizeY;
        this.imagePath = new FileInputStream(imagePath);
        image = new Image(this.imagePath);
        imageView = new ImageView(image);
        imageView.setX(locationX * 10);
        imageView.setY(locationY * 10);
        imageView.setFitHeight(characterSizeY * 10);
        imageView.setFitWidth(characterSizeX * 10);
    }
}