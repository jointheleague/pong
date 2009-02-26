package org.wintrisstech.projects.pong;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PongGame
{
    public Painter painter;
    private JFrame playingField;
    private Mouser mouser;
    public Rectangle2D.Double paddle = new Rectangle2D.Double(10, 300, 10, 100);
    public Ellipse2D.Double ball = new Ellipse2D.Double(500, 500, 50, 50);
    public int ballXspeedInt = Integer.parseInt(JOptionPane.showInputDialog("Ball Speed?"));
    public int ballYspeedInt = ballXspeedInt;
    public int score = 0;
    public AudioClip boing = Applet.newAudioClip(Painter.class.getResource("fire.wav"));

    public void startTheGame()
    {
        painter = new Painter(); // Make class Painter into a "real" painter object.
        painter.game = this;

        playingField = new JFrame("PngReference1"); //Make class JFrame a "real" JFrame.
        playingField.setVisible(true);
        playingField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playingField.add(painter); // Let the JFrame know is has an artist who will paint on the JFrame.

        playingField.setSize(2000, 1000); // Set size after adding painter so that paint() gets called initially.

        mouser = new Mouser();
        mouser.game = this;

        playingField.addMouseMotionListener(mouser); //A little abtruse for the students!
    }
}
