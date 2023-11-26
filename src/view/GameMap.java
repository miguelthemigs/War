package view;


import model.ApiAcess;
import model.ApiToView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;


public class GameMap extends JPanel implements Observer {
    private BufferedImage imagem;
    private BufferedImage imagemFundo;
    public static GameMap painel = new GameMap();
    private static final Color[] cores = {Color.RED,Color.BLUE,Color.BLACK,Color.WHITE,Color.GREEN,Color.YELLOW};
    private static ArrayList<Integer> tropas = new ArrayList<>();
    private static final Integer[] coordX = {560, 540, 450, 550, 515, 605, 85, 170, 110, 310, 190, 140, 278, 178, 195, 655, 840, 890, 785, 850, 840, 675, 790, 705, 670, 942, 615, 615, 810, 735, 775, 880, 645, 880, 715, 260, 270, 220, 182, 438, 471, 530, 560, 450, 570, 510, 590, 860, 885, 925, 800};
    private static final Integer[] coordY = {570, 500, 345, 380, 430, 495, 125, 140, 260, 100, 260, 350, 170, 218, 180, 418, 390, 205, 280, 297, 322, 130, 370, 328, 330, 258, 345, 185, 245, 305, 150, 130, 278, 355, 245, 560, 440, 450, 435, 272, 235, 224, 200, 150, 253, 135, 225, 590, 490, 615, 580};

    public GameMap(){
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/war_tabuleiro_mapa_NOMES.png")));
            imagemFundo = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/war_tabuleiro_fundo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(null);
        this.setPreferredSize(new Dimension(imagem.getWidth(), imagem.getHeight())); // vai ser o tamanho que escolhemos padrao

    }

    public static void reset() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagemFundo, 0, 0, this);
        g.drawImage(imagem, 0, 0, null);
        criaElipse(g);

    }
    // Método para criar as elipses que represntam as tropas e dono de um território no tabuleiro
    public static void criaElipse(Graphics g){
        int x, y;
        ApiToView api = new ApiToView(); // teremos de ter essa instancia aqui
        tropas = api.iterarPaises();
        for(int i = 0; i < tropas.size(); i++){
            int qTropas = tropas.get(i)%100;
            int cor = tropas.get(i)/100;
            g.setColor(cores[cor]);
            x = coordX[i];
            y = coordY[i];

            g.fillOval(x, y, 25, 25); // Ajuste o tamanho conforme necessário
            Font originalFont = g.getFont(); // Salva a fonte original
            Font novaFonte = originalFont.deriveFont(originalFont.getSize() + 4.0f); // Ajuste o valor +4.0f para alterar o tamanho
            g.setFont(novaFonte);

            if (cor == 3 || cor == 4 ||cor == 5)
                g.setColor(Color.BLACK);

            else
                g.setColor(Color.WHITE);

            g.drawString(Integer.toString(qTropas), x+6, y+17);
            g.setFont(originalFont); // Restaura a fonte original


        }
    }
    public static void atualizarElipses() {
        painel.repaint(); // Isso vai forçar o componente a ser redesenhado
    }

    // Método para carregar os botões do painel
    public static void iniciarPainelDesenho() {
        JFrame frame = new JFrame("War PUC-Rio");

        frame.add(painel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Create a JButton for the "Objetivo"
        JButton objetivoButton = new JButton("Objetivo");
        objetivoButton.setBounds(10, 10, 95, 30); // Adjust the button position and size as needed
        painel.add(objetivoButton);

        objetivoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the objectives from the Goal class and format them as a single string
                ArrayList<String> objetivos = ApiAcess.textoObjetivos();

                // Concatenate the objectives with \n\n
                StringBuilder objectivesText = new StringBuilder();
                for (String objetivo : objetivos) {
                    objectivesText.append(objetivo).append("\n\n");
                }

                // Show a warning dialog with the objectives text
                JOptionPane.showMessageDialog(frame, objectivesText.toString(), "Objetivos", JOptionPane.PLAIN_MESSAGE);

            }
        });

        // Create a JButton for the "Opções" menu
        JButton opcoesButton = new JButton("Opções");
        opcoesButton.setBounds(110, 10, 95, 30); // Adjust the button position and size as needed
        painel.add(opcoesButton);

        // Add an ActionListener to the button to call the "opcoesSairRecomeço" function
        opcoesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibirOpcoesMenu();
            }
        });

        painel.repaint();
    }

    // Método para exibir o menu de opcões
    private static void exibirOpcoesMenu() {
        ApiAcess api = ApiAcess.getInstancia();
        Object[] opcoes = {"Salvar e Sair", "Recomeçar"};
        int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Opções",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        // Tratar a escolha do usuário
        switch (escolha) {
            case 0: // Salvar e Sair
                api.salvamento();
                System.exit(0);
                break;
            case 1: // Recomeçar
                JOptionPane.showMessageDialog(null, "Aviso: Recomeçar ainda não implementado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;
            default:
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
