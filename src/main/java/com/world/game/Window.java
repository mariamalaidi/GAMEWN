package com.world.game;

import javax.swing.*;

public class Window extends JFrame {

    GamePanel gamePanel;
    public int width = 1280;
    public int height = 720;
    public Window(){
        gamePanel = GamePanel.getInstance(width,height);
        setTitle("World Navigator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void closeWindow(){
        setVisible(false);
        System.exit(0);
    }
}
