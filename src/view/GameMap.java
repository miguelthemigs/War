package view;


import controller.Goal;
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
import view.TextBoxes;
// precisamos usar o observer eu acho, para poder ver a mudanca de territorios possuidos de cada jogador, e assim ir pintando as bolinhas nos territorios
/*
no mapa ter
    private List<Observer> observers;
    private List<Territorio> territorios;
no territorios ter a funcao update(List<Territorio> territorios) e extender Observer
no Game Map ter public GameMap() {
        this.observers = new ArrayList<>();
        this.territorios = new ArrayList<>();
    }
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(territorios);
        }
        chamar sempre que houver uma mudança de estado que os observadores precisam saber, no caso seria quando:
        inicio do jogo, onde os territorios estarao distribuidos
        fim de um ataque, onde os territorios estarao diferentes

       O método update é a lógica para atualizar as elipses dos territórios que pertencem a esse jogador.
*/

public class GameMap extends JPanel implements Observer {
    private BufferedImage imagem;
    private BufferedImage imagemFundo;
    private List observers;
    private static TextBoxes textBoxes;

    public static GameMap painel = new GameMap();
private static final Color[] cores = {Color.RED,Color.BLUE,Color.BLACK,Color.WHITE,Color.GREEN,Color.YELLOW};
    private static ArrayList<Integer> tropas = new ArrayList<>();
    private static final Integer[] coordX = {560, 540, 450, 550, 515, 605, 85, 170, 110, 310, 190, 140, 278, 178, 195, 655, 840, 890, 785, 850, 840, 675, 790, 705, 670, 942, 615, 615, 810, 735, 775, 880, 645, 880, 715, 260, 270, 220, 182, 438, 471, 530, 560, 450, 570, 510, 590, 860, 885, 925, 800};
    private static final Integer[] coordY = {570, 500, 345, 380, 430, 495, 125, 140, 260, 100, 260, 350, 170, 218, 180, 418, 390, 205, 280, 297, 322, 130, 370, 328, 330, 258, 345, 185, 245, 305, 150, 130, 278, 355, 245, 560, 440, 450, 435, 272, 235, 224, 200, 150, 253, 135, 225, 590, 490, 615, 580};

    public static void setTextBoxes(TextBoxes textBoxes) {
        GameMap.textBoxes = textBoxes;
    }
    public TextBoxes getTextBoxes() {
        return textBoxes;
    }
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagemFundo, 0, 0, this);
        g.drawImage(imagem, 0, 0, null);
        criaElipse(g);

    }
    public static void criaElipse(Graphics g){
        int x, y;
        ApiToView api = new ApiToView();
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

       // g.setColor(Color.BLACK); // Supondo que o jogador tem um método 'getCor()' que retorna a cor associada a ele

    }
    public static void atualizarElipses() {
        painel.repaint(); // Isso vai forçar o componente a ser redesenhado
    }

    public static void iniciarPainelDesenho() {
       JFrame frame = new JFrame("War PUC-Rio");

        frame.add(painel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //TENTANDO OBJETIVOS

        // Get the objectives from the Goal class and format them as a single string
        Goal goal = new Goal();
        ArrayList<String> objetivos = goal.retornaObjetivos();
        StringBuilder objectivesText = new StringBuilder("<html>"); // Use HTML to allow line breaks
        for (String objetivo : objetivos) {
            objectivesText.append(objetivo).append("<br><br><br>");
        }
        objectivesText.append("</html");

        // Create a JPanel to hold the text with a black background
        JPanel objectivesPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Color lightBlue = new Color(26, 100, 180);
                g.setColor(lightBlue);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        objectivesPanel.setLayout(null);
        objectivesPanel.setBounds(10, 10 + 10 + 30 , 1005, 640); // Adjust the text window position and size as needed

        // Create a JLabel to display the formatted objectives
        JLabel objectivesTextLabel = new JLabel(objectivesText.toString());
        objectivesTextLabel.setBounds(5, 0, 990, 640); // Adjust the text position (inside window) and size as needed

        Font objectivesFont = new Font("Stencil", Font.PLAIN, 20); // Increase the font size as needed
        objectivesTextLabel.setFont(objectivesFont); // Set the font for the text

        objectivesTextLabel.setForeground(Color.WHITE); // Set the text color to white

        // Add the label to the objectives panel
        objectivesPanel.add(objectivesTextLabel);
        objectivesPanel.setVisible(false); // Initially set it to not visible
        painel.add(objectivesPanel);

        // Create a JButton to toggle the visibility of objectives
        JButton toggleButton = new JButton("Objetivos");
        toggleButton.setBounds(10, 10, 95, 30); // Adjust the button position and size as needed
        painel.add(toggleButton);

        // Add an ActionListener to the button to toggle the visibility of objectives
        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                objectivesPanel.setVisible(!objectivesPanel.isVisible()); // Toggle visibility
            }
        });

        //TENTANDO OBJETIVOS
        painel.repaint();

        // Desenha a elipse antes de exibir o JFrame
        //Graphics g = painel.getGraphics();







    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
