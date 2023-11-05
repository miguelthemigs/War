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
    private static final Integer[] coordX = {560, 540, 450, 550, 515, 600, 90, 170, 110, 310, 210, 140, 290, 180, 150, 670, 840, 830, 785, 855, 855, 675, 790, 705, 670, 942, 615, 645, 840, 735, 775, 880, 645, 890, 715, 260, 265, 220, 182, 438, 471, 528, 565, 450, 570, 510, 590, 855, 880, 915, 800};
    private static final Integer[] coordY = {570, 500, 370, 380, 430, 460, 140, 140, 250, 100, 260, 350, 180, 225, 180, 400, 360, 210, 280, 297, 322, 130, 370, 328, 330, 258, 345, 185, 245, 305, 150, 130, 278, 355, 240, 560, 440, 470, 435, 272, 235, 222, 200, 160, 253, 145, 225, 600, 490, 645, 580};

/*
* ÁfricadoSul: (560, 570)
Angola: (540, 500)
Argélia: (450, 370)
Egito: (550, 380)
Nigéria: (515, 430)
Somália: (600, 460)
Alasca: (90, 140)
Calgary: (170, 140)
California: (110, 250)
Groenlândia: (310, 100)
NovaYork: (210, 260)
México: (140, 350)
Québec: (290, 180)
Texas: (180, 225)
Vancouver: (150, 180)
ArábiaSaudita: (670, 400)
Bangladesh: (840, 360)
Cazaquistão: (830, 210)
China: (785, 280)
CoréiadoNorte: (855, 297)
CoréiadoSul: (855, 322)
Estônia: (675, 130)
Índia: (790, 370)
Irã: (705, 328)
Iraque: (670, 330)
Japão: (942, 258)
Jordânia: (615, 345)
Letônia: (645, 185)
Mongólia: (840, 245)
Paquistão: (735, 305)
Rússia: (775, 150)
Sibéria: (880, 130)
Síria: (645, 278)
Tailândia: (890, 355)
Turquia: (715, 240)
Argentina: (260, 560)
Brasil: (265, 440)
Peru: (220, 470)
Venezuela: (182, 435)
Espanha: (438, 272)
França: (471, 235)
Itália: (528, 222)
Polônia: (565, 200)
ReinoUnido: (450, 160)
Romênia: (570, 253)
Suécia: (510, 145)
Ucrânia: (590, 225)
Austrália: (855, 600)
Indonésia: (880, 490)
NovaZelândia: (915, 645)
Perth: (800, 580)*/

    public GameMap() {
        try {

            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/war_tabuleiro_mapa copy.png")));
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
