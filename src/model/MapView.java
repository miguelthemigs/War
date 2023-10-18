package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapView {
    public static CustomPanel getCustomPanel() {
        return new CustomPanel();
    }

    public static class CustomPanel extends JPanel {
        private BufferedImage image;
        private final Rectangle clickableRect;
        public static ArrayList<JCheckBox> checkBoxes; // Lista de checkboxes
        private final JButton enviarButton; // Botão de enviar

        public static ArrayList<Jogador.Cor> getCoresSelecionadas() {
            ArrayList<Jogador.Cor> coresSelecionadas = new ArrayList<>();
            for (JCheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()) {
                    coresSelecionadas.add(Jogador.Cor.valueOf(checkBox.getText()));
                }
            }
            return coresSelecionadas;
        }

        public CustomPanel() {
            setLayout(null); // Desativa o gerenciador de layout

            try {
                image = ImageIO.read(new File("src/model/imagens/images/complete(1).png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            clickableRect = new Rectangle(335, 100, 220, 70); // Define um retângulo clicável

            checkBoxes = new ArrayList<>();
            Jogador.Cor[] options = Jogador.Cor.values();
            for (Jogador.Cor cor : options) {
                JCheckBox checkBox = new JCheckBox(cor.name());
                checkBox.setBounds(335, 100 + checkBoxes.size() * 30, 150, 30);
                checkBox.setVisible(false); // Começa invisível
                checkBoxes.add(checkBox);
                add(checkBox);
            }

            enviarButton = new JButton("Enviar");
            enviarButton.setBounds(335, 100 + options.length * 30, 100, 30);
            enviarButton.setVisible(false); // Começa invisível
            add(enviarButton);

            enviarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Jogador.Cor> coresSelecionadas = getCoresSelecionadas();
                    System.out.println("Cores selecionadas: " + coresSelecionadas);
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();

                    if (clickableRect.contains(x, y)) {
                        System.out.println("O retângulo foi clicado!");
                        for (JCheckBox checkBox : checkBoxes) {
                            checkBox.setVisible(true);
                        }
                        enviarButton.setVisible(true);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            if (image != null) {
                g.drawImage(image, 0, 0, null);
            }
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
