package controller;

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
        System.out.println("\n********** JOGO INICIADO **********\n");
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
        System.out.println("\n********** JOGADORES GERADOS **********\n");

        // Aqui realizamos e colocamos cada jogador para ter seu objetivo
        api.sorteiaObjetivo();
        System.out.println("\n********** OBJETIVOS SORTEADOS **********\n");

        // Agora, iremos gerar a lista com todos os territorios, e sortear os territorios e colocar 1 exercito em cada do seu respectivo jogador
        api.sorteiaTerritorios();
        System.out.println("\n********** TERRITORIOS ATUALIZADOS **********\n");
        GameMap.iniciarPainelDesenho(); // cria um novo frame com o mapa
        System.out.println("\n********** MAPA INICIALIZADO **********\n");

        System.out.println("\n|||||||||||| FIM DO PRÉ JOGO ||||||||||||");

        // Roda infinitamente. Quem para o jogo é a funcao "checaSeGanhou", chamda depois de cada ataque
        while (!api.checaSeGanhou()) {
            System.out.println("\n|||||||||||| INICIO DO LOOP ||||||||||||\n\n");

            // Loop para percorrer todos os jogadores e identificar se eles possuem algum continente
            api.checaContinentesJogador();
            System.out.println("\n********** CONTINENTES CHECADOS **********\n");

            // Vamos chegar as tropas a receber, de cada jogador
            api.checarTropasGanhar();
            System.out.println("\n********** TROPAS GANHAR CHECADOS **********\n");
            //api.imprimeTropasARecber();

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
            System.out.println("\n********** ELIPSES ATUALIZADAS **********\n");

            System.out.println("--------------------------------------");
            System.out.println("---------- INICIANDO ATAQUE ----------\n\n");
            // Configurar e exibir a interface de ataque
            ApiAttack.resetInstancia();
            AtaqueGUI ataque = AtaqueGUI.getInstancia();
            ataque.mostraAtaque(); // efetua ataque dos jogadores
            System.out.println("\n\n---------- FINALIZANDO ATAQUE --------\n");
            System.out.println("\n----------------------------------------\n\n");

            System.out.println("\n\n----------------------------------------------\n");
            System.out.println("\n---------- INICIANDO PROCESSOS CARTAS ----------\n");
            api.distribuiCartas(); // ao fim do ataque, distribuimos uma carta para os jogadores que conquistaram um territorio
            api.trocaCartasPoligono();
            System.out.println("\n------------------------------------------------");
            System.out.println("\n---------- FINALIZANDO PROCESSOS CARTAS ----------\n");

            /*
            Cor:
            TropasAdd:
            Territórios:[ ]
            Cartas:[ ]
            Prêmio:

            for (jogador jogador : jogadores){
                System.out.println(jogador.getCor());
                System.out.println(jogador.getTropasParaAdicionar());
                System.out.println(jogador.getTerritoriosPossuidos());
                System.out.println(jogador.getPoligonosPossuidos());
                System.out.println(jogador.getPremio());
            }
            
            */

            System.out.println("\n|||||||||||| FIM DO LOOP ||||||||||||\n\n");

        }





    }

}









