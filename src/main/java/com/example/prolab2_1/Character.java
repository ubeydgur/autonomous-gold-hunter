package com.example.prolab2_1;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

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
    int currentRectangleIndex;
    int maxStraigthWay;
    int minStraigthWay;
    int randomStraigthWay;
    int currentStraightWay = 0;
    InputStream imagePath;
    ImageView imageView;
    Image image;
    MotionDirection frontDirection;
    MotionDirection backDirection;
    ArrayList<MotionDirection> emptyDirections = new ArrayList<>();
    ArrayList<MotionDirection> lastFourDirections = new ArrayList<>();


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


    MotionDirection getDirection(int windowWidth, int windowHeight, int rectangleAndGapSize, ArrayList<RectangleInfo> rectanglesInfo) {
        emptyDirections.clear();
        boolean isIteratedPath = false;
        if (lastFourDirections.size() == 4){
            isIteratedPath = true;
            search :
            for (int i = 0; i < lastFourDirections.size() -1; i++)
            {
                for (int j = i+1; j < lastFourDirections.size(); j++)
                {
                    if (lastFourDirections.get(i) == lastFourDirections.get(j)){
                        isIteratedPath = false;
                        lastFourDirections.removeFirst();
                        break search;
                    }
                }
            }
        }

        if ((currentRectangleIndex - windowWidth / rectangleAndGapSize) >= 0 &&
                rectanglesInfo.get(currentRectangleIndex - windowWidth / rectangleAndGapSize).isPlayerMoved && backDirection != MotionDirection.UP) {
            emptyDirections.add(MotionDirection.UP);
        }
        if ((currentRectangleIndex + windowWidth / rectangleAndGapSize) < (windowWidth / rectangleAndGapSize * windowHeight / rectangleAndGapSize) &&
                rectanglesInfo.get(currentRectangleIndex + windowWidth / rectangleAndGapSize).isPlayerMoved && backDirection != MotionDirection.DOWN) {
            emptyDirections.add(MotionDirection.DOWN);
        }
        if ((currentRectangleIndex - 1 > currentRectangleIndex - (currentRectangleIndex % (windowWidth / rectangleAndGapSize)) - 1) &&
                rectanglesInfo.get(currentRectangleIndex - 1).isPlayerMoved && backDirection != MotionDirection.LEFT) {
            emptyDirections.add(MotionDirection.LEFT);
        }
        if ((currentRectangleIndex + 1 < currentRectangleIndex + ((windowWidth / rectangleAndGapSize) - currentRectangleIndex % (windowWidth / rectangleAndGapSize))) &&
                rectanglesInfo.get(currentRectangleIndex + 1).isPlayerMoved && backDirection != MotionDirection.RIGHT) {
            emptyDirections.add(MotionDirection.RIGHT);
        }

        if (emptyDirections.size() == 1 && emptyDirections.getFirst() == frontDirection) {
            currentStraightWay = 0;
            return frontDirection;
        }
        else {
            if (isIteratedPath) {
                emptyDirections.remove(lastFourDirections.getFirst());
                lastFourDirections.removeFirst();
            }
            emptyDirections.remove(frontDirection);
        }

        if (emptyDirections.isEmpty()) {
            currentStraightWay = 0;
            return backDirection;
        }

        currentStraightWay = 0;
        Random random = new Random();
        randomStraigthWay = random.nextInt(minStraigthWay) + (maxStraigthWay - minStraigthWay);
        int randomDirectionIndex = random.nextInt(emptyDirections.size());
        MotionDirection newDirection = emptyDirections.get(randomDirectionIndex);
        lastFourDirections.add(newDirection);

        return newDirection;
    }
}
