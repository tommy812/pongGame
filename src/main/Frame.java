package main;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame() {
        //frame settings
        setTitle("2D Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        //Add Panel and start timer
        GamePanel animation = new GamePanel();
        animation.startAnimation();
        add(animation);
        pack();
    }
}
