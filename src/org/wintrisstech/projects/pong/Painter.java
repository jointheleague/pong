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
    protected void paintComponent(Graphics g)
    {
        // This is how you create the g2 object.
        Graphics2D g2 = (Graphics2D) g;

        // Optionally show how you can clean up the picture with this.
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the scoreboard
        g2.setColor(Color.green);
        g2.setFont(new Font("Ariel", Font.BOLD, 22));
        g2.drawString("Your Score Is ", 1700, 300);
        g2.setColor(Color.black);
        g2.setFont(new Font("Ariel", Font.ITALIC, 32));
        g2.drawString("" + game.score, 1900, 300);

        // Draw the ball speed indicator
        g2.setColor(Color.red);
        g2.drawString("Ball Speed Is     " + game.ball.xSpeed, 1700, 370);

        // Draw the ball outline
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(6f));
        Ball ball = game.ball;
        g2.drawOval(ball.x, ball.y, ball.diameter, ball.diameter);
        // Draw the ball color
        g2.setColor(Color.yellow);
        g2.fillOval(ball.x, ball.y, ball.diameter, ball.diameter);

        // Draw the paddle outline
        g2.setColor(Color.black);
        g2.draw(game.paddle);
        // Draw the paddle color
        g2.setColor(Color.gray);
        g2.fill(game.paddle);

        // Draw the "YOU LOSE!" message, but only if the game is over
        if (game.gameOver)
        {
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Ariel", Font.BOLD, 300));
            g2.drawString("YOU LOSE!", 50, 500);
        }
    }
}
