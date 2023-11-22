package view;

import model.ApiToView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static model.ApiAcess.jogadores;

public class TextBoxes {
    private static final Object lock = new Object();

    // Método para criar a interface gráfica
    public void criarInterface(Runnable callback) {
        // Configurar a janela principal
        JFrame frame = new JFrame("Seleção de Territórios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 807);
        frame.setLocation(1025, 0);

        JPanel panel = new JPanel();
        frame.add(panel);

        JButton startButton = new JButton("Inserir Tropas");

        // Array para armazenar o índice do jogador atual
        int[] jogadorAtual = {0};
        // Lista de spinners para armazenar os controles de quantidade de tropas
        List<JSpinner> spinners = new ArrayList<>();

        // Adiciona um ouvinte de ação ao botão "Iniciar Jogo"
        startButton.addActionListener(new ActionListener() {
            // Array final para armazenar a soma total de tropas a serem distribuídas
            final int[] soma_tropas = {0};

            public void actionPerformed(ActionEvent e) {
                // Lista para armazenar a quantidade de tropas selecionada para cada território
                ArrayList<Integer> tropasLista = new ArrayList<>();
                int atual = jogadorAtual[0];

                // Verifica se há jogadores restantes
                if (atual < jogadores.size()) {
                    panel.removeAll();

                    // Obtém o jogador atual
                    Object jogador = jogadores.get(atual);
                    JLabel jogadorLabel = new JLabel("Jogador " + ApiToView.retornaCor(jogador) +
                            " tropas: " + ApiToView.retornaTropas(jogador) + "    ");
                    jogadorLabel.setFont(new Font("sans-serif", Font.BOLD, 14));
                    panel.add(jogadorLabel);
                    panel.add(Box.createRigidArea(new Dimension(400, 10)));
                    soma_tropas[0] += ApiToView.retornaTropas(jogador);

                    int territoriosPorLinha = 2;
                    int territorioCount = 0;

                    // Loop sobre os territórios do jogador
                    for (String territorio : ApiToView.retornaTerritorios(jogador)) {
                        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, ApiToView.retornaTropas(jogador), 1);
                        JSpinner spinnerTropas = new JSpinner(spinnerModel);

                        // Ajusta o tamanho da seta do JSpinner
                        Dimension dimension = spinnerTropas.getPreferredSize();
                        dimension = new Dimension(40, dimension.height);
                        spinnerTropas.setPreferredSize(dimension);

                        int margem = Math.max(0, 12 - territorio.length());
                        String espacos = new String(new char[margem]).replace('\0', ' ');

                        // Ajusta o tamanho da fonte para o JLabel do território
                        String formattedTerritorio = espacos + territorio;
                        JLabel territorioLabel = new JLabel(formattedTerritorio + ":");

                        spinners.add(spinnerTropas);

                        panel.add(territorioLabel);
                        panel.add(spinnerTropas);

                        territorioCount++;
                        if (territorioCount % territoriosPorLinha == 0 || territorioCount == ApiToView.retornaTerritorios(jogador).size()) {
                            panel.add(Box.createRigidArea(new Dimension(460, 3)));
                        }
                        int finalSoma_tropas = soma_tropas[0];

                        // Adiciona um ouvinte de mudança para cada JSpinner
                        spinnerTropas.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent e) {
                                int soma = 0;
                                // Calcula a soma total das tropas selecionadas
                                for (JSpinner spinner : spinners) {
                                    soma += (int) spinner.getValue();
                                }
                                // Habilita o botão somente se a soma for igual à quantidade total de tropas
                                startButton.setEnabled(soma == finalSoma_tropas);
                            }
                        });
                    }

                    panel.add(startButton);
                    frame.setVisible(true);

                    // Reinicia as tropas a serem adicionadas
                    ApiToView.reiniciarTropas(jogador, ApiToView.retornaTropas(jogador));

                    jogadorAtual[0]++;
                } else {
                    // Coleta a quantidade de tropas selecionada para cada território
                    for (JSpinner spinner : spinners) {
                        int tropas = (int) spinner.getValue();
                        tropasLista.add(tropas);
                    }

                    // Coleta todos os territórios de todos os jogadores
                    ArrayList<String> territorios = new ArrayList<>();
                    for (Object jogador : jogadores) {
                        territorios.addAll(ApiToView.retornaTerritorios(jogador));
                    }

                    // Define a quantidade de tropas para cada território
                    for (int i = 0; i < tropasLista.size(); i++) {
                        ApiToView.setarTropas(territorios.get(i), tropasLista.get(i));
                        System.out.println("Território " + territorios.get(i) + " tropas: " + tropasLista.get(i));
                    }

                    // Esconde a janela e notifica a controller.Main para continuar
                    frame.setVisible(false);
                    panel.removeAll();
                    synchronized (lock) {
                        lock.notify();
                        startButton.setVisible(false);
                    }
                }
            }
        });

        // Adiciona o botão "Iniciar Jogo" ao painel
        panel.add(startButton);
        frame.setVisible(true);

        // Executa o callback fornecido
        callback.run();
    }

    // Método para obter o objeto de bloqueio
    public Object getLock() {
        return lock;
    }
}
