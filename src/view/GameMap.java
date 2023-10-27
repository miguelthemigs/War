package view;


import model.ApiAcess;
import model.ApiToView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
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

public class GameMap extends JPanel implements Observer {
    private BufferedImage imagem;
    private List observers;
private static final Color[] cores = {Color.RED,Color.BLUE,Color.BLACK,Color.WHITE,Color.GREEN,Color.YELLOW};
    private static ArrayList<Integer> tropas = new ArrayList<>();


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
        criaElipse(g);


    }
    public static void criaElipse(Graphics g){
        int a = 0;
        ApiToView api = new ApiToView();
        tropas = api.iterarPaises();
        for(Integer i:tropas){
            int qTropas = i%100;
            int cor = i/100;
            g.setColor(cores[cor]);
            System.out.println(cores[cor]);
            g.fillOval(a+10, a+20, 25, 25); // Ajuste o tamanho conforme necessário
            Font originalFont = g.getFont(); // Salva a fonte original
            Font novaFonte = originalFont.deriveFont(originalFont.getSize() + 4.0f); // Ajuste o valor +4.0f para alterar o tamanho
            g.setFont(novaFonte);

            if (cor == 3 || cor == 5)
                g.setColor(Color.BLACK);

            else
                g.setColor(Color.WHITE);

            g.drawString(Integer.toString(qTropas), a+6, a+17);
            a += 20;
            g.setFont(originalFont); // Restaura a fonte original

        }
       // g.setColor(Color.BLACK); // Supondo que o jogador tem um método 'getCor()' que retorna a cor associada a ele

    }
    public static void iniciarPainelDesenho() {
       JFrame frame = new JFrame("War PUC-Rio");
        GameMap painel = new GameMap();


        frame.add(painel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        painel.repaint();

        // Desenha a elipse antes de exibir o JFrame
        //Graphics g = painel.getGraphics();







    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
