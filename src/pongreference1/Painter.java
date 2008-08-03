package pongreference1;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Painter extends JComponent
{
    public Mouser mouser = new Mouser();
    int ballXposition = 500;
    int ballYposition = 500;
    private Ellipse2D.Double ball = new Ellipse2D.Double(500, 500, 50, 50);
    private Rectangle2D.Double paddle = new Rectangle2D.Double(10, 300, 10, 100);
    private String ballSpeedString = JOptionPane.showInputDialog("Ball Speed?");
    private int ballXspeedInt = Integer.parseInt(ballSpeedString);
    private int ballYspeedInt = Integer.parseInt(ballSpeedString);
    private int score = 0;
    URL boingFile = Painter.class.getResource("fire.wav");
    AudioClip boing = Applet.newAudioClip(boingFile);

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Optionally show how you can clean up the picture with this.

        g2.setColor(Color.green); // It's fun to have a lot of different colors and fonts
        g2.setFont(new Font("Ariel", Font.BOLD, 22));
        g2.drawString("Your Score Is ", 1700, 300);
        g2.setColor(Color.black);
        g2.setFont(new Font("Ariel", Font.ITALIC, 32));
        g2.drawString("" + score, 1900, 300);

        g2.setColor(Color.red);
        g2.drawString("Ball Speed Is     " + ballSpeedString, 1700, 370);

        g2.setColor(Color.black); // Ball outline.
        g2.setStroke(new BasicStroke(6f));
        g2.draw(ball);

        g2.setColor(Color.yellow); // Fill ball.
        g2.fill(ball);

        g2.setColor(Color.black);
        g2.draw(paddle);
        g2.setColor(Color.gray);
        g2.fill(paddle);

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

        if(ball.x < 0)
        {
            g2.setColor(Color.BLACK); // Fun stuff for the kids.
            g2.setFont(new Font("Ariel", Font.BOLD, 300));
            g2.drawString("YOU LOSE!",50, 500);
        }

        if (ball.intersects(paddle))
        {
            ballXspeedInt = -ballXspeedInt;
            score += 1;
            ball.x += 5; // To keep the ball from sticking to the paddle
            boing.play();
        }

        repaint();
    }

    class Mouser implements MouseMotionListener
    {
        public void mouseDragged(MouseEvent arg0)
        {
            //Do nothing.
        }

        public void mouseMoved(MouseEvent mousePosition)
        {
            paddle.y = mousePosition.getY();
        }
    }
}
