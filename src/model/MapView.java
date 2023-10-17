package model;

import javax.swing.*;
import java.awt.*;
public class MapView {

    public static void createFrameMap() {
        ImageIcon imageIcon = new ImageIcon("src/model/imagens/images/war_tabuleiro_mapa copy.png");

        JFrame frame = new JFrame("War PUC-Rio");
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.darkGray); // Set background color

        JLabel label = new JLabel(imageIcon);

        frame.add(label);

        frame.setSize(1100, 810);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    } // model/imagens/images/DALL·E 2023-10-17 14.28.00 - complete the image expanding the map. the same art type. make it smooth.png


    public static void createFrameInit() {
        ImageIcon imageIcon = new ImageIcon("src/model/imagens/images/complete(1).png");

        JFrame frame = new JFrame("War PUC-Rio");
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.black); // Set background color

        JLabel label = new JLabel(imageIcon);

        frame.add(label);

        frame.setSize(1100, 810);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}