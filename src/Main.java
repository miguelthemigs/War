import model.ApiAcess;


import view.GameMap;
import view.InitGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        InitGame view = new InitGame();
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


        // aqui coloca-se o mapa e o jogo continuara na imagem. ai faremos draw tudo
        Scanner scanner = new Scanner(System.in);

        // Pergunte o número de jogadores

        ApiAcess api = new ApiAcess();
/*
        System.out.println("Cores disponíveis: vermelho, verde, azul, amarelo, preto, branco");
        do {
            System.out.print("Digite a cor que você quer ser: ");
            String corEscolhida = scanner.nextLine();

            coresEscolhidas.add(corEscolhida);

            System.out.print("Quer adicionar mais cores? (S/N): ");
            resposta = scanner.nextLine();
        } while (resposta.equalsIgnoreCase("S"));
*/
        //ArrayList<String> coresView = InitGame.;

        ArrayList<String> coresEscolhidas = customPanel.getCoresSelecionadas();
        api.gerarJogadores(coresEscolhidas);

        // Aqui realizamos e colocamos cada jogador para ter seu objetivo
        api.sorteiaObjetivo();

        // Agora, iremos gerar a lista com todos os territorios, e sortear os territorios e colocar 1 exercito em cada do seu respectivo jogador
        api.sorteiaTerritorios();
        GameMap.iniciarPainelDesenho(); // cria um novo frame com o mapa


        System.out.println("------- Checando se os jogadores possuem algum continente --------");
        // Loop para percorrer todos os jogadores e identificar se eles possuem algum continente
        api.checaContinentesJogador();

        // Vamos chegar as tropas a receber, de cada jogador
        api.checarTropasGanhar();
        api.imprimeTropasARecber();


        // Vamos realizar a distribuição de exércitos de cada jogador em cada território
        api.posicionamentoExercitos();




    }

}









