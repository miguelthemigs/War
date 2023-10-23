package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class GameMap extends JPanel {
    private BufferedImage imagem;

    public GameMap() {
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResource("/model/imagens/images/war_tabuleiro_mapa copy.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setPreferredSize(new Dimension(imagem.getWidth(), imagem.getHeight()));
        this.setBackground(new Color(50, 50, 50));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagem, 0, 0, null);
    }
    public static void iniciarPainelDesenho() {
        JFrame frame = new JFrame("War PUC-Rio");
        GameMap painel = new GameMap();

        frame.add(painel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
