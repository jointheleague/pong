package org.wintrisstech.projects.pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class Paddle implements KeyListener, MouseMotionListener
{

    private static final int defaultX = 10;
    private static final int defaultY = 300;
    private static final int defaultWidth = 10;
    private static final int defaultHeight = 100;
    int x = defaultX;
    int y = defaultY;
    int width = defaultWidth;
    int height = defaultHeight;
    private boolean isMovingUp;
    private boolean isMovingDown;

    void tickUpdate()
    {
        if (isMovingDown && !isMovingUp)
        {
            y += 10;
        } else if (isMovingUp && !isMovingDown)
        {
            y -= 10;
        }
    }

    void paintSelf(Graphics2D g2)
    {
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, width, height);
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            isMovingUp = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            isMovingDown = true;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            isMovingUp = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            isMovingDown = false;
        }
    }

    public void mouseDragged(MouseEvent e)
    {
        //Do nothing
    }

    public void mouseMoved(MouseEvent e)
    {
        y = e.getY() - height / 2;
    }

    void reset()
    {
        y = defaultY;
    }
}
