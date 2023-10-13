import model.ApiAcess;
import model.Jogador;
import model.Tabuleiro;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("""
                                       BEM VINDO AO
                 .----------------. .----------------. .----------------.\s
                | .--------------. | .--------------. | .--------------. |
                | | _____  _____ | | |      __      | | |  _______     | |
                | ||_   _||_   _|| | |     /  \\     | | | |_   __ \\    | |
                | |  | | /\\ | |  | | |    / /\\ \\    | | |   | |__) |   | |
                | |  | |/  \\| |  | | |   / ____ \\   | | |   |  __ /    | |
                | |  |   /\\   |  | | | _/ /    \\ \\_ | | |  _| |  \\ \\_  | |
                | |  |__/  \\__|  | | ||____|  |____|| | | |____| |___| | |
                | |              | | |              | | |              | |
                | '--------------' | '--------------' | '--------------' |
                 '----------------' '----------------' '----------------'\s""");


        Scanner scanner = new Scanner(System.in);

        // Pergunte o número de jogadores

        ArrayList<String> coresEscolhidas = new ArrayList<>();
        String resposta;
        ApiAcess api = new ApiAcess();

        System.out.println("Cores disponíveis: vermelho, verde, azul, amarelo, preto, branco");
        do {
            System.out.print("Digite a cor que você quer ser: ");
            String corEscolhida = scanner.nextLine();

            coresEscolhidas.add(corEscolhida);

            System.out.print("Quer adicionar mais cores? (S/N): ");
            resposta = scanner.nextLine();
        } while (resposta.equalsIgnoreCase("S"));

        ArrayList<Jogador> jogadores = new ArrayList<>(); // guardares os jogadores aqui

        for (String cor : coresEscolhidas) {
            jogadores.add(new Jogador(Jogador.Cor.valueOf(cor)));
        }

        // Agora você tem uma lista de jogadores com as cores escolhidas
        for (int i = 0; i < jogadores.size(); i++) {
            System.out.println("Jogador " + (i+1) + " escolheu a cor: " + jogadores.get(i).getCor());
        }
        System.out.println("Cores escolhidas: " + coresEscolhidas);
        System.out.println("--------------------------------------------------------");

        // Aqui realizamos e colocamos cada jogador para ter seu objetivo
        api.sorteiaObjetivo(jogadores);
        System.out.println();

        for (int i = 0; i < jogadores.size(); i++){
            System.out.println("\nObjetivo jogador "+ (i+1) + ": "+ jogadores.get(i).getObjetivo());
        }

        // Agora, iremos gerar a lista com todos os territorios, e sortear os territorios e colocar 1 exercito em cada do seu respectivo jogador
        api.sorteiaTerritorios(jogadores);
        for (int i = 0; i < jogadores.size(); i++){
            System.out.println("\nTerritorios jogador "+ (i+1));
            api.imprimeListaTerritorios(jogadores.get(i).getTerritoriosPossuidos());
            System.out.println("Numero de territorios: "+jogadores.get(i).getNumTerritorios());


        }

        System.out.println("Checando se os jogadores possuem algum continente -");
        // Loop para percorrer todos os jogadores e identificar se eles possuem algum continente
        for (int i = 0; i < jogadores.size(); i++) {
            System.out.printf("Jogador (%d):", i + 1);
            // Chama o método checaContinentes para o jogador atual
            jogadores.get(i).checaContinentes();
            System.out.println("Fim\n");
        }





    }
}