package org.wintrisstech.projects.pong;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class PongGame extends JComponent implements Runnable, ActionListener
{

    private int width = 800;
    private int height = 600;
    private JFrame frame;
    private Paddle paddle;
    private Ball ball;
    private Timer updateTimer;
    private int score = 0;
    private URL soundAddress = getClass().getResource("fire.wav"); // Find the address of the sound file.
    private AudioClip boing = Applet.newAudioClip(soundAddress); // Convert the sound file to an AudioClip that Java can use.

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new PongGame());
    }

    public void run()
    {
        paddle = new Paddle();
        ball = new Ball();
        ball.setCourtDimension(width, height);
        ball.setPaddle(paddle);

        setPreferredSize(new Dimension(width, height));
        addMouseMotionListener(paddle); // the paddle listens to mouse movements

        frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(paddle); // the paddle listens to key events
        frame.addKeyListener(ball); // the ball also listens to key events
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        updateTimer = new Timer(20, this); // the pong game listens to updateTimer events
        updateTimer.start();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == updateTimer)
        {
            tickUpdate();
        }
    }

    private void tickUpdate()
    {
        paddle.tickUpdate();
        ball.tickUpdate();
        if (ball.outOfBounds)
        {
            updateTimer.stop();
            int playAgain = JOptionPane.showConfirmDialog(frame,
                    "Your score is " + score + ".\nDo you want to play again?",
                    "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (playAgain == JOptionPane.OK_OPTION)
            {
                reset();
                updateTimer.restart();
            } else
            {
                System.exit(0); //Graceful exit
            }
        }

        if (ball.hitPaddle)
        {
            score++;
            boing.play();
            ball.hitPaddle = false;
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setColor(Color.WHITE);
        g2.drawRect(0, 0, width, height);
        paddle.paintSelf(g2);
        ball.paintSelf(g2);
    }

    private void reset()
    {
        score = 0;
        paddle.reset();
        ball.reset();
    }
}
