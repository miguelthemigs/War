package model;

import controller.Observable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ApiAttack extends JFrame {

    private int jogadorAtualIndex;
    private ArrayList<Jogador> jogadores;
    private JComboBox<String> territoriosComboBox;
    private JComboBox<String> alvosComboBox;
    private JButton atacarButton;
    private JButton cancelarButton;
    private static ApiAttack instancia = null;
    private Jogador jogadorAtual;
    private ArrayList<Pais> territoriosJogador;

    private ApiAttack() {
        // O construtor é privado para evitar a criação externa de instâncias
        this.jogadorAtualIndex = 0;
        this.territoriosJogador = new ArrayList<>();
    }

    public static ApiAttack getInstancia(ArrayList<Jogador> jogadores) {
        if (instancia == null) {
            instancia = new ApiAttack();
            instancia.jogadores = jogadores;
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

    /*
    public void iniciarAtaque() {
        // Configurar frame
        setTitle("Ataque - Jogador " + jogadorAtual.getCor());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Criar combo box para territórios do jogador
        territoriosComboBox = new JComboBox<>();
        for (Pais territorio : territoriosJogador) {
            territoriosComboBox.addItem(territorio.getNome());
        }
        territoriosComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAlvosComboBox();
            }
        });

        // Criar combo box para alvos
        alvosComboBox = new JComboBox<>();
        atualizarAlvosComboBox();

        // Criar botão de ataque
        atacarButton = new JButton("Atacar");
        cancelarButton = new JButton("Cancelar");
        atacarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarAtaque();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Adicionar componentes ao frame
        add(new JLabel("Selecione o território de origem:"));
        add(territoriosComboBox);
        add(new JLabel("Selecione o território alvo:"));
        add(alvosComboBox);
        add(atacarButton);
        add(cancelarButton);

        // Exibir frame
        setVisible(true);
    }
    */
    public void iniciarAtaque() {
        // Configurar frame
        setTitle("Ataque - Jogador " + jogadorAtual.getCor());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Infinite loop until "Cancelar" is pressed
        while (true) {
            // Clear the contents of the frame for each iteration
            getContentPane().removeAll();
            repaint();

            // Configurar layout
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            // Criar combo box para Meus Países
            JComboBox<String> meusPaisesComboBox = new JComboBox<>();
            for (Pais territorio : territoriosJogador) {
                if (territorio.getTropas() > 1)
                    meusPaisesComboBox.addItem(territorio.getNome());
            }

            // Criar combo box para Países Fronteiras
            JComboBox<String> paisesFronteirasComboBox = new JComboBox<>();
            paisesFronteirasComboBox.addItem("-");

            // Criar botão de ataque
            JButton atacarButton = new JButton("Atacar");
            JButton cancelarButton = new JButton("Finalizar");
            atacarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String origem = (String) meusPaisesComboBox.getSelectedItem();
                    String alvo = (String) paisesFronteirasComboBox.getSelectedItem();
                    realizarAtaque(origem, alvo);
                }
            });
            cancelarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();  // Close the frame on "Cancelar"
                }
            });

            // Adicionar componentes ao frame
            add(new JLabel("Selecione o território de origem:"));
            add(meusPaisesComboBox);
            add(new JLabel("Selecione o território alvo:"));
            add(paisesFronteirasComboBox);
            add(atacarButton);
            add(cancelarButton);

            // Adiciona ActionListener para atualizar "alvo" combo box quando "origem" é selecionado
            meusPaisesComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Obter o item selecionado em "origem"
                    String selectedOrigem = (String) meusPaisesComboBox.getSelectedItem();
                    // Atualizar "alvo" combo box com base na seleção em "origem"
                    updateAlvoComboBox(selectedOrigem, paisesFronteirasComboBox, jogadorAtual);
                }
            });

            // Exibir frame
            setVisible(true);

            // Wait for the frame to be disposed before breaking the loop
            try {
                while (isVisible()) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            // Break the loop if the frame was disposed (Cancelar was pressed)
            if (!isVisible()) {
                break;
            }
        }
    }

    // Método para atualizar o combo box "alvo" com base na seleção em "origem"
    private void updateAlvoComboBox(String selectedOrigem, JComboBox<String> alvoComboBox, Jogador jogador) {
        // Obter vizinhos não possuídos com base na seleção em "origem"
        ArrayList<Pais> vizinhosNaoPossuidos = obterVizinhosNaoPossuidos(selectedOrigem, jogador);

        // Limpar o combo box "alvo" e adicionar os vizinhos não possuídos
        alvoComboBox.removeAllItems();
        for (Pais pais : vizinhosNaoPossuidos) {
            alvoComboBox.addItem(pais.getNome());
        }
    }


    private ArrayList<Pais> obterVizinhosNaoPossuidos(String territorio, Jogador jogador) {
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

    private void realizarAtaque(String territorioSelecionado, String alvoSelecionado) {
        System.out.printf("Origem do ataque: %s\nAlvo do ataque: %s", territorioSelecionado, alvoSelecionado);
        ApiAcess api = ApiAcess.getInstancia();

        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Você deseja atacar " + alvoSelecionado + "?",
                "Atacar Território",
                JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            // Lógica de ataque
            int tropasAtaque = api.StringtoPais(territorioSelecionado).getTropas();
            int tropasDefesa = api.StringtoPais(alvoSelecionado).getTropas();
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

            for (int i = 0; i < 3; i++) {
                resultadosAtaque[i] = (i < numeroDadosAtaque) ? Dados.jogarVermelho() : 0;
                resultadosDefesa[i] = (i < numeroDadosDefesa) ? Dados.jogarAmarelo() : 0;
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
            // Observer.notify()
        }
    }

    // Função para transformar um array em uma string, substituindo 0 por "-"
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

    // Função para inverter a ordem de um array
    private void reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
    /*private void reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }*/
}





