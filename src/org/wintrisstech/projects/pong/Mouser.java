package org.wintrisstech.projects.pong;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Mouser implements MouseMotionListener
{
    public PongGame game;

    public void mouseDragged(MouseEvent arg0)
    {
        //Do nothing.
    }

    public void mouseMoved(MouseEvent mousePosition)
    {
        game.paddle.y = mousePosition.getY();
    }
}
