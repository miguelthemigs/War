import model.ApiAcess;

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

        api.gerarJogadores(coresEscolhidas);

        // Aqui realizamos e colocamos cada jogador para ter seu objetivo
        api.sorteiaObjetivo();

        // Agora, iremos gerar a lista com todos os territorios, e sortear os territorios e colocar 1 exercito em cada do seu respectivo jogador
        api.sorteiaTerritorios();

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









