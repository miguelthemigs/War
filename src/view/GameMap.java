package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
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

public class GameMap extends JPanel {
    private BufferedImage imagem;

    public GameMap() {
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResource("/model/imagens/images/war_tabuleiro_mapa copy.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(null);
        this.setPreferredSize(new Dimension(imagem.getWidth(), imagem.getHeight())); // vai ser o tamanho que escolhemos padrao
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
