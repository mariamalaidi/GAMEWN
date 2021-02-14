package com.world.game;

import com.world.game.entity.Player;
import javax.swing.*;
import java.awt.*;

public class GameLauncher {

    public static GameLauncher createGameLauncher() {
        return new GameLauncher();
    }

    private GameLauncher() {
        new Window();
    }

    public static void main(String[] args) {
        GameLauncher gameLauncher = GameLauncher.createGameLauncher();
        JFrame frame=new JFrame("Enter you Name");
        JButton button=new JButton("Submit");
        button.setBounds(100,100,140, 40);
        JLabel label = new JLabel();
        label.setText("Enter Name :");
        label.setBounds(10, 10, 100, 100);
        JLabel label1 = new JLabel();
        label1.setBounds(10, 110, 200, 100);
        JTextField textfield= new JTextField();
        textfield.setBounds(110, 50, 130, 30);
        frame.add(label1);
        frame.add(textfield);
        frame.add(label);
        frame.add(button);
        frame.setLocation(400   ,400);
        frame.setSize(300,300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(arg0 -> {
            Player.name = textfield.getText();
            frame.dispose();
        });

    }
}

