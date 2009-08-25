package org.wintrisstech.projects.pong;

import java.awt.Color;
import java.awt.Graphics2D;

class Paddle
{

    int x = 10;
    int y = 300;
    int width = 10;
    int height = 100;

    void centerY(int yIn)
    {
        y = yIn - height / 2;
    }

    void paintMe(Graphics2D g2)
    {
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, width, height);
    }
}
