package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel{

    final int LEFTUP = 1;
    final int LEFTDOWN = 2;
    final int RIGHTUP=3;
    final int RIGHTDOWN=4;
    final int DOWNRIGHT=5;
    final int DOWNLEFT=6;


    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize*scale; // 48x48 px tile due to higher monitor definition
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    //final screen size = 769px x 576px
    final int screenWidth = tileSize*maxScreenCol; //768px
    final int screenHeight = tileSize*maxScreenRow; //576px




    private Timer animationTimer;
    KeyHandler keyH = new KeyHandler();


    //Rectangles
    private Rectangle topBorder, bottomBorder,centralLine,leftMargin,rightMargin, player,player2,ball;
    //labels
    JLabel leftScoreLabel= new JLabel("<html> 0 </html>");
    JLabel rightScoreLabel = new JLabel("<html> 0 </html>");




    //set player default position
    int playerX = 0;
    int playerY = screenHeight/2;
    int playerScore=0;

    int player2X = screenWidth-10;
    int player2Y = screenHeight/2;
    int player2Score = 0;
    int playerSpeed = 10;

    //set ball default position
    int ballHeight=10;
    int ballWidth = 10;
    int ballX = screenWidth/2;// - ballWidth/2
    int ballY = screenHeight/2 ;//+ ballHeight/2
    int ballSpeedx = 10;
    int ballSpeedy = 10;
    Random rand = new Random();
    int ballDirection= 1;//rand.nextInt(2);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        player = new Rectangle(playerX,playerY,10,tileSize*2);
        player2 = new Rectangle(player2X,player2Y,10,tileSize*2);
        topBorder = new Rectangle(0,0,screenWidth,tileSize/2);
        bottomBorder = new Rectangle(0,screenHeight-tileSize/2,screenWidth,tileSize/2);
        centralLine = new Rectangle(screenWidth/2,0,4,screenHeight);
        ball = new Rectangle(ballX,ballY,ballWidth,ballHeight);
        leftMargin = new Rectangle(0,0,4,screenHeight);
        rightMargin = new Rectangle(screenWidth-4,0,4,screenHeight);
    }

    //ball requires a direction


    public void update(){
        //animate first player
        if (keyH.wPressed){
            if (!player.intersects(topBorder)) {
                playerY -= playerSpeed;
            }else {
                System.out.println("top hit by p1");
            }

        }
        if (keyH.sPressed){
            if(!player.intersects(bottomBorder)){
                playerY += playerSpeed;
            }else {
                System.out.println("bottom hit by p1");
            }

        }



        //animate the ball
        switch(ballDirection){
            case (LEFTUP):
                if(ball.intersects(leftMargin)){
                    ballX = screenWidth/2;
                    ballY = screenHeight/2;
                    ballDirection=rand.nextInt(3,4);
                    playerScore++;
                } else if (ball.intersects(player)) {
                    //if()
                    ballDirection=RIGHTUP;

                } else if (ball.intersects(topBorder)){
                    ballDirection=LEFTDOWN;
                }
                    else {
                    ballX -= ballSpeedx;
                    ballY -= ballSpeedy;
                }
                break;
            case (LEFTDOWN):
                if(ball.intersects(leftMargin)){
                    ballX = screenWidth/2;
                    ballY = screenHeight/2;
                    ballDirection= rand.nextInt(3,4);
                    playerScore++;
                } else if (ball.intersects(player)) {
                    //if()
                    ballDirection=RIGHTDOWN;
                } else if (ball.intersects(bottomBorder)){
                    ballDirection = LEFTUP;
                }
                else {
                    ballX -= ballSpeedx;
                    ballY += ballSpeedy;
                }
                break;
            case(RIGHTUP):
                if(ball.intersects(rightMargin)){
                    ballX = screenWidth/2;
                    ballY = screenHeight/2;
                    ballDirection= rand.nextInt(2);
                    player2Score++;
                } else if (ball.intersects(player2)) {
                    ballDirection=LEFTUP;
                } else if (ball.intersects(topBorder)){
                    ballDirection = RIGHTDOWN;
                }else {
                    ballX += ballSpeedx;
                    ballY -= ballSpeedy;
                }
                break;
            case(RIGHTDOWN):
                if(ball.intersects(rightMargin)){
                    ballX = screenWidth/2;
                    ballY = screenHeight/2;
                    ballDirection= rand.nextInt(2);
                    player2Score++;
                } else if (ball.intersects(player2)) {
                    ballDirection=LEFTDOWN;
                } else if (ball.intersects(bottomBorder)){
                    ballDirection = RIGHTUP;
                }else {
                    ballX += ballSpeedx;
                    ballY += ballSpeedy;
                }
                break;

        }


        //animate the second player
        if (!player2.intersects(topBorder)){
            if (ballY<player2Y){
                player2Y -= playerSpeed;
            }
        }
        if (!player2.intersects((bottomBorder))){
            if (ballY>player2Y){
                player2Y += playerSpeed;
            }
        }







        /*if (keyH.leftPressed){
            playerX -= playerSpeed;
        }
        if (keyH.rightPressed){
            playerX += playerSpeed;
        }*/
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        player = new Rectangle(playerX,playerY,10,tileSize*2);
        player2 = new Rectangle(player2X,player2Y,10,tileSize*2);
        ball = new Rectangle(ballX,ballY,ballWidth,ballHeight);

        //players
        g2.fillRect(playerX,playerY,10,tileSize*2);
        g2.fillRect(player2X,player2Y,10,tileSize*2);

        //borders
        g2.fillRect(topBorder.x, topBorder.y, topBorder.width, topBorder.height);
        g2.fillRect(bottomBorder.x, bottomBorder.y, bottomBorder.width, bottomBorder.height);

        //central line
        g2.fillRect(centralLine.x, centralLine.y, centralLine.width, centralLine.height);
        g2.setColor(Color.red);

        //ball
        g2.fillRect(ball.x,ball.y,ball.width,ball.height);



        //TODO: change 0 to a score variable.
        leftScoreLabel.setText("<html>"+playerScore+"</html>");
        leftScoreLabel.setForeground(Color.white);
        leftScoreLabel.setVisible(true);
        leftScoreLabel.setLocation((screenWidth/2) - tileSize,tileSize*2);
        leftScoreLabel.setSize(tileSize , tileSize);
        leftScoreLabel.setFont(new Font("Serif", Font.PLAIN, tileSize));
        add(leftScoreLabel);
        rightScoreLabel.setText("<html>"+player2Score+"</html>");
        rightScoreLabel.setForeground(Color.white);
        rightScoreLabel.setVisible(true);
        rightScoreLabel.setLocation((screenWidth/2) + tileSize/2,tileSize*2);
        rightScoreLabel.setSize(tileSize , tileSize);
        rightScoreLabel.setFont(new Font("Serif", Font.PLAIN, tileSize));
        add(rightScoreLabel);

    }


    //timer start
    public void startAnimation() {
        if (animationTimer == null) {
            animationTimer = new Timer(1000/60, new TimeHandler());
            animationTimer.start();
        } else {
            if (!animationTimer.isRunning())
                animationTimer.restart();
        }//ifStatement
    }
    private class TimeHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            update();

            //match.saveMatch();

            repaint();
        }//ActionPerformed()
    }
}

