//Imports
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class GameViewer 
{
    //Frame Variables
    private final int frameWidth;
    private final int frameHeight;
    private boolean gameRunning;
    private JFrame frame;
    private JPanel panel;
    
    //Lives Variables
    private ArrayList<Heart> heartList;
    private int numOfLives;
    
    //Game Over Variables
    private JLabel gameOverTxt;
    
    ////Player Variables
    private JLabel player;
    private KeyListener playerKeyListener;
    
    //Enemy Variables
    private ArrayList<Enemy> enemyList;
    private ActionListener enemyMover;
    private Timer enemyTimer;

    //Background Variables
    private JLabel background;
    
    public GameViewer()
    {
        //Frame Setup
        gameRunning = true;
        frameWidth = 600;
        frameHeight = 600;
        frame = new JFrame();
        frame.setSize(frameWidth, frameHeight);
        frame.setTitle("Potato Boy Aventure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new BorderLayout());
        
        //Hearts
        heartList = new ArrayList<Heart>();
        numOfLives = 3;
        for(int i = 0; i < numOfLives; i++)
        {
            Heart heart = new Heart();
            if(i == 0)
            {
                heart.setLocation(20,20);
            }
            else
            {
                heart.setLocation(heartList.get(i - 1).getX() + 50, 20);
            }
            panel.add(heart);
            heartList.add(heart);
        }
        
        
        //Game Over Screen
        gameOverTxt = new GameOver();
        gameOverTxt.setLocation(frame.getWidth() + 100, frame.getHeight() + 100);
        panel.add(gameOverTxt);
        
        
        //Player
        player = new Player();
        player.setLocation(250,250);
        panel.add(player);
        
        //Player movement
        class PlayerKeyListener implements KeyListener
        {
            public void keyPressed(KeyEvent event)
            {
                if(gameRunning == true)
                {
                    int key = event.getKeyCode();
                    if(key == KeyEvent.VK_LEFT)
                    {
                        if(player.getX() - 10 > -60)
                        {
                            player.setLocation(player.getX() - 10, player.getY());
                        }
                    }
                    else if(key == KeyEvent.VK_RIGHT)
                    {
                        if(player.getX() + 60 < frame.getWidth())
                        {
                            player.setLocation(player.getX() + 10, player.getY());
                        }
                    }
                    else if(key == KeyEvent.VK_UP)
                    {
                        if(player.getY() - 10 > -60)
                        {
                            player.setLocation(player.getX(), player.getY() - 10);
                        }
                    }
                    else if(key == KeyEvent.VK_DOWN)
                    {
                        if(player.getY() + 10 < frame.getHeight() - 60)
                        {
                            player.setLocation(player.getX(), player.getY() + 10);
                        }
                    }
                }
            }
            public void keyTyped(KeyEvent event){}
            public void keyReleased(KeyEvent event){}
        }
        playerKeyListener = new PlayerKeyListener();
        frame.addKeyListener(playerKeyListener);
        
        
        //Enemy
        enemyList = new ArrayList<Enemy>();
        for(int i = 0; i < 3; i++)
        {
            Enemy enemy = new Enemy();
            if(i == 0)
            {
                enemy.setLocation(20,90);  
            }
            else if(i == 1)
            {
                enemy.setLocation(500,90);
            }
            else
            {
                enemy.setLocation(20,500);
            }
            panel.add(enemy);
            enemyList.add(enemy);
        }
        
        //Enemy Movement, Player Hit, and Game Over
        class EnemyListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if(gameRunning == true)
                {
                    for(int i = 0; i < enemyList.size(); i++)
                    {
                        int direction = (int)(Math.random()*4) + 1;
                        //Moves Enemy Up
                        if(direction == 1)
                        {
                            if(enemyList.get(i).getY() - 60 > 0)
                            {
                                enemyList.get(i).setLocation(enemyList.get(i).getX(), enemyList.get(i).getY() - 4);
                            }
                        }
                        //Moves Enemy Right
                        else if(direction == 2)
                        {
                            if(enemyList.get(i).getX() + 60 < frame.getWidth())
                            {
                                enemyList.get(i).setLocation(enemyList.get(i).getX() + 4, enemyList.get(i).getY());
                            }        
                        }
                        //Moves Enemy Down
                        if(direction == 3)
                        {
                            if(enemyList.get(i).getY() + 60 < frame.getHeight())
                            {
                                enemyList.get(i).setLocation(enemyList.get(i).getX(), enemyList.get(i).getY() + 4);
                            }
                        }
                        //Moves Enemy Left
                        if(direction == 4)
                        {
                            if(enemyList.get(i).getX() - 60 > 0)
                            {
                                enemyList.get(i).setLocation(enemyList.get(i).getX() - 4, enemyList.get(i).getY());
                            }
                        }
                        if(enemyList.get(i).getBounds().intersects(player.getBounds()))
                        {
                            //Player Hit
                            numOfLives -= 1;
                            if(numOfLives >= 0)
                            {
                                heartList.get(numOfLives).setLocation(-100,-100);
                                enemyList.get(i).setLocation(250,250);
                                //Game Over Sequence
                                if(numOfLives == 0)
                                {
                                    gameRunning = false;
                                    for(int j = 0; j < enemyList.size(); j++)
                                    {
                                        enemyList.get(j).setLocation(-100,-100);
                                    }
                                    player.setLocation(-100,-200);
                                    gameOverTxt.setLocation(-100,0);
                                }
                                else
                                {
                                    enemyList.get(i).setLocation(500,500);
                                }
                            }
                        }
                    }
                }
            }
        }
        enemyMover = new EnemyListener();
        enemyTimer = new Timer(6, enemyMover);
        enemyTimer.start();
        
        
        //Background
        background = new Background();
        panel.add(background);
        
        
        //Sets Frame to Visable and Adds Panel to Frame
        frame.add(panel);
        frame.setVisible(true);
    }
    public static void main(String[] args)
    {
        GameViewer GUI = new GameViewer();
    }
}