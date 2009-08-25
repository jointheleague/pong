package org.wintrisstech.projects.pong;

import java.awt.Color;
import java.awt.Graphics2D;

class Ball
{

    private int x;
    private int y;
    private int courtWidth;
    private int courtHeight;
    static private int defaultSpeed = 10;
    private int xSpeed = defaultSpeed;
    private int ySpeed = defaultSpeed;
    private int diameter = 50;
    boolean outOfBounds = false;
    boolean hitPaddle = false;
    private Paddle paddle;

    void setCourtDimension(int w, int h)
    {
        courtWidth = w;
        courtHeight = h;
        reset();
    }

    void reset()
    {
        x = (courtWidth - diameter) / 2;
        y = (courtHeight - diameter) / 2;
        outOfBounds = false;
        xSpeed = defaultSpeed;
        ySpeed = defaultSpeed;
    }

    void setPaddle(Paddle p)
    {
        paddle = p;
    }

    void tickUpdate()
    {
        x = x + xSpeed; // The way to explain what is happening.
        y += ySpeed; // The way the "Big Boys" do it.

        if (x + diameter > courtWidth && xSpeed > 0) // These are good brain burners for the kids.  They can usually figure it out.  Explain why it can't be 1500 if the screen width is 1500 because of ball bounding box.
        {
            xSpeed = -xSpeed;
        }

        if (y + diameter > courtHeight && ySpeed > 0)
        {
            ySpeed = -ySpeed;
        }

        if (y < 0 && ySpeed < 0)
        {
            ySpeed = -ySpeed;
        }

        if (x + diameter / 2 < 0)
        {
            outOfBounds = true;
        }

        if (x < paddle.x + paddle.width && y + diameter > paddle.y &&
                y < paddle.y + paddle.height && xSpeed < 0)
        {
            hitPaddle = true;
            xSpeed = -xSpeed;
        }

    }

    void paintMe(Graphics2D g2)
    {
        g2.setColor(Color.YELLOW);
        g2.fillOval(x, y, diameter, diameter);

    }
}
