package view;


import model.ApiToView;

import javax.swing.*;
import java.awt.*;
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
    private BufferedImage imagemFundo;
    private List observers;
    public static GameMap painel = new GameMap();
private static final Color[] cores = {Color.RED,Color.BLUE,Color.BLACK,Color.WHITE,Color.GREEN,Color.YELLOW};
    private static ArrayList<Integer> tropas = new ArrayList<>();
    private static final Integer[] coordX = {560, 540, 450, 550, 515, 605, 85, 170, 110, 310, 190, 140, 278, 178, 195, 655, 840, 890, 785, 850, 840, 675, 790, 705, 670, 942, 615, 615, 810, 735, 775, 880, 645, 880, 715, 260, 270, 220, 182, 438, 471, 530, 560, 450, 570, 510, 590, 860, 885, 925, 800};
    private static final Integer[] coordY = {570, 500, 345, 380, 430, 495, 125, 140, 260, 100, 260, 350, 170, 218, 180, 418, 390, 205, 280, 297, 322, 130, 370, 328, 330, 258, 345, 185, 245, 305, 150, 130, 278, 355, 245, 560, 440, 450, 435, 272, 235, 224, 200, 150, 253, 135, 225, 590, 490, 615, 580};

    /*
     * África do Sul: (560, 570)
     * Angola: (540, 500)
     * Argélia: (450, 345)
     * Egito: (550, 380)
     * Nigéria: (515, 430)
     * Somália: (605, 495)
     * Alasca: (85, 125)
     * Calgary: (170, 140)
     * California: (110, 260)
     * Groenlândia: (310, 100)
     * Nova York: (190, 260)
     * México: (140, 350)
     * Québec: (278, 170)
     * Texas: (178, 218)
     * Vancouver: (195, 180)
     * Arábia Saudita: (655, 418)
     * Bangladesh: (840, 390)
     * Cazaquistão: (890, 205)
     * China: (785, 280)
     * Coreia do Norte: (850, 297)
     * Coreia do Sul: (840, 322)
     * Estônia: (675, 130)
     * Índia: (790, 370)
     * Irã: (705, 328)
     * Iraque: (670, 330)
     * Japão: (942, 258)
     * Jordânia: (615, 345)
     * Letônia: (615, 185)
     * Mongólia: (810, 245)
     * Paquistão: (735, 305)
     * Rússia: (775, 150)
     * Sibéria: (880, 130)
     * Síria: (645, 278)
     * Tailândia: (880, 355)
     * Turquia: (715, 245)
     * Argentina: (260, 560)
     * Brasil: (270, 440)
     * Peru: (220, 450)
     * Venezuela: (182, 435)
     * Espanha: (438, 272)
     * França: (471, 235)
     * Itália: (530, 224)
     * Polônia: (560, 200)
     * Reino Unido: (450, 150)
     * Romênia: (570, 253)
     * Suécia: (535, 135)
     * Ucrânia: (590, 225)
     * Austrália: (860, 590)
     * Indonésia: (885, 490)
     * Nova Zelândia: (925, 615)
     * Perth: (800, 580)
     */
/*
* ÁfricadoSul: (560, 570)
Angola: (540, 500)
Argélia: (450, 330)
Egito: (550, 380)
Nigéria: (515, 430)
Somália: (620, 500)
Alasca: (70, 110)
Calgary: (170, 140)
California: (110, 260)
Groenlândia: (310, 100)
NovaYork: (185, 260)
México: (140, 350)
Québec: (270, 160)
Texas: (160, 205)
Vancouver: (250, 175)
ArábiaSaudita: (655, 428)
Bangladesh: (830, 380)
Cazaquistão: (910, 200)
China: (785, 280)
CoréiadoNorte: (840, 297)
CoréiadoSul: (830, 322)
Estônia: (675, 130)
Índia: (790, 370)
Irã: (705, 328)
Iraque: (670, 330)
Japão: (942, 258)
Jordânia: (615, 345)
Letônia: (615, 185)
Mongólia: (810, 245)
Paquistão: (735, 305)
Rússia: (775, 150)
Sibéria: (880, 130)
Síria: (645, 278)
Tailândia: (890, 355)
Turquia: (715, 245)
Argentina: (260, 560)
Brasil: (265, 440)
Peru: (220, 440)
Venezuela: (182, 435)
Espanha: (438, 272)
França: (471, 235)
Itália: (530, 224)
Polônia: (560, 200)
ReinoUnido: (450, 150)
Romênia: (570, 253)
Suécia: (535, 135)
Ucrânia: (590, 225)
Austrália: (870, 590)
Indonésia: (885, 490)
NovaZelândia: (915, 615)
Perth: (800, 580)*/

    public GameMap() {
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

            if (cor == 3 || cor == 5)
                g.setColor(Color.BLACK);

            else
                g.setColor(Color.WHITE);

            g.drawString(Integer.toString(qTropas), x+6, y+17);
            g.setFont(originalFont); // Restaura a fonte original


        }

       // g.setColor(Color.BLACK); // Supondo que o jogador tem um método 'getCor()' que retorna a cor associada a ele

    }
    public static void atualizarElipses() {
        // Atualize os dados (números de tropas e cores) conforme necessário
        // Por exemplo, você pode chamar novamente api.iterarPaises() aqui
        // e depois ajustar os valores de tropas e cores.
        painel.repaint(); // Isso vai forçar o componente a ser redesenhado
    }
    public static void iniciarPainelDesenho() {
       JFrame frame = new JFrame("War PUC-Rio");

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
