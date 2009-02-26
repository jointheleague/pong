package org.wintrisstech.projects.pong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

public class Painter extends JComponent
{
    public PongGame game;

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
        g2.drawString("" + game.score, 1900, 300);

        g2.setColor(Color.red);
        g2.drawString("Ball Speed Is     " + game.ballXspeedInt, 1700, 370);

        g2.setColor(Color.black); // Ball outline.
        g2.setStroke(new BasicStroke(6f));
        g2.draw(game.ball);

        g2.setColor(Color.yellow); // Fill ball.
        g2.fill(game.ball);

        g2.setColor(Color.black);
        g2.draw(game.paddle);
        g2.setColor(Color.gray);
        g2.fill(game.paddle);

        game.ball.x = game.ball.x + game.ballXspeedInt; // The way to explain what is happening.
        game.ball.y += game.ballYspeedInt; // The way the "Big Boys" do it.

        if (game.ball.x > 1900) // These are good brain burners for the kids.  They can usually figure it out.  Explain why it can't be 1500 if the screen width is 1500 because of ball bounding box.
        {
            game.ballXspeedInt = -game.ballXspeedInt;
        }

        if (game.ball.y > 900)
        {
            game.ballYspeedInt = -game.ballYspeedInt;
        }

        if (game.ball.y < 0)
        {
            game.ballYspeedInt = -game.ballYspeedInt;
        }

        if(game.ball.x < 0)
        {
            g2.setColor(Color.BLACK); // Fun stuff for the kids.
            g2.setFont(new Font("Ariel", Font.BOLD, 300));
            g2.drawString("YOU LOSE!",50, 500);
        }

        if (game.ball.intersects(game.paddle))
        {
            game.ballXspeedInt = -game.ballXspeedInt;
            game.score += 1;
            game.ball.x += 5; // To keep the ball from sticking to the paddle
            game.boing.play();
        }

        repaint();
    }

}
