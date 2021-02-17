package com.world.game;
import com.world.game.entity.MultiPlayer;
import com.world.game.network.GameClient;
import com.world.game.network.GameServer;
import com.world.game.network.packet.Packet00Login;
import com.world.game.state.GameStateManger;
import com.world.game.util.KeyHandler;
import com.world.game.util.MouseHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable, Active{
    public static int widthOfGameArea;
    public static int heightOfGameArea;
    public static int oldFrameCount;
    public static Graphics2D graphics2DOFGameWorld;
    private GameStateManger gameStateManger;
    private BufferedImage imageOfGameArea;
    private boolean running = false;
    private MouseHandler mouseHandler;
    private KeyHandler keyHandler;
    private Thread thread;
    private GameClient clientSocket;
    private GameServer serverSocket;
    private MultiPlayer player;
    private static GamePanel INSTANCE;
    private ArrayList<MultiPlayer> connectedPlayers;

    synchronized public static GamePanel getInstance(int widthOfGameArea, int heightOfGameArea){
        if(INSTANCE == null){
            INSTANCE = new GamePanel(widthOfGameArea, heightOfGameArea);
        }
        return INSTANCE;
    }

    private GamePanel(int widthOfGameArea, int heightOfGameArea) {
        connectedPlayers = new ArrayList<>();
        GamePanel.widthOfGameArea = widthOfGameArea;
        GamePanel.heightOfGameArea = heightOfGameArea;
        setPreferredSize(new Dimension(widthOfGameArea, heightOfGameArea));
        setFocusable(true);
        requestFocus();
    }

    public GameStateManger getGameStateManger(){
        return gameStateManger;
    }

    private void initSockets(){
        if(JOptionPane.showConfirmDialog(this, "Do you want to run this server")==0) {
            serverSocket = new GameServer(this);
            serverSocket.start();
        }
        clientSocket = new GameClient(this, "localhost");
        clientSocket.start();
        Packet00Login login = new Packet00Login( JOptionPane.showInputDialog(this, "Please enter a username"));
        if(serverSocket != null){
            serverSocket.addConnection(player, login);
        }
        clientSocket.sendData("ping".getBytes());
        login.writeData(clientSocket);

    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this, "Game Thread");
            thread.start();
        }
    }

    public void init()  {
        running = true;
        imageOfGameArea = new BufferedImage(widthOfGameArea, heightOfGameArea, BufferedImage.TYPE_INT_RGB);
        graphics2DOFGameWorld = (Graphics2D) imageOfGameArea.getGraphics();
        mouseHandler = MouseHandler.createMouseHandler(this);
        keyHandler = KeyHandler.createKeyHandler(this);
        gameStateManger = GameStateManger.createGameStateManger();
        initSockets();
    }

    @Override
    public void run() {
        init();
        final double GAME_HERTZ = 60.0;
        final double TIME_BEFORE_UPDATE = 1000000000 / GAME_HERTZ;
        final int MOST_UPDATE_BEFORE_RENDER = 3;
        double lastUpdateTime = System.nanoTime();
        double lastRenderedTime;
        final double TARGET_FPS = 60;
        final double TOTAL_TIME_BEFORE_RENDER = 1000000000 / TARGET_FPS;
        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        oldFrameCount = 0;

        while (running) {
            double timeRightNow = System.nanoTime();
            int updateCount = 0;
            while (((timeRightNow - lastUpdateTime) > TIME_BEFORE_UPDATE) && (updateCount < MOST_UPDATE_BEFORE_RENDER)) {
                update();
                input(mouseHandler, keyHandler);
                lastUpdateTime += TIME_BEFORE_UPDATE;
                updateCount++;
            }
            if ((timeRightNow - lastUpdateTime) > TIME_BEFORE_UPDATE) {
                lastUpdateTime = timeRightNow - TIME_BEFORE_UPDATE;
            }

            input(mouseHandler, keyHandler);
            render();
            lastRenderedTime = timeRightNow;
            frameCount++;
            draw();

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount) {
                    System.out.println("New Second " + thisSecond + " Frames " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (timeRightNow - lastRenderedTime < TOTAL_TIME_BEFORE_RENDER & timeRightNow - lastUpdateTime < TIME_BEFORE_UPDATE) {
                Thread.yield();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Thread yielding");
                }
                timeRightNow = System.nanoTime();
            }
        }

    }

    public  int getFPS(){
        return oldFrameCount;
    }

    public int getWidthOfGameArea(){
        return widthOfGameArea;
    }

    public int getHeightOfGameArea(){
        return heightOfGameArea;
    }
    public void update() {
        gameStateManger.update();
    }

    public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
        gameStateManger.input(mouseHandler, keyHandler);
    }

    public void render() {
        if (graphics2DOFGameWorld != null) {
            graphics2DOFGameWorld.setColor(new Color(195, 148, 199));
            graphics2DOFGameWorld.fillRect(0, 0, widthOfGameArea, heightOfGameArea);
            gameStateManger.render(graphics2DOFGameWorld);
        }
    }

    public void draw() {
        Graphics graphics = this.getGraphics();
        graphics.drawImage(imageOfGameArea, 0, 0, widthOfGameArea, heightOfGameArea, null);
        graphics.dispose();
    }

    public String toString(){
        return "Width Of The GamePanel : "+
                widthOfGameArea+"/nHeight Of the Game Panel : "+heightOfGameArea;
    }
}
