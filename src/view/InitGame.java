package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InitGame {
    private static InitGame.CustomPanel customPanel;
    private static final Object lock = new Object(); // Adicionado aqui

    public static InitGame.CustomPanel getCustomPanel() { // singleton
        if (customPanel == null) {
            customPanel = new InitGame.CustomPanel();
        }
        return customPanel;
    }

    public static class CustomPanel extends JPanel {
        private BufferedImage image;
        private final Rectangle clickableRect;
        public static ArrayList<JCheckBox> checkBoxes; // Lista de checkboxes
        private final JButton enviarButton; // Botão de enviar

        public CustomPanel() {
            setLayout(null);

            try {
                image = ImageIO.read(new File("src/view/images/complete(1).png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            clickableRect = new Rectangle(335, 100, 220, 70);

            checkBoxes = new ArrayList<>();
            String[] options = {"vermelho", "azul", "verde", "preto", "branco", "amarelo" };
            for (String cor : options) {
                JCheckBox checkBox = new JCheckBox(cor);
                checkBox.setBounds(335, 200 + checkBoxes.size() * 50, 200, 70);
                checkBox.setVisible(false);
                checkBoxes.add(checkBox);
                add(checkBox);
            }

            enviarButton = new JButton("Iniciar");
            enviarButton.setBounds(335, 220 + options.length * 50, 200, 70);
            enviarButton.setVisible(false);
            add(enviarButton);

            enviarButton.addActionListener(e -> {
                ArrayList<String> coresSelecionadas = getCoresSelecionadas();
                System.out.println("Cores selecionadas: " + coresSelecionadas);


                synchronized (lock) {
                    lock.notify(); // Notifique a Main para continuar
                    enviarButton.setVisible(false);
                    for (JCheckBox checkBox : checkBoxes) {
                        checkBox.setVisible(false);
                    }
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

        public ArrayList<String> getCoresSelecionadas() {
            ArrayList<String> coresSelecionadas = new ArrayList<>();
            for (JCheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()) {
                    coresSelecionadas.add(checkBox.getText());
                }
            }
            return coresSelecionadas;
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

    }

    public JFrame generateBeginning() {
        JFrame frame = new JFrame("War PUC-Rio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 810);

        customPanel = getCustomPanel();
        frame.add(customPanel);

        frame.setVisible(true);
        return frame;

    }

    public Object getLock() {
        return lock;
    }
}
