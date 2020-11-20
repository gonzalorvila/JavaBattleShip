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
import java.io.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainMenuPanel extends JPanel {
    public static int frameSize = 1500;
    private JFrame mainMenuFrame;
    private JLabel backgroundImage;
    private JButton startGameButton;
    private JButton chooseDifficultyButton;

    public void makeMainMenu(ActionListener onButtonListener) throws IOException {
        mainMenuFrame = new JFrame("Main Menu");
        mainMenuFrame.setPreferredSize(new Dimension(frameSize, frameSize*2/7));
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   

        // top level panel
        Path currentRelativePath = Paths.get("");
        String pathToImage = currentRelativePath.toAbsolutePath().toString() + "/view/battleShipMainMenu.jpg";
        BufferedImage backgroundImage = ImageIO.read(new File(pathToImage));
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        };
        panel.setLayout(new FlowLayout());
        // JLabel background = new JLabel( new ImageIcon("battleShipMainMenu.jpg"));
        // background.setLayout(new FlowLayout());
        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(onButtonListener);
        JButton chooseRegularButton = new JButton("Regular Mode");
        chooseRegularButton.addActionListener(onButtonListener);
        JButton chooseHardButton = new JButton("Hard Mode: Bigger Board!");
        chooseHardButton.addActionListener(onButtonListener);
        // panel.add(background);
        panel.add(startGameButton);
        panel.add(chooseRegularButton);
        panel.add(chooseHardButton);
        mainMenuFrame.add(panel);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);
    }
}
