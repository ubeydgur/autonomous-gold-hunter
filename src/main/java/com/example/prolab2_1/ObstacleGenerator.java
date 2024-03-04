package com.example.prolab2_1;

import java.io.FileNotFoundException;

public class ObstacleGenerator {
    public StaticObstacle generateSummerObstacle(SummerObstacles summerObstacle) throws FileNotFoundException
    {
        switch (summerObstacle) {
            case WALL:
                return new StaticObstacle("pictures/wall_summer.jpg", 10, 1, "summer");
            case MOUNTAIN:
                return new StaticObstacle("pictures/mountain_summer.png", 15, 15, "summer");
            case TREE2X2:
                return new StaticObstacle("pictures/tree_summer_2x2.png", 2, 2, "summer");
            case TREE3X3:
                return new StaticObstacle("pictures/tree_summer_3x3.png", 3, 3, "summer");
            case TREE4X4:
                return new StaticObstacle("pictures/tree_summer_4x4.png", 4, 4, "summer");
            case TREE5X5:
                return new StaticObstacle("pictures/tree_summer_5x5.png", 5, 5, "summer");
            case ROCK2X2:
                return new StaticObstacle("pictures/rock_summer_2x2.png", 2, 2, "summer");
            case ROCK3X3:
                return new StaticObstacle("pictures/rock_summer_3x3.png", 3, 3, "summer");
        }
        return null;
    }

    public StaticObstacle generateWinterObstacle(WinterObstacles winterObstacle) throws FileNotFoundException {
        switch (winterObstacle) {
            case WALL:
                return new StaticObstacle("pictures/wall_winter.png", 10, 1, "winter");
            case MOUNTAIN:
                return new StaticObstacle("pictures/mountain_winter.png", 15, 15, "winter");
            case TREE2X2:
                return new StaticObstacle("pictures/tree_winter_2x2.png", 2, 2, "winter");
            case TREE3X3:
                return new StaticObstacle("pictures/tree_winter_3x3.png", 3, 3, "winter");
            case TREE4X4:
                return new StaticObstacle("pictures/tree_winter_4x4.png", 4, 4, "winter");
            case TREE5X5:
                return new StaticObstacle("pictures/tree_winter_5x5.png", 5, 5, "winter");
            case ROCK2X2:
                return new StaticObstacle("pictures/rock_winter_2x2.png", 2, 2, "winter");
            case ROCK3X3:
                return new StaticObstacle("pictures/rock_winter_3x3.png", 3, 3, "winter");
        }
        return null;
    }

    public DynamicObstacle generateDynamicObstacle (DynamicObstacles dynamicObstacle) throws FileNotFoundException {
        switch (dynamicObstacle){
            case BEE:
                return new DynamicObstacle("pictures/bee.png", "Bee", 3, 2);
            case BIRD:
                return new DynamicObstacle("pictures/bird.png", "Bird", 2, 5);
        }
        return null;
    }
}
