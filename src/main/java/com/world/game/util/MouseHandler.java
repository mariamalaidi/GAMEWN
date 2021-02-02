package com.world.game.util;

import com.world.game.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mousePressed = -1;

    public MouseHandler(GamePanel gamePanel){
        gamePanel.addMouseListener(this);
    }

    public static int getMouseX() {
        return mouseX;
    }


    public static int getMouseY() {
        return mouseY;
    }


    public static int getMousePressed() {
        return mousePressed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
