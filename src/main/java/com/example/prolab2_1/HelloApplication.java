package com.example.prolab2_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class InfoRect {
    Rectangle rectangle;
    boolean isAvailable = true;
}

public class HelloApplication extends Application {
    ArrayList<InfoRect> rectangleArray = new ArrayList<>();
    Random random = new Random();



    @Override
    public void start(Stage stage) throws IOException {
        int windowHeight;
        int windowWidth;
        double rectangleSize = 9.5;
        double gapSize = 0.5;

        /*System.out.println("Enter Window Height: ");
        Scanner scanner = new Scanner(System.in);
        windowHeight = scanner.nextInt();
        System.out.println("Enter Window Width: ");
        windowWidth = scanner.nextInt();*/
        double rectAmountOnX = 1000 / (rectangleSize + gapSize);
        double rectAmountOnY = 1000 / (rectangleSize + gapSize);
        double rectTotal = rectAmountOnX * rectAmountOnY;


        for (double y = 0; y < 1000; y+=rectangleSize + gapSize) {
            for (double x = 0; x < 1000; x+=rectangleSize + gapSize) {
                InfoRect infoRect = new InfoRect();
                infoRect.rectangle = new Rectangle(x, y, rectangleSize, rectangleSize);
                infoRect.rectangle.setFill(Color.WHITE);
                rectangleArray.add(infoRect);
            }
        }

        ArrayList<StaticObstacle.SummerObstacles> summerObstacle = new ArrayList<>();
        summerObstacle.addAll(Arrays.asList(StaticObstacle.SummerObstacles.values()));
        ArrayList<StaticObstacle> staticObstacles = new ArrayList<>();

        ArrayList<StaticObstacle.WinterObstacles> winterObstacle = new ArrayList<>();
        winterObstacle.addAll(Arrays.asList(StaticObstacle.WinterObstacles.values()));


        ObstacleGenerator obstacleGenerator = new ObstacleGenerator();
        int randomObstacleIndex;
        int randomTotalObstacle = 20;
        int randomSeason;

        for (int i = 0; i < randomTotalObstacle; i++)
        {
            randomSeason = random.nextInt(2);
            switch (randomSeason){
                case 0:
                    randomObstacleIndex = random.nextInt(winterObstacle.size());
                    staticObstacles.add(obstacleGenerator.generateWinterObstacle(winterObstacle.get(randomObstacleIndex)));
                    break;
                case 1:
                    randomObstacleIndex = random.nextInt(summerObstacle.size());
                    staticObstacles.add(obstacleGenerator.generateSummerObstacle(summerObstacle.get(randomObstacleIndex)));
                    break;
            }
        }

        int imageX;
        int imageY;
        ArrayList<InfoRect> infoRects = new ArrayList<>();

        for (int k = 0; k < staticObstacles.size(); k++) {

            search:
            for (int m = 0; m <1; m++) {
                imageY = random.nextInt(99 - staticObstacles.get(k).sizeY);
                if (staticObstacles.get(k).getSeason() == "summer")
                    imageX = random.nextInt(49 - staticObstacles.get(k).sizeX) + 50;
                else
                    imageX = random.nextInt(49 - staticObstacles.get(k).sizeX);
                for (int y = 0; y < staticObstacles.get(k).sizeY; y++) {
                    for (int x = 0; x < staticObstacles.get(k).sizeX; x++) {
                        int index = (imageX + x) + ((imageY + y) * 100);
                        if (rectangleArray.get(index).isAvailable) {
                            infoRects.add(rectangleArray.get(index));
                        } else {
                            infoRects.clear();
                            m--;
                            continue search;
                        }
                    }
                }
            }

            for (int i = 0; i < infoRects.size(); i++) {
                //infoRects.get(i).rectangle.setFill(Color.RED);
                infoRects.get(i).isAvailable = false;
            }

            staticObstacles.get(k).imageView.setX(infoRects.get(0).rectangle.getX());
            staticObstacles.get(k).imageView.setY(infoRects.get(0).rectangle.getY());
            infoRects.clear();

        }

        Group myGroup = new Group();
        for (int i = 0; i < rectTotal; i++){
            myGroup.getChildren().add(rectangleArray.get(i).rectangle);
        }
        for (int i = 0; i < staticObstacles.size(); i++){
            myGroup.getChildren().add(staticObstacles.get(i).imageView);
        }

        
        Scene scene = new Scene(myGroup,1000,1000/*windowWidth,windowHeight*/);
        scene.setFill(Color.BLACK);
        stage.setTitle("GOLDEN HUNTER");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}