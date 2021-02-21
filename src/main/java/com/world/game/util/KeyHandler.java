package com.world.game.util;

import com.world.game.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener {
    public static List<Key> keys = new ArrayList<>();
    public static KeyHandler createKeyHandler(GamePanel gamePanel){
        return new KeyHandler(gamePanel);
    }
    private KeyHandler(GamePanel gamePanel){
            gamePanel.addKeyListener(this);
    }

    public class Key{
        public int presses, absorbs;
        public boolean down, clicked;

        public Key(){
            keys.add(this);
        }

        public void toggle(boolean pressed){
            if(pressed != down){
                down = pressed;
            }
            if (pressed) {
                presses++;
            }
        }

        public void tick(){
            if(absorbs < presses){
                absorbs++;
                clicked = true;
            }
            else {
                clicked = false;
            }
        }
    }

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key attack = new Key();
    public Key menu = new Key();
    public Key enter = new Key();
    public Key escape = new Key();

    public void releaseAllKeys(){
        for (Key key : keys) {
            key.down = false;
        }
    }

    public void tick(){
        for (Key key : keys) {
            key.tick();
        }
    }

    public void toggle(KeyEvent keyEvent, boolean pressed){
        if(keyEvent.getKeyChar()== 'w') up.toggle(pressed);
        if(keyEvent.getKeyChar() == 's') down.toggle(pressed);
        if(keyEvent.getKeyChar() == 'a') left.toggle(pressed);
        if(keyEvent.getKeyChar() == 'd') right.toggle(pressed);
        if(keyEvent.getKeyChar() == ' ') attack.toggle(pressed);
        if(keyEvent.getKeyChar() == 'e') menu.toggle(pressed);
        if(keyEvent.getKeyChar() == '\n') enter.toggle(pressed);
        if(keyEvent.getKeyChar() == '\\') escape.toggle(pressed);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //No typing in this game
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        toggle(keyEvent, true);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        toggle(keyEvent, false);
    }
}
