package com.world.game;

public class GameLauncher {

    public static Window window;
    public static GameLauncher createGameLauncher() {
        return new GameLauncher();
    }

    private GameLauncher() {
        window = new Window();
    }

    public static void main(String[] args) {
         GameLauncher gameLauncher = GameLauncher.createGameLauncher();

    }

}

