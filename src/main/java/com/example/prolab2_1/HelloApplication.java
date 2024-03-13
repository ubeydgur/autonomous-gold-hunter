package com.example.prolab2_1;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class RectangleInfo {
    Rectangle rectangle;
    boolean isObstaclePlaced = true;
    boolean isPlayerMoved = true;
    boolean isSeen = false;
    Enum obstacleType;
}

public class HelloApplication extends Application {
    ArrayList<RectangleInfo> rectangleArray = new ArrayList<>();
    Random random = new Random();
    Character arthurMorgan = null;
    int characterSizeX = 1;
    int characterSizeY = 1;
    static int windowHeight = 1000;
    static int windowWidth = 1000;
    static double rectangleSize = 9.5;
    static double gapSize = 0.5;
    static double rectangleAndGapSize = rectangleSize + gapSize;

    @Override
    public void start(Stage stage) throws IOException {
        /*System.out.println("Enter Window Height: ");
        Scanner scanner = new Scanner(System.in);
        windowHeight = scanner.nextInt();
        System.out.println("Enter Window Width: ");
        windowWidth = scanner.nextInt();*/

        int rectangleAmountX = windowHeight / (int)rectangleAndGapSize;
        int rectangleAmountY = windowWidth / (int)rectangleAndGapSize;
        int rectangleTotal = rectangleAmountX * rectangleAmountY;


        // Create Ractangles
        for (double y = 0; y < windowHeight; y += rectangleAndGapSize) {
            for (double x = 0; x < windowWidth; x += rectangleAndGapSize) {
                RectangleInfo rectangleInfo = new RectangleInfo();
                rectangleInfo.rectangle = new Rectangle(x, y, rectangleSize, rectangleSize);
                if (x > windowHeight / 2 - rectangleAndGapSize)
                    rectangleInfo.rectangle.setFill(Color.WHITE);
                else
                    rectangleInfo.rectangle.setFill(Color.WHITE);
                rectangleArray.add(rectangleInfo);
            }
        }


        // Add Enums to ArrayList
        ArrayList<TypeObstacles> typeObstacle = new ArrayList<>();
        typeObstacle.addAll(Arrays.asList(TypeObstacles.values()));

        ArrayList<DynamicObstacles> dynamicObstacle = new ArrayList<>();
        dynamicObstacle.addAll(Arrays.asList(DynamicObstacles.values()));

        // Add Generator and ArrayList for Obstacles and Treasures
        ArrayList<StaticObstacle> staticObstacles = new ArrayList<>();
        ArrayList<DynamicObstacle> dynamicObstacles = new ArrayList<>();
        ArrayList<Treasure> treasures = new ArrayList<>();

        TreasureGenerator treasureGenerator = new TreasureGenerator();
        ObstacleGenerator obstacleGenerator = new ObstacleGenerator();

        // Create Obstacles and Treasures
        int totalStaticObstacle = 20;
        int totalDynamicObstacle = 3;
        int totalTreasure = 5;
        int randomSeason;
        int randomObstacle;
        int staticObstaclesSize;

        // Create Default Static Obstacles
        obstacleGenerator.createDefaultObstacles(staticObstacles);
        staticObstaclesSize = staticObstacles.size();

        // Create Static Obstacles
        for (int i = 0; i < totalStaticObstacle - staticObstaclesSize; i++) {
            randomSeason = random.nextInt(2);
            switch (randomSeason){
                case 0:
                    randomObstacle = random.nextInt(typeObstacle.size());
                    staticObstacles.add(obstacleGenerator.generateWinterObstacle(typeObstacle.get(randomObstacle)));
                    break;
                case 1:
                    randomObstacle = random.nextInt(typeObstacle.size());
                    staticObstacles.add(obstacleGenerator.generateSummerObstacle(typeObstacle.get(randomObstacle)));
                    break;
            }
        }

        // Create Dynamic Obstacles
        for (int i = 0; i < totalDynamicObstacle; i++){
            randomObstacle = random.nextInt(dynamicObstacle.size());
            dynamicObstacles.add(obstacleGenerator.generateDynamicObstacle(dynamicObstacle.get(randomObstacle)));
        }

        // Create Treasures
        for (int i = 0; i < totalTreasure; i++){
            treasures.add(treasureGenerator.goldChest());
            treasures.add(treasureGenerator.silverChest());
            treasures.add(treasureGenerator.emeraldChest());
            treasures.add(treasureGenerator.copperChest());
        }

        // Set Coordinates of Treasures and Obstacles
        ArrayList<RectangleInfo> rectanglesInfo = new ArrayList<>();
        int imageRandomX;
        int imageRandomY;
        int imageRectangleIndex;
        int startImageIndex;
        int obstacleBorderSpace;
        int imageBorderSpace = 0;

        // Set Coordinates of Static Obstacles
        for (int k = 0; k < staticObstacles.size(); k++) {
            search:
            for (int m = 0; m < 1; m++) {
                imageRandomY = random.nextInt(rectangleAmountY - staticObstacles.get(k).sizeY);

                if (staticObstacles.get(k).season == Season.SUMMER)
                    imageRandomX = random.nextInt(rectangleAmountX / 2 - staticObstacles.get(k).sizeX) + rectangleAmountX / 2;
                else
                    imageRandomX = random.nextInt(rectangleAmountX / 2 - staticObstacles.get(k).sizeX);

                if(staticObstacles.get(k).getObstacleType() == TypeObstacles.MOUNTAIN ||
                        staticObstacles.get(k).getObstacleType() ==TypeObstacles.TREE) {
                    obstacleBorderSpace = 0;
                    imageBorderSpace = 0;
                }
                else {
                    obstacleBorderSpace = 2;
                    imageBorderSpace = 3;
                }

                for (int y = 0; y < staticObstacles.get(k).sizeY + obstacleBorderSpace; y++) {
                    for (int x = 0; x < staticObstacles.get(k).sizeX + obstacleBorderSpace; x++) {
                        imageRectangleIndex = (imageRandomX + x) + ((imageRandomY + y) * rectangleAmountX);

                        if (rectangleArray.get(imageRectangleIndex).isObstaclePlaced) {
                            rectanglesInfo.add(rectangleArray.get(imageRectangleIndex));
                        }
                        else {
                            m--;
                            rectanglesInfo.clear();
                            continue search;
                        }
                    }
                }
            }


            for (int i = 0; i < rectanglesInfo.size(); i++) {
                rectanglesInfo.get(i).isObstaclePlaced = false;
                if (staticObstacles.get(k).getObstacleType() == TypeObstacles.TREE ||  staticObstacles.get(k).getObstacleType() == TypeObstacles.MOUNTAIN) {
                    rectanglesInfo.get(i).isPlayerMoved = false;
                    rectanglesInfo.get(i).obstacleType = staticObstacles.get(k).getObstacleType();
                }
            }

            startImageIndex = staticObstacles.get(k).sizeX + 3;
            if (staticObstacles.get(k).getObstacleType() == TypeObstacles.ROCK ||  staticObstacles.get(k).getObstacleType() == TypeObstacles.WALL) {
                for (int j = 1; j <= staticObstacles.get(k).sizeY; j++) {
                    for (int l = 0; l < staticObstacles.get(k).sizeX; l++) {
                        rectanglesInfo.get(l + startImageIndex).isPlayerMoved = false;
                        rectanglesInfo.get(l + startImageIndex).obstacleType = staticObstacles.get(k).getObstacleType();
                    }
                    startImageIndex += staticObstacles.get(k).sizeX + 2;
                }
            }


            staticObstacles.get(k).imageView.setX(rectanglesInfo.get(staticObstacles.get(k).sizeX + imageBorderSpace).rectangle.getX());
            staticObstacles.get(k).imageView.setY(rectanglesInfo.get(staticObstacles.get(k).sizeX + imageBorderSpace - 1).rectangle.getY());
            rectanglesInfo.clear();
        }

        // Set Coordinates of Dynamic Obstacles
        for (int k = 0; k < dynamicObstacles.size(); k++) {
            search:
            for (int m = 0; m < 1; m++) {
                imageRandomY = random.nextInt(rectangleAmountY - dynamicObstacles.get(k).visitFieldY);
                imageRandomX = random.nextInt(rectangleAmountX - dynamicObstacles.get(k).visitFieldX);
                
                for (int y = 0; y < dynamicObstacles.get(k).visitFieldY; y++) {
                    for (int x = 0; x < dynamicObstacles.get(k).visitFieldX; x++) {
                        imageRectangleIndex = (imageRandomX + x) + ((imageRandomY + y) * rectangleAmountX);
                        if (rectangleArray.get(imageRectangleIndex).isObstaclePlaced) {
                            rectanglesInfo.add(rectangleArray.get(imageRectangleIndex));
                        }
                        else {
                            m--;
                            rectanglesInfo.clear();
                            continue search;
                        }
                    }
                }
            }

            for (int i = 0; i < rectanglesInfo.size(); i++) {
                rectanglesInfo.get(i).rectangle.setFill(Color.LIGHTPINK);
                rectanglesInfo.get(i).isObstaclePlaced = false;
                rectanglesInfo.get(i).isPlayerMoved = false;
                rectanglesInfo.get(i).obstacleType = dynamicObstacles.get(k).species;
            }

            dynamicObstacles.get(k).imageView.setX(rectanglesInfo.get(0).rectangle.getX());
            dynamicObstacles.get(k).imageView.setY(rectanglesInfo.get(0).rectangle.getY());
            rectanglesInfo.clear();
        }

        // Set Coordinates of Treasures
        for (int k = 0; k < treasures.size(); k++) {
            search:
            for (int m = 0; m < 1; m++) {
                imageRandomY = random.nextInt(rectangleAmountY - treasures.get(k).sizeY);
                imageRandomX = random.nextInt(rectangleAmountX - treasures.get(k).sizeX);

                for (int y = 0; y < treasures.get(k).sizeY; y++) {
                    for (int x = 0; x < treasures.get(k).sizeX; x++) {
                        imageRectangleIndex = (imageRandomX + x) + ((imageRandomY + y) * rectangleAmountY);
                        if (rectangleArray.get(imageRectangleIndex).isObstaclePlaced) {
                            rectanglesInfo.add(rectangleArray.get(imageRectangleIndex));
                        }
                        else {
                            m--;
                            rectanglesInfo.clear();
                            continue search;
                        }
                    }
                }
            }

            for (int i = 0; i < rectanglesInfo.size(); i++)
                rectanglesInfo.get(i).isObstaclePlaced = false;

            treasures.get(k).imageView.setX(rectanglesInfo.get(0).rectangle.getX());
            treasures.get(k).imageView.setY(rectanglesInfo.get(0).rectangle.getY());
            rectanglesInfo.clear();
        }

        // Create Character
        boolean isCharacterCreated = false;
        int locationX;
        int locationY;

        while (!isCharacterCreated) {
            locationX = random.nextInt(rectangleAmountX - characterSizeX);
            locationY = random.nextInt(rectangleAmountY - characterSizeY);

            if (rectangleArray.get(locationX + locationY  * rectangleAmountY).isObstaclePlaced){
                arthurMorgan = new Character("pictures/bee.png", locationX, locationY, characterSizeX, characterSizeY, (int)rectangleAndGapSize);
                arthurMorgan.currentRectangleIndex = locationX + locationY * rectangleAmountY;
                isCharacterCreated = true;
            }
        }

        // With the tick method, the character is made to move continuously on the screen
        arthurMorgan.specifyDirectionRandomly();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> tick()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        // Add Obstacles, Treasures, Rectangles and Character to Screen
        Group myGroup = new Group();

        for (int i = 0; i < rectangleTotal; i++)
            myGroup.getChildren().add(rectangleArray.get(i).rectangle);

        for (int i = 0; i < staticObstacles.size(); i++)
            myGroup.getChildren().add(staticObstacles.get(i).imageView);

        for (int i = 0; i < dynamicObstacles.size(); i++)
            myGroup.getChildren().add(dynamicObstacles.get(i).imageView);

        for (int i = 0; i < treasures.size(); i++)
            myGroup.getChildren().add(treasures.get(i).imageView);

        myGroup.getChildren().add(arthurMorgan.imageView);


        Scene scene = new Scene(myGroup,windowWidth,windowHeight);
        scene.setFill(Color.BLACK);
        stage.setTitle("AUTONOMOUS GOLD HUNTER");
        stage.setScene(scene);
        stage.show();
    }

    public void tick(){
        arthurMorgan.move(windowWidth,windowHeight);
        arthurMorgan.checkDirection(windowWidth, windowHeight, (int)rectangleAndGapSize, rectangleArray);
    }


    public static void main(String[] args) {
        launch();
    }
}