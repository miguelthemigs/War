package model;

import controller.Observer;
import view.GameMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import static view.GameMap.atualizarElipses;

public class ApiAttack extends JFrame implements Observer {

    private int jogadorAtualIndex;
    private ArrayList<Jogador> jogadores;

    private static ApiAttack instancia = null;
    private Jogador jogadorAtual;
    private ArrayList<Pais> territoriosJogador;
    // Criar combo box para Meus Países
    private JComboBox<String> meusPaisesComboBox = new JComboBox<>();
    // Criar combo box para Países Fronteiras
    private JComboBox<String> paisesFronteirasComboBox = new JComboBox<>();

    private ApiAttack() {
        // O construtor é privado para evitar a criação externa de instâncias
        this.jogadorAtualIndex = 0;
        this.territoriosJogador = new ArrayList<>();
    }

    // Método para reiniciar a instância
    public static void resetInstancia() {
        instancia = null;
    }

    public static ApiAttack getInstancia(ArrayList<Jogador> jogadores) {
        if (instancia == null) {
            instancia = new ApiAttack();
            instancia.jogadores = jogadores;

            // Criar uma instância de SuaClasse e registrar como observador
            Observer observador = GameMap::atualizarElipses;
            instancia.addObserver(observador);
        }
        return instancia;
    }

    public boolean podeIniciarProximoAtaque() {
        return jogadorAtualIndex < jogadores.size();
    }

    public void iniciarProximoAtaque() {
        if (podeIniciarProximoAtaque()) {
            setJogadorAtual(jogadores.get(jogadorAtualIndex));
            jogadorAtualIndex++;
            iniciarAtaque();
        }
    }

    public void setJogadorAtual(Jogador jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
        this.territoriosJogador = jogadorAtual.getTerritoriosPossuidos();
    }
    private final ArrayList<Observer> observers = new ArrayList<>();


    public void addObserver(Observer o) {
            observers.add(o);
    }

    public void notifyObservers() {
            for (Observer observer : observers) {
                observer.onNotify();
        }
    }
    public void onNotify() {
            atualizarElipses();  // Chame o método desejado para atualizar as elipses na interface
    }

    // Método para obter todas as informações sobre um ataque
    public void iniciarAtaque() {
        // Configurar frame
        setTitle("Ataque - Jogador " + jogadorAtual.getCor());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(1025,0);

        // Loop infinito enquanto 'cancelar' não é pressionado
        while (true) {
            System.out.println("\nENTROU NO LOOP ATAQUE\n\n");

            // Atualizar ComboBox de Meus Países
            updateMeusPaisesComboBox(meusPaisesComboBox);

            // Reseta items da combo box alvo
            resetaComboBox(paisesFronteirasComboBox);

            // Limpa o conteudo do frame a cada iteração
            getContentPane().removeAll();
            repaint();

            // Configurar layout
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            // Criar botão de ataque
            JButton atacarButton = new JButton("Atacar");
            JButton cancelarButton = new JButton("Finalizar");
            atacarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String origem = (String) meusPaisesComboBox.getSelectedItem();
                    String alvo = (String) paisesFronteirasComboBox.getSelectedItem();

                    // Verificar se o país de origem selecionado é válido
                    if (alvo == null || origem.equals("Selecione o pais de origem")) {
                        JOptionPane.showMessageDialog(null, "Selecione um país de origem válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return; // Sai do método se o país não for válido
                    }

                    realizarAtaque(origem, alvo);

                    // Atualizar ComboBox de Meus Países após a conquista
                    updateMeusPaisesComboBox(meusPaisesComboBox);
                    // Reseta items da combo box
                    resetaComboBox(paisesFronteirasComboBox);
                }
            });
            cancelarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();  // Close the frame on "Cancelar"
                }
            });

            // Criar contêiner para os botões "Atacar" e "Finalizar"
            JPanel botoesPanel = new JPanel();

            // Configurando layout para o contêiner dos botões
            botoesPanel.setLayout(new FlowLayout());

            // Adicionar botões ao contêiner
            botoesPanel.add(atacarButton);
            botoesPanel.add(cancelarButton);

            // Adicionar componentes ao frame
            add(new JLabel("Selecione o território de origem:"));
            add(meusPaisesComboBox);
            add(new JLabel("Selecione o território alvo:"));
            add(paisesFronteirasComboBox);
            add(botoesPanel); // Adicionar o contêiner dos botões

            // Adiciona ActionListener para atualizar "alvo" combo box quando "origem" é selecionado
            meusPaisesComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Obter o item selecionado em "origem"
                    String selectedOrigem = (String) meusPaisesComboBox.getSelectedItem();
                    // Condicao para evitar bugs
                    if (selectedOrigem != null && !selectedOrigem.equals("Selecione o pais de origem")){
                        // Atualizar "alvo" combo box com base na seleção em "origem"
                        updateAlvoComboBox(selectedOrigem, paisesFronteirasComboBox);
                    }
                }
            });

            // Exibir frame
            setVisible(true);

            // Espera o frame ser descartado para poder sair do loop
            try {
                while (isVisible()) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            // Quebra o frame se 'cancelar' for pressionado
            if (!isVisible()) {
                break;
            }
        }
        System.out.println("\nSAIU NO LOOP ATAQUE\n\n");
    }

    // Método para atualizar o combo box "alvo" com base na seleção em "origem"
    private void updateAlvoComboBox(String selectedOrigem, JComboBox<String> alvoComboBox) {
        // Obter vizinhos não possuídos com base na seleção em "origem"
        ArrayList<Pais> vizinhosNaoPossuidos = obterVizinhosNaoPossuidos(selectedOrigem);

        // Limpar o combo box "alvo" e adicionar os vizinhos não possuídos
        alvoComboBox.removeAllItems();
        for (Pais pais : vizinhosNaoPossuidos) {
            alvoComboBox.addItem(pais.getNome());
        }
    }

    private static void resetaComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        comboBox.addItem("Selecione o pais alvo");
    }

    // Método para saber quais vizinhos de um território possuem outros donos
    private ArrayList<Pais> obterVizinhosNaoPossuidos(String territorio) {
        ApiAcess api = ApiAcess.getInstancia();
        ArrayList<Pais> naoPossuidos = new ArrayList<>();
        Pais pais = api.StringtoPais(territorio);
        String[] fronteiras = pais.getFronteiras();
        for(String fronteira: fronteiras){
            if(!jogadorAtual.getTerritoriosPossuidos().contains(api.StringtoPais(fronteira))){ // se o jogador nao tem o pais que faz fronteira
                naoPossuidos.add(api.StringtoPais(fronteira));
            }
        }
        return naoPossuidos;
    }

    // Método para manipular e realizar todas as informações sobre um ataque
    private void realizarAtaque(String territorioSelecionado, String alvoSelecionado) {

        System.out.printf("Origem do ataque: %s\nAlvo do ataque: %s\n\n", territorioSelecionado, alvoSelecionado);
        ApiAcess api = ApiAcess.getInstancia();

        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Você deseja atacar " + alvoSelecionado + "?",
                "Atacar Território",
                JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            // Lógica de ataque
            Pais paisAtaque = api.StringtoPais(territorioSelecionado);
            int tropasAtaque = paisAtaque.getTropas();

            Pais paisDefesa = api.StringtoPais(alvoSelecionado);
            int tropasDefesa = paisDefesa.getTropas();
            int numeroDadosAtaque = 0, numeroDadosDefesa = 0;

            int[] resultadosAtaque = new int[]{0, 0, 0};
            int[] resultadosDefesa = new int[]{0, 0, 0};

            // Atacante usa um dado a menos do que suas tropas
            if (tropasAtaque >= 4) {
                numeroDadosAtaque = 3;
            } else if (tropasAtaque == 3) {
                numeroDadosAtaque = 2;
            } else if (tropasAtaque == 2) {
                numeroDadosAtaque = 1;
            }

            // Defensor usa até três dados, se tiver pelo menos três tropas
            if (tropasDefesa >= 3) {
                numeroDadosDefesa = 3;
            } else if (tropasDefesa == 2) {
                numeroDadosDefesa = 2;
            } else if (tropasDefesa == 1) {
                numeroDadosDefesa = 1;
            }


            int manipular = JOptionPane.showOptionDialog(
                    null,
                    "Deseja manipular o resultado dos dados?",
                    "Dados",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Manipular", "Jogar"},
                    "Manipular"
            );

            if (manipular == JOptionPane.YES_OPTION) {
                int[][] resultados = mostrarJanelaDados(numeroDadosAtaque, numeroDadosDefesa);
                resultadosAtaque = resultados[0];
                resultadosDefesa = resultados[1];
            }

            else {
                for (int i = 0; i < 3; i++) {
                    resultadosAtaque[i] = (i < numeroDadosAtaque) ? Dados.jogarVermelho() : 0;
                    resultadosDefesa[i] = (i < numeroDadosDefesa) ? Dados.jogarAmarelo() : 0;
                }
            }

            // Ordena os resultados em ordem decrescente
            Arrays.sort(resultadosAtaque);
            reverseArray(resultadosAtaque);
            Arrays.sort(resultadosDefesa);
            reverseArray(resultadosDefesa);

            // Exibe o resultado do ataque
            String mensagemResultado = "Ataque concluído!\n\n";
            mensagemResultado += "Resultado dos dados de ataque: " + arrayToString(resultadosAtaque) + "\n";
            mensagemResultado += "Resultado dos dados de defesa: " + arrayToString(resultadosDefesa) + "\n\n";

            int attackLoss = 0;
            int defenseLoss = 0;
            for (int i = 0; i < Math.min(numeroDadosAtaque, numeroDadosDefesa); i++) {
                if (resultadosAtaque[i] > resultadosDefesa[i]) {
                    defenseLoss++;
                } else {
                    attackLoss++;
                }
            }

            mensagemResultado += "Tropas perdidas pelo atacante: " + attackLoss + "\n";
            mensagemResultado += "Tropas perdidas pelo defensor: " + defenseLoss + "\n";

            JOptionPane.showMessageDialog(null, mensagemResultado);

            api.StringtoPais(territorioSelecionado).removeTropas(attackLoss); // Já remove as tropas
            tropasAtaque -= attackLoss;
            System.out.println("\nremoveu do atacante: "+ attackLoss);

            api.StringtoPais(alvoSelecionado).removeTropas(defenseLoss);
            tropasDefesa -= defenseLoss;
            System.out.println("removeu do defensor: "+ defenseLoss);
            notifyObservers();

            if(tropasDefesa < 1) {
                JOptionPane.showMessageDialog(null, "Territorio conquistado! " + alvoSelecionado);
                jogadorAtual.conquistouTerritorio = true; // Indica que conquistou um territorio, e deve ganhar uma carta
                removePaisDoDefensor(paisDefesa);
                jogadorAtual.addTerritoriosPossuidos(paisDefesa);
                jogadorAtual.addNumTerritoriosConquistados(); // Adiciona no numero de territorios conquistados, para ver se ele foi vitorioso
                int tropasTransferir = pedirQuantidadeTropasTransferir(tropasAtaque);

                // Transferir tropas
                paisDefesa.addTropas(tropasTransferir);
                paisAtaque.removeTropas(tropasTransferir);
                System.out.println("\nTerritorio conquistado (" + alvoSelecionado + ")\n");
                notifyObservers();
            }

        }
    }

    // Método para exibir e operar a manipulação dos dados
    private static int[][] mostrarJanelaDados(int ataqueDados, int defesaDados) {

        int[] resultadosAtaque = new int[ataqueDados];
        int[] resultadosDefesa = new int[defesaDados];

        // Janela para o Ataque
        String[] opcoesAtaque = {"1", "2", "3", "4", "5", "6"};
        JComboBox<String>[] ataqueComboBoxes = new JComboBox[ataqueDados];
        for (int i = 0; i < ataqueDados; i++) {
            ataqueComboBoxes[i] = new JComboBox<>(opcoesAtaque);
        }
        JPanel ataquePanel = new JPanel();
        ataquePanel.setLayout(new GridLayout(ataqueDados + 1, 2));
        ataquePanel.add(new JLabel("Escolha os valores de dados de ataque:"));
        for (int i = 0; i < ataqueDados; i++) {
            ataquePanel.add(ataqueComboBoxes[i]);
        }
        int resultAtaque = JOptionPane.showConfirmDialog(null, ataquePanel, "Dados de Ataque", JOptionPane.OK_CANCEL_OPTION);

        if (resultAtaque == JOptionPane.OK_OPTION) {
            for (int i = 0; i < ataqueDados; i++) {
                resultadosAtaque[i] = Integer.parseInt((String) ataqueComboBoxes[i].getSelectedItem());
            }
        }

        // Janela para a Defesa
        String[] opcoesDefesa = {"1", "2", "3", "4", "5", "6"};
        JComboBox<String>[] defesaComboBoxes = new JComboBox[defesaDados];
        for (int i = 0; i < defesaDados; i++) {
            defesaComboBoxes[i] = new JComboBox<>(opcoesDefesa);
        }
        JPanel defesaPanel = new JPanel();
        defesaPanel.setLayout(new GridLayout(defesaDados + 1, 2));
        defesaPanel.add(new JLabel("Escolha os valores de dados de defesa:"));
        for (int i = 0; i < defesaDados; i++) {
            defesaPanel.add(defesaComboBoxes[i]);
        }
        int resultDefesa = JOptionPane.showConfirmDialog(null, defesaPanel, "Dados de Defesa", JOptionPane.OK_CANCEL_OPTION);

        if (resultDefesa == JOptionPane.OK_OPTION) {
            for (int i = 0; i < defesaDados; i++) {
                resultadosDefesa[i] = Integer.parseInt((String) defesaComboBoxes[i].getSelectedItem());
            }
        }

        return new int[][] {resultadosAtaque, resultadosDefesa};
    }

    // Método para atualizar a ComboBox com base no território selecionado
    private void updateMeusPaisesComboBox(JComboBox<String> meusPaisesComboBox) {
        // Zerar a JComboBox
        meusPaisesComboBox.removeAllItems();

        meusPaisesComboBox.addItem("Selecione o pais de origem");

        // Adicionar apenas os territórios com mais de 1 tropa
        for (Pais territorio : jogadorAtual.getTerritoriosPossuidos()) {
            if (territorio.getTropas() > 1 && territorio.getFronteiras().length > 0) {
                meusPaisesComboBox.addItem(territorio.getNome());
            }
        }
    }

    // Método para obter tropas a serem transferidas após conquista
    private int pedirQuantidadeTropasTransferir(int tropasAtaque) {
        // Criar um JSpinner para permitir que o jogador selecione o número de tropas a serem transferidas
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, Math.min(tropasAtaque-1, 3), 1);
        JSpinner spinnerTropas = new JSpinner(spinnerModel);

        // Criar um painel para agrupar o rótulo e o spinner
        JPanel spinnerPanel = new JPanel();
        spinnerPanel.add(new JLabel("Selecione o número de tropas a serem transferidas (1-" + Math.min(tropasAtaque-1, 3) +"): "));
        spinnerPanel.add(spinnerTropas);

        // Exibir um diálogo para o jogador inserir a quantidade de tropas a serem transferidas
        int result = JOptionPane.showConfirmDialog(
                null,
                spinnerPanel,
                "Transferir Tropas",
                JOptionPane.OK_CANCEL_OPTION
        );

        // Se o jogador pressionar OK, retorna a quantidade selecionada, caso contrário, retorna 0
        return (result == JOptionPane.OK_OPTION) ? (int) spinnerTropas.getValue() : 1;
    }
    private void removePaisDoDefensor(Pais pais){
        for(Jogador jogador: jogadores){
            if(jogador.getTerritoriosPossuidos().contains(pais)) {
                jogador.removeTerritorio(pais);
                jogador.removeNumTerritoriosConquistados();

                // Definir quem matou o territorio mais recente do jogador
                jogador.setMatou(jogadorAtual);
            }
        }
    }

    // Método para transformar um array em uma string, substituindo 0 por "-"
    private String arrayToString(int[] array) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            result.append((array[i] != 0) ? array[i] : "-");
            if (i < array.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    // Método para inverter a ordem de um array
    private void reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
}