import controller.Observer;
import model.ApiAcess;


import model.ApiAttack;
import view.AtaqueGUI;
import view.GameMap;
import view.InitGame;
import view.TextBoxes;

import javax.swing.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        InitGame view = new InitGame();
        JFrame frame;
        TextBoxes box = new TextBoxes();
        InitGame.CustomPanel customPanel = InitGame.getCustomPanel();
        frame = view.generateBeginning();


        try {
            synchronized (view.getLock()) {
                view.getLock().wait(); // Espere pela notificação do clique do botão
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Continuando a execução...");
        frame.setVisible(false);


        // Pergunte o número de jogadores

        ApiAcess api = ApiAcess.getInstancia();

        ArrayList<String> coresEscolhidas = customPanel.getCoresSelecionadas();
        api.gerarJogadores(coresEscolhidas);

        // Aqui realizamos e colocamos cada jogador para ter seu objetivo
        api.sorteiaObjetivo();

        // Agora, iremos gerar a lista com todos os territorios, e sortear os territorios e colocar 1 exercito em cada do seu respectivo jogador
        api.sorteiaTerritorios();
        GameMap.iniciarPainelDesenho(); // cria um novo frame com o mapa
        //api.trocaCartasPoligono();

        int limitador = 3;
        while (limitador > 0) {
            System.out.println("------- Checando se os jogadores possuem algum continente --------");
            // Loop para percorrer todos os jogadores e identificar se eles possuem algum continente
            api.checaContinentesJogador();

            // Vamos chegar as tropas a receber, de cada jogador
            api.checarTropasGanhar();
            api.imprimeTropasARecber();

            box.criarInterface(() -> {
                System.out.println("estou aqui");

            });
            try {
                synchronized (box.getLock()) {
                    box.getLock().wait(); // Espere pela notificação do onde finalizamos onde queremos colocar o territorio
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            GameMap.atualizarElipses();


            // Configurar e exibir a interface de ataque
            ApiAttack.resetInstancia();
            AtaqueGUI ataque = AtaqueGUI.getInstancia();
            ataque.mostraAtaque(); // efetua ataque dos jogadores


            api.distribuiCartas(); // ao fim do ataque, distribuimos uma carta para os jogadores que conquistaram um territorio
            api.trocaCartasPoligono();

            limitador--;
        }





    }

}









