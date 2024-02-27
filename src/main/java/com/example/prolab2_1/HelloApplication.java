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
import java.util.Random;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        int windowHeight;
        int windowWidth;
        int rectangleSize = 19;
        int gapSize = 1;

        /*System.out.println("Enter Window Height: ");
        Scanner scanner = new Scanner(System.in);
        windowHeight = scanner.nextInt();
        System.out.println("Enter Window Width: ");
        windowWidth = scanner.nextInt();*/


        ArrayList<Rectangle> rectangleArray = new ArrayList<>();
        for (int y = 0; y < 1000; y+=rectangleSize + gapSize){
            for (int x = 0; x < 1000; x+=rectangleSize + gapSize){
                Rectangle rectangle = new Rectangle(x, y, rectangleSize, rectangleSize);
                rectangle.setFill(Color.WHITE);
                rectangleArray.add(rectangle);
            }
        }

        Group myGroup = new Group();
        myGroup.getChildren().addAll(rectangleArray);

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
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