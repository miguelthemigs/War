package model;

import view.GameMap;
import view.TextBoxes;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ApiAcess {
    String[] objetivos = Cartas.objetivo; // devo tirar um onjetivo da lista a cada sorteio
    private static ApiAcess instancia = null;
    public static ArrayList<Jogador> jogadores = new ArrayList<>();
    ArrayList<Cartas.Territorio> cartasEmJogo = new ArrayList<>(List.of(Cartas.Territorio.allTerritorios));

    // Método para obter a instância única
    public static ApiAcess getInstancia() {
        if (instancia == null) {
            instancia = new ApiAcess();
        }
        return instancia;
    }
    public void sorteiaObjetivo() {
        Random rand = new Random();
        ArrayList<Integer> indicesAntigos = new ArrayList<>();

        while (indicesAntigos.size() < jogadores.size()) { // Enquanto não sorteou para todos os jogadores
            int indiceAleatorio = rand.nextInt(objetivos.length);
            if (!indicesAntigos.contains(indiceAleatorio)) {
                int indiceJogador = indicesAntigos.size(); // Usa o tamanho da lista como índice
                jogadores.get(indiceJogador).setObjetivo(objetivos[indiceAleatorio]);
                indicesAntigos.add(indiceAleatorio);
            }
        }
        for (Jogador jogador : jogadores) {
            System.out.println("\nObjetivo jogador " + jogador.getCor() + ": " + jogador.getObjetivo());
        }

    }
private boolean temCor(Jogador.Cor cor){
        for(Jogador jogador: jogadores){
            if(jogador.getCor().equals(cor)) // se essa cor existir na lista de jogadores, retorna true
                return true;
        }
        return false;
}
    private boolean atendeCriterioVitoriaCores(Jogador jogador, String objetivo, Jogador.Cor corAlvo){
        return jogador.getObjetivo().equals(objetivo) &&
                (jogador.getCor().equals(corAlvo) || !temCor(corAlvo)) &&
                jogador.getTerritoriosPossuidos().size() == 24;
    }
    public void checaSeGanhou() { // deve ser chamada ao fim de cada ataque
        for (Jogador jogador : jogadores) { // checa os objetivos de cores
            for (int i = 0; i <= 5; i++) { // itero pelos objetivos de cores
                String objetivo = objetivos[i];
                Jogador.Cor corAlvo = Jogador.Cor.values()[i];

                if (atendeCriterioVitoriaCores(jogador, objetivo, corAlvo)) { // se o jogador tiver algum objetivo de cores, ele entra aqui
                    jogador.ganhouJogo = true;
                    System.out.println("Jogador " + jogador.getCor() + " ganhou!");
                    break;
                }
                for (Jogador oponente : jogadores) { // checa se nao eiste o jogador que deveria destruir
                    if (oponente.getCor().equals(corAlvo) && oponente.getTerritoriosPossuidos().isEmpty()) {
                        jogador.ganhouJogo = true;
                        System.out.println("Jogador " + jogador.getCor() + " ganhou!");
                        break;
                    }

                }
            }

        if (jogador.getObjetivo().equals(objetivos[6])) {
            ArrayList<String> continentesDoJogador = jogador.checaContinentes();
            if (continentesDoJogador.contains("AmericaNorte") && continentesDoJogador.contains("Africa")) {
                jogador.ganhouJogo = true;
                System.out.println("Jogador " + jogador.getCor() + " ganhou!");
                break;
            }
        } else if ((jogador.getObjetivo().equals(objetivos[7]))) {
            ArrayList<String> continentesDoJogador = jogador.checaContinentes();
            if (continentesDoJogador.contains("Asia") && continentesDoJogador.contains("Africa")) {
                jogador.ganhouJogo = true;
                System.out.println("Jogador " + jogador.getCor() + " ganhou!");
                break;
            }
        } else if ((jogador.getObjetivo().equals(objetivos[8]))) {
            ArrayList<String> continentesDoJogador = jogador.checaContinentes();
            if (continentesDoJogador.contains("Oceania") && continentesDoJogador.contains("AmericaNorte")) {
                jogador.ganhouJogo = true;
                System.out.println("Jogador " + jogador.getCor() + " ganhou!");
                break;
            }
        } else if ((jogador.getObjetivo().equals(objetivos[9]))) {
            ArrayList<String> continentesDoJogador = jogador.checaContinentes();
            if (continentesDoJogador.contains("Asia") && continentesDoJogador.contains("AmericaSul")) {
                jogador.ganhouJogo = true;
                System.out.println("Jogador " + jogador.getCor() + " ganhou!");
                break;
            }
        } else if ((jogador.getObjetivo().equals(objetivos[10]))) {
            ArrayList<String> continentesDoJogador = jogador.checaContinentes();
            if (continentesDoJogador.contains("Europa") && continentesDoJogador.contains("AmericaSul") && continentesDoJogador.size() >= 3) {
                jogador.ganhouJogo = true;
                System.out.println("Jogador " + jogador.getCor() + " ganhou!");
                break;
            }
        } else if ((jogador.getObjetivo().equals(objetivos[11]))) {
            ArrayList<String> continentesDoJogador = jogador.checaContinentes();
            if (continentesDoJogador.contains("Europa") && continentesDoJogador.contains("Oceania") && continentesDoJogador.size() >= 3) {
                jogador.ganhouJogo = true;
                System.out.println("Jogador " + jogador.getCor() + " ganhou!");
                break;
            }
        } else if ((jogador.getObjetivo().equals(objetivos[12])) && jogador.getTerritoriosPossuidos().size() >= 24) {
            jogador.ganhouJogo = true;
            System.out.println("Jogador " + jogador.getCor() + " ganhou!");
            break;
        } else if ((jogador.getObjetivo().equals(objetivos[13]))) {
            ArrayList<Pais> territorios = jogador.getTerritoriosPossuidos();
            int contagem = 0;
            if (territorios.size() >= 18) {
                for (Pais pais : territorios) {
                    if (pais.getTropas() >= 2)
                        contagem++; // significa que esse pais tem 2 ou mais tropas. deve ser 18 vezes
                }
                if (contagem >= 18) { // significa que tenho mais de 18 territorios com 2 ou mais tropas
                    jogador.ganhouJogo = true;
                    System.out.println("Jogador " + jogador.getCor() + " ganhou!");
                    break;
                }
            }

        }
    }
        }


    ArrayList<Pais> geraListaSorteioTerritorios() {
        ArrayList<Pais> listaTodosPaises = new ArrayList<>();
        listaTodosPaises.addAll(Arrays.asList(Tabuleiro.Africa));
        listaTodosPaises.addAll(Arrays.asList(Tabuleiro.AmericaNorte));
        listaTodosPaises.addAll(Arrays.asList(Tabuleiro.Asia));
        listaTodosPaises.addAll(Arrays.asList(Tabuleiro.AmericaSul));
        listaTodosPaises.addAll(Arrays.asList(Tabuleiro.Europa));
        listaTodosPaises.addAll(Arrays.asList(Tabuleiro.Oceania));
        return listaTodosPaises;


    }

    public void sorteiaTerritorios() {
        ArrayList<Pais> listaTodosPaises;
        listaTodosPaises = geraListaSorteioTerritorios();
        Collections.shuffle(listaTodosPaises);
        while (!listaTodosPaises.isEmpty()) {
            for (Jogador jogador : jogadores) {
                if (!listaTodosPaises.isEmpty()) {
                    jogador.addTerritoriosPossuidos(listaTodosPaises.get(0));
                    listaTodosPaises.get(0).addTropas(1); // eu add 1 exercito nesse pais
                    listaTodosPaises.get(0).setDono(jogador);
                    listaTodosPaises.remove(listaTodosPaises.get(0));

                }
            }
        }
        for (Jogador jogador : jogadores) {
            System.out.println("\nTerritorios jogador " + jogador.getCor());
            imprimeListaTerritorios(jogador.getTerritoriosPossuidos());
            System.out.println("Numero de territorios: " + jogador.getNumTerritorios());
        }

    }

    public void imprimeListaTerritorios(ArrayList<Pais> territorios) {
        for (Pais pais : territorios) {
            System.out.println(pais.getNome());
        }
    }

    public void gerarJogadores(ArrayList<String> coresEscolhidas) {

        for (String cor : coresEscolhidas) {
            jogadores.add(new Jogador(Jogador.Cor.valueOf(cor)));
        }

        // Agora você tem uma lista de jogadores com as cores escolhidas
        for (Jogador jogador : jogadores) {
            System.out.println("Jogador " + jogador.getCor() + " escolheu a cor: " + jogador.getCor());
        }
        System.out.println("Cores escolhidas: " + coresEscolhidas);
        System.out.println("--------------------------------------------------------");
    }

    public void checaContinentesJogador() {
        for (Jogador jogador : jogadores) {
            // System.out.printf("Jogador (%d):\n", i + 1);
            // Chama o método checaContinentes para o jogador atual
            jogador.checaContinentes();
            System.out.println("Jogador " + jogador.getCor() + " tem os seguintes continentes: " + jogador.getContinentesPossuidos());
            //System.out.println("Fim\n");
        }
    }

    public void checarTropasGanhar() {
        for (Jogador jogador : jogadores) {
            int tropasGanhar;
            tropasGanhar = jogador.getNumTerritorios() / 2;
            tropasGanhar += checarTropasAMais(jogador);
            if (tropasGanhar < 3)
                tropasGanhar = 3;
            jogador.setTropasParaAdicionar(tropasGanhar);
        }
    }

    // fazer depois funcao de checar troca de cartas. Teremos de add mais tropas
    private int checarTropasAMais(Jogador jogador) {
        ArrayList<String> cont = jogador.checaContinentes();
        int tropas = 0;
        if (cont.contains("Africa")) {
            tropas += 3;
        }
        if (cont.contains("Europa")) {
            tropas += 5;
        }
        if (cont.contains("Asia")) {
            tropas += 7;
        }
        if (cont.contains("Oceania")) {
            tropas += 2;
        }
        if (cont.contains("AmericaSul")) {
            tropas += 2;
        }
        if (cont.contains("AmericaNorte")) {
            tropas += 5;
        }
        return tropas;
    }

    public void imprimeTropasARecber() {
        for (Jogador jogador : jogadores) {
            System.out.println("Tropas a receber jogador " + (jogador.getCor()) + ": " + jogador.getTropasParaAdicionar());
        }
    }

    // Método para imprimir informações sobre os territórios e tropas
    private void imprimirTerritoriosETropas(Jogador jogador) {
        System.out.println("Territórios e Tropas:\n");

        for (Pais territorio : jogador.getTerritoriosPossuidos()) {
            System.out.println("Território: " + territorio.getNome());
            System.out.println("Tropas: " + territorio.getTropas());
            System.out.println();
        }
    }

    public void posicionamentoExercitos() {
        for (Jogador jogador : jogadores) {
            System.out.println("\nJogador: " + jogador.getCor());
            imprimirTerritoriosETropas(jogador);

            while (jogador.getTropasParaAdicionar() > 0) {
                System.out.println("Tropas disponíveis: " + jogador.getTropasParaAdicionar());

                // Exemplo:
                Scanner scanner = new Scanner(System.in);
                System.out.println("Em qual território você quer adicionar as tropas?");
                String territorioNome = scanner.next();

                Pais territorio = null;
                for (Pais pais : jogador.getTerritoriosPossuidos()) {
                    if (pais.getNome().equals(territorioNome)) {
                        territorio = pais;
                        break;
                    }
                }

                if (territorio == null) {
                    System.out.println("Território inválido.");
                    continue;
                }

                System.out.println("Quantas tropas você quer adicionar? (Digite 0 para sair)");
                int quantidade = scanner.nextInt();
                if (quantidade > jogador.getTropasParaAdicionar()) {
                    System.out.println("Mais tropas do que você tem. Tente novamente");
                    continue;
                }

                if (quantidade == 0) {
                    break;
                }
                territorio.addTropas(quantidade);
                imprimirTerritoriosETropas(jogador);
                jogador.diminuiTropasParaAdicionar(quantidade);

            }
        }
    }

public void trocaCartasPoligono() {
        /*
    int resposta = JOptionPane.showConfirmDialog(null, "Iniciar Troca de Cartas", "Trocar Cartas", JOptionPane.YES_NO_OPTION);

    if (resposta != JOptionPane.YES_OPTION) {
        resposta = JOptionPane.YES_OPTION;
    }

         */

    JOptionPane.showMessageDialog(null, "TROCA DE CARTAS - Escolha três formas para trocar ou clique cancelar:");
    //jogadores.get(0).addPoligonosPossuidos(Cartas.Territorio.allTerritorios[0]);
    //jogadores.get(0).addPoligonosPossuidos(Cartas.Territorio.allTerritorios[1]);
    //jogadores.get(0).addPoligonosPossuidos(Cartas.Territorio.allTerritorios[2]);
    //jogadores.get(1).addPoligonosPossuidos(Cartas.Territorio.allTerritorios[52]);
    for (int i = 0; i < jogadores.size(); i++) {
        JOptionPane.showMessageDialog(null, "\nJogador: " + jogadores.get(i).getCor());

        ArrayList<String> cartas = new ArrayList<>();
        for (Cartas.Territorio carta : jogadores.get(i).getPoligonosPossuidos()) {
            cartas.add("Forma: " + carta.getPoligono() + "\nPais: " + carta.getPais() + "\n");
            //JOptionPane.showMessageDialog(null, "Forma: " + carta.getPoligono() + "\nPais: " + carta.getPais() + "\n");
        }
        // Agora, vamos exibir as informações
        StringBuilder mensagem = new StringBuilder();
        for(String carta : cartas){
            mensagem.append(carta).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());

        if(jogadores.get(i).getPoligonosPossuidos().isEmpty()){
            continue;
        }

        String pais1 = JOptionPane.showInputDialog("Digite o nome do primeiro país:");

        if (pais1 == null) { // Se o usuário pressionar Cancelar
            continue;
        }

        if (pais1.equals("Coringa1") || pais1.equals("Coringa2")) {
            Cartas.Territorio pais1Selecionada = null;
            for (Cartas.Territorio territorio : jogadores.get(i).getPoligonosPossuidos()) {
                if (territorio.getPais().equals(pais1)) {
                    pais1Selecionada = territorio;
                }
                if (pais1Selecionada != null) break;
            }
            if (pais1Selecionada != null) {
                JOptionPane.showMessageDialog(null, "Troca bem-sucedida! Adicionando tropas ao prêmio...");
                comparaPremios(jogadores.get(i));
                jogadores.get(i).removePoligonosPossuidos(pais1Selecionada);
                cartasEmJogo.add(pais1Selecionada); // devolvo a carta para as cartas em jogo
                continue;
            } else {
                JOptionPane.showMessageDialog(null, "Você não tem a carta coringa");
            }
        }

        String pais2 = JOptionPane.showInputDialog("Digite o nome do segundo país:");
        String pais3 = JOptionPane.showInputDialog("Digite o nome do terceiro país:");

        Cartas.Territorio pais1Selecionada = null;
        Cartas.Territorio pais2Selecionada = null;
        Cartas.Territorio pais3Selecionada = null;

        for (Cartas.Territorio territorio : jogadores.get(i).getPoligonosPossuidos()) {
            if (territorio.getPais().equals(pais1)) {
                pais1Selecionada = territorio;
            }
            if (territorio.getPais().equals(pais2)) {
                pais2Selecionada = territorio;
            }
            if (territorio.getPais().equals(pais3)) {
                pais3Selecionada = territorio;
            }
            if (pais1Selecionada != null && pais2Selecionada != null && pais3Selecionada != null) {
                break;
            }
        }

        if (pais1Selecionada != null && pais2Selecionada != null && pais3Selecionada != null) {
            // Agora você pode verificar se os três países selecionados são iguais ou diferentes
            if (pais1Selecionada.equals(pais2Selecionada) && pais2Selecionada.equals(pais3Selecionada)) {
                JOptionPane.showMessageDialog(null,"Troca bem-sucedida! Adicionando tropas ao prêmio...");
                comparaPremios(jogadores.get(i));
                jogadores.get(i).removePoligonosPossuidos(pais1Selecionada);
                jogadores.get(i).removePoligonosPossuidos(pais2Selecionada);
                jogadores.get(i).removePoligonosPossuidos(pais3Selecionada);
                cartasEmJogo.add(pais1Selecionada);
                cartasEmJogo.add(pais2Selecionada);
                cartasEmJogo.add(pais3Selecionada);
                // adicionar novamente ao baralho apos criar distribuicao de cartas

            } else if (!pais1Selecionada.equals(pais2Selecionada) && !pais2Selecionada.equals(pais3Selecionada) && !pais1Selecionada.equals(pais3Selecionada)) {
                JOptionPane.showMessageDialog(null,"Troca bem-sucedida! Adicionando tropas ao prêmio...");
                comparaPremios(jogadores.get(i));
                jogadores.get(i).removePoligonosPossuidos(pais1Selecionada);
                jogadores.get(i).removePoligonosPossuidos(pais2Selecionada);
                jogadores.get(i).removePoligonosPossuidos(pais3Selecionada);
                cartasEmJogo.add(pais1Selecionada);
                cartasEmJogo.add(pais2Selecionada);
                cartasEmJogo.add(pais3Selecionada);
                // adicionar novamente ao baralho apos criar distribuicao de cartas

            } else {
                JOptionPane.showMessageDialog(null,"Combinação inválida de formas.");
            }
        } else {
            JOptionPane.showMessageDialog(null,"Um ou mais países não foram encontrados na lista de polígonos.");
        }
    }
}

    public void ataque() {

        int resposta = JOptionPane.showConfirmDialog(null, "Deseja Atacar", "Trocar Cartas", JOptionPane.YES_NO_OPTION);

        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

        // ... Restante do código para a troca de cartas
    }

    private void comparaPremios(Jogador jogador) {
        if (jogador.getPremio() == 0) {
            jogador.adicionaTropasParaAdicionar(4);
        } else if (jogador.getPremio() == 1) {
            jogador.adicionaTropasParaAdicionar(6);
        } else if (jogador.getPremio() == 2) {
            jogador.adicionaTropasParaAdicionar(8);
        } else if (jogador.getPremio() == 3) {
            jogador.adicionaTropasParaAdicionar(10);
        } else if (jogador.getPremio() == 4) {
            jogador.adicionaTropasParaAdicionar(12);
        } else if (jogador.getPremio() == 5) {
            jogador.adicionaTropasParaAdicionar(15);
        } else if (jogador.getPremio() == 6) {
            jogador.adicionaTropasParaAdicionar(20);
        } else {
            jogador.adicionaTropasParaAdicionar(25);
        }
        jogador.addPremio();
    }


    public void distribuiCartas() { // distribui cartas para cada jogador que conquistou um territorio
        Collections.shuffle(cartasEmJogo);

        for (Jogador jogador : jogadores) {
            if (jogador.conquistouTerritorio) {
                jogador.addPoligonosPossuidos(cartasEmJogo.get(0)); // damos uma carta ao jogador
                jogador.conquistouTerritorio = false; // setamos como false para próxima rodada
                cartasEmJogo.remove(cartasEmJogo.get(0)); // removemos a carta
            }
        }

    }
}
/*
    public void paintComponentApi(Graphics g) {

        for (Jogador jogador : jogadores) {
            ArrayList<Pais> territoriosDoJogador = jogador.getTerritoriosPossuidos();

            for (Pais territorio : territoriosDoJogador) {
                int x = territorio.getX();
                int y = territorio.getY();

                if(jogador.getCor() == Jogador.Cor.azul){
                    g.setColor(Color.BLUE);
                } else if (jogador.getCor() == Jogador.Cor.preto) {
                    g.setColor(Color.BLACK);
                }
                else if (jogador.getCor() == Jogador.Cor.branco) {
                    g.setColor(Color.WHITE);
                }
                else if (jogador.getCor() == Jogador.Cor.vermelho) {
                    g.setColor(Color.RED);
                }
                else if (jogador.getCor() == Jogador.Cor.verde) {
                    g.setColor(Color.GREEN);
                }
                else {
                    g.setColor(Color.YELLOW);
                }

                g.fillOval(x, y, 25, 25);

                if (jogador.getCor() == Jogador.Cor.branco)
                    g.setColor(Color.BLACK);

                else
                    g.setColor(Color.WHITE);

                g.drawString(Integer.toString(territorio.getTropas()), x + 7, y + 16);
                System.out.println("Loop dos territaaas\n");
            }
        }
    }
}

*/

/*
 public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagem, 0, 0, null);

        for (Jogador jogador : jogadores) {
            List<Territorio> territoriosDoJogador = jogador.getTerritorios();

            for (Territorio territorio : territoriosDoJogador) {
                int x = territorio.getX();
                int y = territorio.getY();
                g.setColor(jogador.getCor());
                g.fillOval(x, y, 50, 30);
                g.setColor(Color.WHITE);
                g.drawString("99", x + 20, y + 20);
            }
        }
 */


/*
class myMain{
    public static void main(String[] args) {
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


        // Adicionando polígonos ao jogador
        Cartas.Territorio territorio1 = Cartas.Territorio.allTerritorios[51];
        Cartas.Territorio territorio2 = Cartas.Territorio.allTerritorios[1];
        Cartas.Territorio territorio3 = Cartas.Territorio.allTerritorios[2];

        ArrayList<Cartas.Territorio> poligonosPossuidos = new ArrayList<>();
        poligonosPossuidos.add(territorio1);
        poligonosPossuidos.add(territorio2);
        poligonosPossuidos.add(territorio3);


        api.jogadores.get(0).setPoligonosPossuidos(poligonosPossuidos);

        api.trocaCartasPoligono();

        //System.out.println(api.jogadores.get(0).getPoligonosPossuidos().get(0)); // expected == null
        System.out.println(api.jogadores.get(0).getPremio()); // expected 1

    }
}
*/


