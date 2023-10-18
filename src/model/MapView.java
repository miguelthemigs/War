package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapView { // Classe onde exibiremos o inicio do jogo
    public static CustomPanel getCustomPanel() {
        return new CustomPanel();
    }

    public static class CustomPanel extends JPanel {
        private BufferedImage image;
        private final Rectangle clickableRect;
        public static ArrayList<JCheckBox> checkBoxes; // Lista de checkboxes
        private final JButton enviarButton; // Botão de enviar

        public static ArrayList<String> getCoresSelecionadas() {
            ArrayList<String> coresSelecionadas = new ArrayList<>();
            for (JCheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()) {
                    coresSelecionadas.add(checkBox.getText());
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
            Jogador.Cor[] options = Jogador.Cor.values(); // as opcoes da checkbox sao as cores que o jogador pode escolher
            for (Jogador.Cor cor : options) {
                JCheckBox checkBox = new JCheckBox(cor.name());
                checkBox.setBounds(335, 200 + checkBoxes.size() * 50, 200, 70);
                checkBox.setVisible(false); // Começa invisível
                checkBoxes.add(checkBox);
                add(checkBox);
            }

            enviarButton = new JButton("Iniciar");
            enviarButton.setBounds(335, 220 + options.length * 50, 200, 70);
            enviarButton.setVisible(false); // Começa invisível
            add(enviarButton);

            enviarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<String> coresSelecionadas = getCoresSelecionadas();
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
