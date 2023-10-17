package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapView {
    public static class CustomPanel extends JPanel {
        private BufferedImage image;
        private Rectangle clickableRect;

        public CustomPanel() {
            setLayout(null); // Desativa o gerenciador de layout

            try {
                image = ImageIO.read(new File("src/model/imagens/images/complete(1).png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            clickableRect = new Rectangle(100, 100, 100, 50); // Define um retângulo clicável
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();

                    if (clickableRect.contains(x, y)) {
                        System.out.println("O retângulo foi clicado!");
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(100, 10, getWidth(), getHeight());
            if (image != null) {
                g.drawImage(image, 0, 0, null);
            }

            // Desenha o retângulo clicável
            g.setColor(Color.BLUE);
            g.fillRect(clickableRect.x, clickableRect.y, clickableRect.width, clickableRect.height);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1100, 810);
        }
    }

    public static void generateBeginning() {
        JFrame frame = new JFrame("War PUC-Rio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 810);

        CustomPanel panel = new CustomPanel();
        frame.add(panel);

        frame.setVisible(true);
    }


}
