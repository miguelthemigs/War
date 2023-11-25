package model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;
import java.util.List;

public class ApiAcess {
    String[] objetivos = Cartas.objetivo; // devo tirar um onjetivo da lista a cada sorteio
    private static ApiAcess instancia = null;
    private static final Object lockRecarga = new Object(); // Adicionado aqui
    public static ArrayList<Jogador> jogadores = new ArrayList<>();
    ArrayList<Cartas.Territorio> cartasEmJogo = new ArrayList<>(List.of(Cartas.Territorio.allTerritorios));
    private boolean perguntaSalvamentoFeita = false;

    private  String salvamentoPath;

    public void reset() {
        for (Jogador jogador : jogadores){
            jogador.reset();
        }
        jogadores.clear();

        cartasEmJogo = new ArrayList<>(List.of(Cartas.Territorio.allTerritorios));
        perguntaSalvamentoFeita = false;
        salvamentoPath = null;
    }


    // Método para obter a instância única
    public static ApiAcess getInstancia() {
        if (instancia == null) {
            instancia = new ApiAcess();
        }
        return instancia;
    }

    // Método para reiniciar a instância
    public void resetInstancia() {
        instancia = null;
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
    public static ArrayList<String> textoObjetivos(){
        ArrayList<String> objetivos = new ArrayList<>();
        for(Jogador jogador: jogadores){
            objetivos.add("Objetivo jogador ''"+ ApiToView.retornaCor(jogador) + "'':\n- " +  ApiToView.retornaObjetivo(jogador));
        }
        return objetivos;
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
                jogador.getTerritoriosPossuidos().size() >= 24;
    }
    public boolean checaSeGanhou() { // deve ser chamada ao fim de cada ataque
        for (Jogador jogador : jogadores) { // checa os objetivos de cores
            for (int i = 0; i <= 5; i++) { // itero pelos objetivos de cores
                String objetivo = objetivos[i];
                Jogador.Cor corAlvo = Jogador.Cor.values()[i];

                if (atendeCriterioVitoriaCores(jogador, objetivo, corAlvo)) { // se o jogador tiver algum objetivo de cores, ele entra aqui
                    jogador.ganhouJogo = true;
                    JOptionPane.showMessageDialog(null, "Jogador " + jogador.getCor() + " ganhou!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
                for (Jogador oponente : jogadores) { // checa se nao eiste o jogador que deveria destruir
                    if (oponente.getCor().equals(corAlvo) && oponente.getTerritoriosPossuidos().isEmpty()) {
                        jogador.ganhouJogo = true;
                        JOptionPane.showMessageDialog(null, "Jogador " + jogador.getCor() + " ganhou!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    }
                }
            }

            if (jogador.getObjetivo().equals(objetivos[6])) {
                ArrayList<String> continentesDoJogador = jogador.checaContinentes();
                if (continentesDoJogador.contains("AmericaNorte") && continentesDoJogador.contains("Africa")) {
                    jogador.ganhouJogo = true;
                    JOptionPane.showMessageDialog(null, "Jogador " + jogador.getCor() + " ganhou!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            } else if ((jogador.getObjetivo().equals(objetivos[7]))) {
                ArrayList<String> continentesDoJogador = jogador.checaContinentes();
                if (continentesDoJogador.contains("Asia") && continentesDoJogador.contains("Africa")) {
                    jogador.ganhouJogo = true;
                    JOptionPane.showMessageDialog(null, "Jogador " + jogador.getCor() + " ganhou!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            } else if ((jogador.getObjetivo().equals(objetivos[8]))) {
                ArrayList<String> continentesDoJogador = jogador.checaContinentes();
                if (continentesDoJogador.contains("Oceania") && continentesDoJogador.contains("AmericaNorte")) {
                    jogador.ganhouJogo = true;
                    JOptionPane.showMessageDialog(null, "Jogador " + jogador.getCor() + " ganhou!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            } else if ((jogador.getObjetivo().equals(objetivos[9]))) {
                ArrayList<String> continentesDoJogador = jogador.checaContinentes();
                if (continentesDoJogador.contains("Asia") && continentesDoJogador.contains("AmericaSul")) {
                    jogador.ganhouJogo = true;
                    JOptionPane.showMessageDialog(null, "Jogador " + jogador.getCor() + " ganhou!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            } else if ((jogador.getObjetivo().equals(objetivos[10]))) {
                ArrayList<String> continentesDoJogador = jogador.checaContinentes();
                if (continentesDoJogador.contains("Europa") && continentesDoJogador.contains("AmericaSul") && continentesDoJogador.size() >= 3) {
                    jogador.ganhouJogo = true;
                    JOptionPane.showMessageDialog(null, "Jogador " + jogador.getCor() + " ganhou!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            } else if ((jogador.getObjetivo().equals(objetivos[11]))) {
                ArrayList<String> continentesDoJogador = jogador.checaContinentes();
                if (continentesDoJogador.contains("Europa") && continentesDoJogador.contains("Oceania") && continentesDoJogador.size() >= 3) {
                    jogador.ganhouJogo = true;
                    JOptionPane.showMessageDialog(null, "Jogador " + jogador.getCor() + " ganhou!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            } else if ((jogador.getObjetivo().equals(objetivos[12])) && jogador.getTerritoriosPossuidos().size() >= 24) {
                jogador.ganhouJogo = true;
                JOptionPane.showMessageDialog(null, "Jogador " + jogador.getCor() + " ganhou!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                return true;
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
                        JOptionPane.showMessageDialog(null, "Jogador " + jogador.getCor() + " ganhou!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    }
                }

            }
    }
        return false;
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
                    listaTodosPaises.get(0).setTropas(1); // eu add 1 exercito nesse pais
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

        System.out.println("Cores escolhidas: " + coresEscolhidas);
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
            jogador.adicionaTropasParaAdicionar(tropasGanhar);
            System.out.println("Jogador " + jogador.getCor() + " ganhou: " + tropasGanhar);
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

        // Iterar sobre os jogadores
        for (Jogador jogador : jogadores) {
            if (jogador.getPoligonosPossuidos().size() >= 3) {
                boolean cartasSelecionadasCorretamente = false;

                while (!cartasSelecionadasCorretamente) {
                    // Criar um painel para checkboxes
                    JPanel checkboxPanel = new JPanel();
                    checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));

                    // Adicionar rótulo indicando o jogador
                    JLabel label = new JLabel("Cartas do jogador " + jogador.getCor() + ":");
                    checkboxPanel.add(label);

                    ArrayList<String> cartas = new ArrayList<>();

                    for (Cartas.Territorio carta : jogador.getPoligonosPossuidos()) {
                        cartas.add("Forma: " + carta.getPoligono() + " (" + carta.getPais() + ")");
                    }

                    // Adicionar checkboxes ao painel
                    JCheckBox[] checkboxes = new JCheckBox[cartas.size()];
                    for (int j = 0; j < cartas.size(); j++) {
                        checkboxes[j] = new JCheckBox(cartas.get(j));
                        checkboxPanel.add(checkboxes[j]);
                    }

                    // Exibir o painel de checkboxes
                    int result = JOptionPane.showConfirmDialog(null, checkboxPanel, "Selecione as cartas", JOptionPane.OK_CANCEL_OPTION);

                    // Se o usuário clicar em OK, processar as cartas selecionadas
                    if (result == JOptionPane.OK_OPTION) {
                        // Lista para armazenar as cartas selecionadas
                        ArrayList<Cartas.Territorio> cartasSelecionadas = new ArrayList<>();
                        for (int j = 0; j < checkboxes.length; j++) {
                            if (checkboxes[j].isSelected()) {
                                // Adicione lógica para processar a carta selecionada
                                // Você pode acessar a carta correspondente usando jogadores.get(i).getPoligonosPossuidos().get(j)

                                // Obtém a carta selecionada
                                Cartas.Territorio cartaSelecionada = jogador.getPoligonosPossuidos().get(j);

                                // Adiciona a carta à lista de cartas selecionadas
                                cartasSelecionadas.add(cartaSelecionada);
                            }
                        }

                        // Verifica se o jogador selecionou exatamente 3 cartas
                        if (cartasSelecionadas.size() == 3) {
                            // Imprime os detalhes das 3 cartas
                            System.out.println("Forma da carta selecionada 1: '" + cartasSelecionadas.get(0).getPoligono() + "'(" + cartasSelecionadas.get(0).getPais() + ")");
                            System.out.println("Forma da carta selecionada 2: '" + cartasSelecionadas.get(1).getPoligono() + "'(" + cartasSelecionadas.get(1).getPais() + ")");
                            System.out.println("Forma da carta selecionada 3: '" + cartasSelecionadas.get(2).getPoligono() + "'(" + cartasSelecionadas.get(2).getPais() + ")");


                            // Verifica se as cartas são todas iguais ou todas diferentes (carta coringa já se aplica na logica de diferente)
                            if (
                                    (cartasSelecionadas.get(0).getPoligono().equals(cartasSelecionadas.get(1).getPoligono()) &&
                                    cartasSelecionadas.get(0).getPoligono().equals(cartasSelecionadas.get(2).getPoligono()) &&
                                    cartasSelecionadas.get(1).getPoligono().equals(cartasSelecionadas.get(2).getPoligono())) ||
                                            ((cartasSelecionadas.get(0).getPoligono().equals(Cartas.Poligono.coringa) && cartasSelecionadas.get(1).getPoligono().equals(cartasSelecionadas.get(2).getPoligono())) ||
                                                    ((cartasSelecionadas.get(1).getPoligono().equals(Cartas.Poligono.coringa) && cartasSelecionadas.get(0).getPoligono().equals(cartasSelecionadas.get(2).getPoligono()))) ||
                                                            (cartasSelecionadas.get(2).getPoligono().equals(Cartas.Poligono.coringa) && cartasSelecionadas.get(0).getPoligono().equals(cartasSelecionadas.get(1).getPoligono()))
                            )) {
                                System.out.println("IGUAIS");
                                // Adicione a lógica para realizar a troca quando as cartas são iguais
                                cartasSelecionadasCorretamente = true;
                                cartasEmJogo.add(cartasSelecionadas.get(0)); // devolvo a carta troca para lista de cartas
                                cartasEmJogo.add(cartasSelecionadas.get(1));
                                cartasEmJogo.add(cartasSelecionadas.get(2));
                                jogador.removePoligonosPossuidos(cartasSelecionadas.get(0));
                                jogador.removePoligonosPossuidos(cartasSelecionadas.get(1));
                                jogador.removePoligonosPossuidos(cartasSelecionadas.get(2));
                                comparaPremios(jogador);
                                break;

                            } else if (
                                    !cartasSelecionadas.get(0).getPoligono().equals(cartasSelecionadas.get(1).getPoligono()) &&
                                    !cartasSelecionadas.get(1).getPoligono().equals(cartasSelecionadas.get(2).getPoligono()) &&
                                    !cartasSelecionadas.get(0).getPoligono().equals(cartasSelecionadas.get(2).getPoligono())
                            ) {

                                System.out.println("DIFF");
                                // Adicione a lógica para realizar a troca quando as cartas são diferentes
                                cartasSelecionadasCorretamente = true;
                                cartasEmJogo.add(cartasSelecionadas.get(0));
                                cartasEmJogo.add(cartasSelecionadas.get(1));
                                cartasEmJogo.add(cartasSelecionadas.get(2));
                                jogador.removePoligonosPossuidos(cartasSelecionadas.get(0));
                                jogador.removePoligonosPossuidos(cartasSelecionadas.get(1));
                                jogador.removePoligonosPossuidos(cartasSelecionadas.get(2));
                                comparaPremios(jogador);
                                break;

                            } else {
                                // Se não são todas iguais nem todas diferentes, exibe uma mensagem de erro
                                JOptionPane.showMessageDialog(null, "Por favor, selecione cartas que são todas iguais ou todas diferentes.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // Se o jogador não selecionou exatamente 3 cartas, exibe uma mensagem de erro
                            JOptionPane.showMessageDialog(null, "Por favor, selecione exatamente 3 cartas.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Se o usuário clicar em Cancelar, sair do loop
                        break;
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "O jogador " + jogador.getCor() + " possui menos de 3 cartas.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public Pais StringtoPais(String nomeTerritorio) {
        for (Pais pais : geraListaSorteioTerritorios()) {
            if (pais.getNome().equals(nomeTerritorio)) {
                return pais;
            }
        }
        System.out.printf("\nnomeTerritorio: %s\n", nomeTerritorio);
        throw new RuntimeException("Nao encontramos o pais pedido");
    }

    public void confirmaJogadores(){
        jogadores.removeIf(jogador -> jogador.getTerritoriosPossuidos().isEmpty());
    }

    public void ataque() {
        ApiAttack api = ApiAttack.getInstancia(jogadores);
        ApiAcess api2 = ApiAcess.getInstancia();
        boolean ganhou = false;

        while (api.podeIniciarProximoAtaque()) {
            ganhou = api2.checaSeGanhou();
            if(!ganhou){
                api.iniciarProximoAtaque();
                confirmaJogadores();
            }
            else break;
        }


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

    public void salvamento() {
        // Pergunta sobre onde salvar só será feita se perguntaSalvamentoFeita for false
        if (!perguntaSalvamentoFeita) {
            salvamentoPath = escolheFile();
        }

        // Verifica se o arquivo a ser salvo é do tipo texto
        if (salvamentoPath != null && !salvamentoPath.toLowerCase().endsWith(".txt")) {
            salvamentoPath += ".txt";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(salvamentoPath))) {
            for (Jogador jogador : jogadores) {
                writer.write("Cor: " + jogador.getCor() + "\n");
                writer.write("TropasAdd: " + jogador.getTropasParaAdicionar() + "\n");
                writer.write("Territórios:[\n");
                for (Pais territorio : jogador.getTerritoriosPossuidos()) {
                    writer.write(String.format("%s, %d,\n", territorio.getNome(), territorio.getTropas()));
                }
                writer.write("]\n");

                writer.write("Cartas:[\n");
                for (Cartas.Territorio carta : jogador.getPoligonosPossuidos()) {
                    writer.write(String.format("%s, %s,\n", carta.getPais(), carta.getPoligono()));
                }
                writer.write("]\n");

                writer.write("Premio: " + jogador.getPremio() + "\n");

                writer.write("Objetivo: " + jogador.getObjetivo() + "\n");

                writer.write("\n----\n\n");
            }
            System.out.println("Logs salvos com sucesso no arquivo 'log.txt'");

            // Define perguntaSalvamentoFeita como true
            perguntaSalvamentoFeita = true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar logs: " + e.getMessage());
        }
    }

    public void carregamento() {
        String path = escolheFile();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            String cor = null;
            int tropasAdd = 0;
            int premio = 0;
            String objetivo = null;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Cor: ")) {
                    cor = linha.substring("Cor: ".length());
                    System.out.println("Cor: " + cor);

                    jogadores.add(new Jogador(Jogador.Cor.valueOf(cor)));
                }
                else if (linha.startsWith("TropasAdd: ")) {
                    tropasAdd = Integer.parseInt(linha.substring("TropasAdd: ".length()));
                    System.out.println("TropasAdd: " + tropasAdd);

                    jogadores.get(jogadores.size()-1).setTropasParaAdicionar(tropasAdd);
                }
                else if (linha.startsWith("Territórios:[")) {
                    while ((linha = br.readLine()) != null && !linha.equals("]")) {
                        // Dividir a linha por vírgula
                        String[] partes = linha.split(",");

                        // Extrair valores
                        String nomeTerritorio = partes[0].trim();
                        int valorTerritorio = Integer.parseInt(partes[1].trim());

                        // Imprimir valores
                        System.out.println("Nome do Território: " + nomeTerritorio);
                        System.out.println("Valor do Território: " + valorTerritorio);

                        Pais pais = StringtoPais(nomeTerritorio);
                        jogadores.get(jogadores.size()-1).addTerritoriosPossuidos(pais);
                        pais.setTropas(valorTerritorio);
                    }
                }
                else if (linha.startsWith("Cartas:[")) {
                    while ((linha = br.readLine()) != null && !linha.equals("]")) {
                        // Dividir a linha por vírgula
                        String[] partes = linha.split(",");

                        // Extrair valores
                        String cartaTerritorio = partes[0].trim();
                        String cartaPoligono = partes[1].trim();

                        // Imprimir valores
                        System.out.println("Território da Carta: " + cartaTerritorio);
                        System.out.println("Poligono da Carta: " + cartaPoligono);

                        // Encontrar a carta correspondente
                        Cartas.Territorio Carta = null;
                        for (Cartas.Territorio cartaPais : Cartas.Territorio.allTerritorios) {
                            if (cartaPais.getPais().equals(cartaTerritorio) && cartaPais.getPoligono().toString().equals(cartaPoligono)) {
                                Carta = cartaPais;
                                break;
                            }
                        }

                        if (Carta != null){
                            jogadores.get(jogadores.size()-1).addPoligonosPossuidos(Carta);
                        }
                    }
                }
                else if (linha.startsWith("Premio: ")) {
                    premio = Integer.parseInt(linha.substring("Premio: ".length()));
                    System.out.println("Premio: " + premio);

                    jogadores.get(jogadores.size()-1).setPremio(premio);
                }
                else if (linha.startsWith("Objetivo: ")) {
                    objetivo = linha.substring("Objetivo: ".length());
                    System.out.println("Objetivo: " + objetivo);

                    jogadores.get(jogadores.size()-1).setObjetivo(objetivo);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String escolheFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar arquivo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos de Texto (.txt)", "txt"));

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        } else {
            return null;
        }
    }

}



