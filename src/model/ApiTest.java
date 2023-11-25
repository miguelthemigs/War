package model;

import controller.Observer;
import view.GameMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiTest extends JFrame {

    ApiAcess api = ApiAcess.getInstancia();
    private static ApiTest instancia = null;


    //Gambiarra
    //Gambiarra
    //Gambiarra
    public static ArrayList<Jogador> jogadores = ApiAcess.jogadores;
    //Gambiarra
    //Gambiarra
    //Gambiarra



    private JComboBox<String> meusPaisesComboBox = new JComboBox<>();
    private JComboBox<String> paisesFronteirasComboBox = new JComboBox<>();

    public static ApiTest getInstancia() {
        if (instancia == null) {
            instancia = new ApiTest();
        }
        return instancia;
    }


    public void remanejarTropas() {
        for (Jogador jogador : jogadores) {

            // Configurar frame
            setTitle("Remanejar Tropas - Jogador " + jogador.getCor());
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLocation(1025, 0);

            SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 99, 1);
            JSpinner spinnerTropas = new JSpinner(spinnerModel);
            int remanjemaentos = 0;
            Map<Pais, Integer> lista = new HashMap<>();
            // Infinite loop until "Cancelar" is pressed
            while (true) {
                // Atualizar ComboBox de Meus Países
                updateMeusPaisesComboBox(meusPaisesComboBox, jogador);

                // Reseta items da combo box de fronteiras
                resetaComboBox(paisesFronteirasComboBox);

                // Clear the contents of the frame for each iteration
                getContentPane().removeAll();
                repaint();


                // Configurar layout
                setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

                // Criar botão de remanejar
                JButton remanejarButton = new JButton("Remanejar");
                JButton cancelarButton = new JButton("Finalizar");
                remanejarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String origem = (String) meusPaisesComboBox.getSelectedItem();
                        String destino = (String) paisesFronteirasComboBox.getSelectedItem();
                        int quantidadeTropas = (int) spinnerTropas.getValue();

                        // Verificar se os países selecionados são válidos
                        if (destino == null || origem.equals("Selecione o pais de origem")) {
                            JOptionPane.showMessageDialog(null, "Selecione países válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                            return; // Sai do método se os países não forem válidos
                        }

                        // Realizar o remanejamento de tropas
                        realizarRemanejamento(origem, destino, quantidadeTropas, lista);
                        GameMap.atualizarElipses();

                        // Atualizar ComboBox de Meus Países após o remanejamento
                        updateMeusPaisesComboBox(meusPaisesComboBox, jogador);
                        // Reseta items da combo box de fronteiras
                        resetaComboBox(paisesFronteirasComboBox);
                    }
                });
                cancelarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();  // Close the frame on "Cancelar"
                    }
                });

                // Criar contêiner para os botões "Remanejar" e "Finalizar"
                JPanel botoesPanel = new JPanel();

                // Configurando layout para o contêiner dos botões
                botoesPanel.setLayout(new FlowLayout());

                // Adicionar botões ao contêiner
                botoesPanel.add(remanejarButton);
                botoesPanel.add(cancelarButton);

                // Adicionar componentes ao frame
                add(new JLabel("Selecione o território de origem:"));
                add(meusPaisesComboBox);
                add(new JLabel("Selecione o território de destino:"));
                add(paisesFronteirasComboBox);
                add(new JLabel("Selecione a quantidade de tropas a serem remanejadas:"));
                add(spinnerTropas);
                add(botoesPanel); // Adicionar o contêiner dos botões

                // Adiciona ActionListener para atualizar "destino" combo box quando "origem" é selecionado
                meusPaisesComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obter o item selecionado em "origem"
                        String selectedOrigem = (String) meusPaisesComboBox.getSelectedItem();
                        // Condicao para evitar bugs
                        if (selectedOrigem != null && !selectedOrigem.equals("Selecione o pais de origem")) {
                            // Atualizar "destino" combo box com base na seleção em "origem"
                            updateFronteiraComboBox(selectedOrigem, paisesFronteirasComboBox, jogador);
                        }
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
            adicionarTropasAposRemanejar(lista); // aqui espera o fim do remanejamento
            lista.clear();
            GameMap.atualizarElipses();
        }
    }

    private void realizarRemanejamento(String origem, String destino, int quantidadeTropas, Map<Pais, Integer> list) {

        System.out.printf("Remanejamento de tropas: %d tropas de %s para %s\n", quantidadeTropas, origem, destino);
        if(quantidadeTropas == (api.StringtoPais(origem).getTropas())){
            ApiAcess api = ApiAcess.getInstancia();
            api.StringtoPais(origem).removeTropas(quantidadeTropas);
            list.put(api.StringtoPais(destino), quantidadeTropas);
        }
        else{
            JOptionPane.showMessageDialog(null, "Por favor, selecione um numero de tropas que voce tenha.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        }


    public void adicionarTropasAposRemanejar(Map<Pais, Integer> lista) {
        for (Map.Entry<Pais, Integer> entry : lista.entrySet()) {
            Pais pais = entry.getKey();
            int valorAdicional = entry.getValue();
            pais.addTropas(valorAdicional);
        }

    }
    private void updateMeusPaisesComboBox(JComboBox<String> meusPaisesComboBox, Jogador jogador) {
        // Zerar a JComboBox
        meusPaisesComboBox.removeAllItems();

        meusPaisesComboBox.addItem("Selecione o pais de origem");

        // Adicionar apenas os territórios com mais de 1 tropa
        for (Pais territorio : jogador.getTerritoriosPossuidos()) {
            if (territorio.getTropas() > 1 && !obterVizinhosPossuidos(territorio.getNome(), jogador).isEmpty()) {
                meusPaisesComboBox.addItem(territorio.getNome());
            }
        }
    }

    private void resetaComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        comboBox.addItem("Selecione o pais alvo");
    }

    private void updateFronteiraComboBox(String selectedOrigem, JComboBox<String> alvoComboBox, Jogador jogador) {
        // Obter vizinhos não possuídos com base na seleção em "origem"
        ArrayList<Pais> vizinhosPossuidos = obterVizinhosPossuidos(selectedOrigem, jogador);

        // Limpar o combo box "alvo" e adicionar os vizinhos não possuídos
        alvoComboBox.removeAllItems();
        for (Pais pais : vizinhosPossuidos) {
            alvoComboBox.addItem(pais.getNome());
        }
    }

    private ArrayList<Pais> obterVizinhosPossuidos(String territorio, Jogador jogador) {
        System.out.println("\n\n'"+jogador.getCor()+"'\n\n");
        ArrayList<Pais> possuidos = new ArrayList<>();
        Pais pais = api.StringtoPais(territorio);
        String[] fronteiras = pais.getFronteiras();
        for (String fronteira : fronteiras) {
            if (jogador.getTerritoriosPossuidos().contains(api.StringtoPais(fronteira))) {
                possuidos.add(api.StringtoPais(fronteira));
            }
        }
        return possuidos;
    }
}