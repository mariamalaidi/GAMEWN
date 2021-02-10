package com.world.game;

import com.world.game.util.KeyHandler;
import com.world.game.util.MouseHandler;

public interface Active {
    public void update();
    public void input(MouseHandler mouseHandler,KeyHandler keyHandler);
    public void draw();
}
