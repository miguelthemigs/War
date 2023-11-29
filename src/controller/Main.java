package controller;


import model.ApiAcess;


import model.ApiAttack;
import model.ApiRemanejar;
import view.AtaqueGUI;
import view.GameMap;
import view.InitGame;
import view.TextBoxes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        System.out.println("\n********** JOGO INICIADO **********\n");
        TextBoxes box = new TextBoxes();

        ApiAcess api = ApiAcess.getInstancia();
        api.reset();

        InitGame view = new InitGame();
        InitGame.reset();

        JFrame frame;
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

        if (!InitGame.recarregou) {

            System.out.println("\n********** JOGO COMECOU **********\n");

            // Pergunte o número de jogadores

            ArrayList<String> coresEscolhidas = customPanel.getCoresSelecionadas();
            api.gerarJogadores(coresEscolhidas);
            System.out.println("\n********** JOGADORES GERADOS **********\n");

            // Aqui realizamos e colocamos cada jogador para ter seu objetivo
            api.sorteiaObjetivo();
            System.out.println("\n********** OBJETIVOS SORTEADOS **********\n");

            // Agora, iremos gerar a lista com todos os territorios, e sortear os territorios e colocar 1 exercito em cada do seu respectivo jogador
            api.sorteiaTerritorios();
            System.out.println("\n********** TERRITORIOS ATUALIZADOS **********\n");

            System.out.println("\n|||||||||||| FIM DO PRÉ JOGO ||||||||||||");
        }

        else {
            api.carregamento();
            System.out.println("\n********** JOGO RECARREGADO **********\n");
        }

        GameMap.reset();
        GameMap.iniciarPainelDesenho(); // cria um novo frame com o mapa
        System.out.println("\n********** MAPA INICIALIZADO **********\n");

        // Roda infinitamente. Quem para o jogo é a funcao "checaSeGanhou", chamda depois de cada ataque
        while (!api.checaSeGanhou()) {

            api.salvamento();
            System.out.println("\n********** RODADA SALVA **********\n");

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
                    System.out.println("\n********** NOVAS TROPAS ALOCADAS **********\n");
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
            ataque.mostraAtaque(); // Efetua ataque dos jogadores
            System.out.println("\n\n---------- FINALIZANDO ATAQUE --------\n");
            System.out.println("\n----------------------------------------\n\n");

            // Confirma que apos o ataque o jogo segue
            if (api.acabou){
                break;
            }


            ataque.mostraRemanejar();
            System.out.println("\n********** TROPAS REMANEJADAS **********\n");

            System.out.println("\n\n----------------------------------------------\n");
            System.out.println("\n---------- INICIANDO PROCESSOS CARTAS ----------\n");
            api.distribuiCartas(); // Ao fim do ataque, distribuimos uma carta para os jogadores que conquistaram um territorio
            api.trocaCartasPoligono();
            System.out.println("\n------------------------------------------------");
            System.out.println("\n---------- FINALIZANDO PROCESSOS CARTAS ----------\n");

            System.out.println("\n|||||||||||| FIM DO LOOP ||||||||||||\n\n");

        }

        // Pergunta ao jogador se quer parar de jogar ou inicar outra partida
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Deseja recomeçar a partida ou acabar o jogo?",
                "Fim do Jogo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Recomeçar", "Acabar"},
                "Recomeçar"
        );

        if (escolha == JOptionPane.YES_OPTION) {
            // Fecha todos os frames
            for (Window janela : Window.getWindows()) {
                janela.dispose();
            }
            // Recomeça o jogo indo para o inicio da main
            main(args);
        } else {
            // Finaliza o jogo
            System.exit(0);
        }

    }

}
