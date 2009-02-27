package org.wintrisstech.projects.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Painter extends JComponent
{
    public PongGame game;

    @Override
    protected void paintComponent(Graphics g)
    {
        // Draw the scoreboard
        g.setColor(Color.green);
        g.setFont(new Font("Ariel", Font.BOLD, 22));
        g.drawString("Your Score Is ", 1700, 300);
        g.setColor(Color.black);
        g.setFont(new Font("Ariel", Font.ITALIC, 32));
        g.drawString("" + game.score, 1900, 300);

        // Draw the ball speed indicator
        g.setColor(Color.red);
        g.drawString("Ball Speed Is     " + game.ball.xSpeed, 1700, 370);

        // Get ready to draw the ball
        Ball ball = game.ball;
        // Draw the ball color
        g.setColor(Color.yellow);
        g.fillOval(ball.x, ball.y, ball.diameter, ball.diameter);
        // Draw the ball outline
        g.setColor(Color.black);
        g.drawOval(ball.x, ball.y, ball.diameter, ball.diameter);

        // Get ready to draw the paddle
        Paddle p = game.paddle;
        // Draw the paddle color
        g.setColor(Color.gray);
        g.fillRect(p.x, p.y, p.width, p.height);
        // Draw the paddle outline
        g.setColor(Color.black);
        g.drawRect(p.x, p.y, p.width, p.height);

        // Draw the "YOU LOSE!" message, but only if the game is over
        if (game.gameOver)
        {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Ariel", Font.BOLD, 300));
            g.drawString("YOU LOSE!", 50, 500);
        }
    }
}
