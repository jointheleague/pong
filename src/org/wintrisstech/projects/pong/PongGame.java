package org.wintrisstech.projects.pong;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class PongGame implements ActionListener, MouseMotionListener
{
    public Painter painter;
    private JFrame playingField;
    public boolean gameOver = false;
    public Rectangle2D.Double paddle = new Rectangle2D.Double(10, 300, 10, 100);
    public Ellipse2D.Double ball = new Ellipse2D.Double(500, 500, 50, 50);
    public int ballXspeedInt = Integer.parseInt(JOptionPane.showInputDialog("Ball Speed?"));
    public int ballYspeedInt = ballXspeedInt;
    public int score = 0;
    public AudioClip boing = Applet.newAudioClip(Painter.class.getResource("fire.wav"));

    public void startTheGame()
    {
        painter = new Painter(); // Make class Painter into a "real" painter object.
        painter.game = this;

        playingField = new JFrame("PngReference1"); //Make class JFrame a "real" JFrame.
        playingField.setVisible(true);
        playingField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playingField.add(painter); // Let the JFrame know is has an artist who will paint on the JFrame.

        playingField.setSize(2000, 1000); // Set size after adding painter so that paint() gets called initially.

        playingField.addMouseMotionListener(this); //A little abtruse for the students!

        Timer updateTimer = new Timer(20, this);
        updateTimer.start();
    }

    public void actionPerformed(ActionEvent e)
    {
        ball.x = ball.x + ballXspeedInt; // The way to explain what is happening.
        ball.y += ballYspeedInt; // The way the "Big Boys" do it.

        if (ball.x > 1900) // These are good brain burners for the kids.  They can usually figure it out.  Explain why it can't be 1500 if the screen width is 1500 because of ball bounding box.
        {
            ballXspeedInt = -ballXspeedInt;
        }

        if (ball.y > 900)
        {
            ballYspeedInt = -ballYspeedInt;
        }

        if (ball.y < 0)
        {
            ballYspeedInt = -ballYspeedInt;
        }

        if (ball.x < 0)
        {
            gameOver = true;
        }

        if (ball.intersects(paddle))
        {
            ballXspeedInt = -ballXspeedInt;
            score += 1;
            ball.x += 5; // To keep the ball from sticking to the paddle
            boing.play();
        }

        painter.repaint();
    }

    public void mouseDragged(MouseEvent arg0)
    {
        //Do nothing.
    }

    public void mouseMoved(MouseEvent mousePosition)
    {
        paddle.y = mousePosition.getY();
        painter.repaint();
    }
}
