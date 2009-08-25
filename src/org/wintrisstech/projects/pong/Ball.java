package org.wintrisstech.projects.pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Ball implements KeyListener
{
    private static final int defaultSpeed = 10;
    private static final int defaultDiameter = 50;
    private int x;
    private int y;
    private int courtWidth;
    private int courtHeight;
    private int xSpeed = defaultSpeed;
    private int ySpeed = defaultSpeed;
    private int diameter = defaultDiameter;
    boolean outOfBounds = false;
    boolean hitPaddle = false;
    private Paddle paddle;
    private boolean frozen = false;

    void setCourtDimension(int w, int h)
    {
        courtWidth = w;
        courtHeight = h;
        reset();
    }

    void setPaddle(Paddle p)
    {
        paddle = p;
    }

    void reset()
    {
        x = (courtWidth - diameter) / 2;
        y = (courtHeight - diameter) / 2;
        outOfBounds = false;
        xSpeed = defaultSpeed;
        ySpeed = defaultSpeed;
        frozen = false;
    }

    void tickUpdate()
    {
        if (frozen)
        {
            return;
        }
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

    void paintSelf(Graphics2D g2)
    {
        g2.setColor(Color.YELLOW);
        g2.fillOval(x, y, diameter, diameter);

    }

    public void keyTyped(KeyEvent e)
    {
        //Do nothing
    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            frozen = !frozen;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        //Do nothing
    }
}
