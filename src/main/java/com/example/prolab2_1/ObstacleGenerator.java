package com.example.prolab2_1;

import java.io.FileNotFoundException;

public class ObstacleGenerator {
    public StaticObstacle generateSummerObstacle(StaticObstacle.SummerObstacles summerObstacle) throws FileNotFoundException
    {
        switch (summerObstacle) {
            case Wall:
                return new StaticObstacle("pictures/wall_summer.jpg", 10, 1, "summer");
            case Mountain:
                return new StaticObstacle("pictures/mountain_summer.png", 15, 15, "summer");
            case Tree2x2:
                return new StaticObstacle("pictures/tree_summer_2x2.png", 2, 2, "summer");
            case Tree3x3:
                return new StaticObstacle("pictures/tree_summer_3x3.png", 3, 3, "summer");
            case Tree4x4:
                return new StaticObstacle("pictures/tree_summer_4x4.png", 4, 4, "summer");
            case Tree5x5:
                return new StaticObstacle("pictures/tree_summer_5x5.png", 5, 5, "summer");
            case Rock2x2:
                return new StaticObstacle("pictures/rock_summer_2x2.png", 2, 2, "summer");
            case Rock3x3:
                return new StaticObstacle("pictures/rock_summer_3x3.png", 3, 3, "summer");
        }
        return null;
    }

    public StaticObstacle generateWinterObstacle(StaticObstacle.WinterObstacles winterObstacle) throws FileNotFoundException {
        switch (winterObstacle) {
            case Wall:
                return new StaticObstacle("pictures/wall_winter.png", 10, 1, "winter");
            case Mountain:
                return new StaticObstacle("pictures/mountain_winter.png", 15, 15, "winter");
            case Tree2x2:
                return new StaticObstacle("pictures/tree_winter_2x2.png", 2, 2, "winter");
            case Tree3x3:
                return new StaticObstacle("pictures/tree_winter_3x3.png", 3, 3, "winter");
            case Tree4x4:
                return new StaticObstacle("pictures/tree_winter_4x4.png", 4, 4, "winter");
            case Tree5x5:
                return new StaticObstacle("pictures/tree_winter_5x5.png", 5, 5, "winter");
            case Rock2x2:
                return new StaticObstacle("pictures/rock_winter_2x2.png", 2, 2, "winter");
            case Rock3x3:
                return new StaticObstacle("pictures/rock_winter_3x3.png", 3, 3, "winter");
        }
        return null;
    }
    public DynamicObstacle generateDynamicObstacle (DynamicObstacle.DynamicObstacles dynamicObstacle) throws FileNotFoundException {
        switch (dynamicObstacle){
            case Bee:
                return new DynamicObstacle("pictures/bee.png", "Bee", 3, 2);
            case Bird:
                return new DynamicObstacle("pictures/bird.png", "Bird", 2, 5);
        }
        return null;
    }
}
