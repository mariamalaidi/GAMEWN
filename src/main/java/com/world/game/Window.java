package com.world.game;

import javax.swing.*;

public class Window extends JFrame {
    GamePanel gamePanel;
    public Window(){
        gamePanel = GamePanel.getInstance(1280,720);
        setTitle("World Navigator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
