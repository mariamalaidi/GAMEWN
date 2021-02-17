package com.world.game;

public class GameLauncher {

    public static GameLauncher createGameLauncher() {
        return new GameLauncher();
    }

    private GameLauncher() {
        new Window();
    }

    public static void main(String[] args) {
        GameLauncher gameLauncher = GameLauncher.createGameLauncher();
    }
}

