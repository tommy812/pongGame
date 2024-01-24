package main;

import java.awt.*;

public class Player {
    int x;
    int y;
    int Score=0;
    int width;
    int height;
    Rectangle area;


    public Player(int x, int y, int width, int height){
        this.x = x;

        this.y = y;

        this.width = width;

        this.height = height;
        area = new Rectangle(x,y,width,height);
    }
}
