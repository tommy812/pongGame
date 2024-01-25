package main;

import java.awt.*;

public class Player {
    int x;
    int y;
    int score=0;
    int width;
    int height;
    Rectangle area;
    int speed=10;


    public Player(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setRect();
    }

    public void setRect(){
        area = new Rectangle(x,y,width,height);
    }
}
