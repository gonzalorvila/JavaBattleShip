package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class mainMenuPanel extends JPanel {
    public static int frameSize = 1500;
    private JFrame mainMenuFrame;
    private JLabel backgroundImage;
    private JButton startGameButton;
    private JButton chooseDifficultyButton;

    public void makeMainMenu(ActionListener chooseDifficultyListener, ActionListener startGameListener) {
        mainMenuFrame = new JFrame("Main Menu");
        mainMenuFrame.setPreferredSize(new Dimension(frameSize, frameSize*2/7));
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   

        // top level panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel background = new JLabel( new ImageIcon("battleShipMainMenu.jpg"));
        background.setLayout(new FlowLayout());
        JButton startGameButton = new JButton("Start Game");
        JButton chooseRegularButton = new JButton("Regular Mode");
        JButton chooseHardButton = new JButton("Hard Mode: Bigger Board!");
        background.add(startGameButton);
        background.add(chooseRegularButton);
        background.add(chooseHardButton);
        panel.add(background);
        mainMenuFrame.add(panel);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);
    }
}