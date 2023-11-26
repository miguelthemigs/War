package model;

import view.GameMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiRemanejar extends JFrame {
    ApiAcess api = ApiAcess.getInstancia();
    private static ApiRemanejar instancia = null;
    public static ArrayList<Jogador> jogadores = ApiAcess.jogadores;
    private Jogador jogadorAtual;
    private JComboBox<String> meusPaisesComboBox = new JComboBox<>();
    private JComboBox<String> paisesFronteirasComboBox = new JComboBox<>();

    public static ApiRemanejar getInstancia() {
        if (instancia == null) {
            instancia = new ApiRemanejar();
        }
        return instancia;
    }

    private void configureFrame() {
        setTitle("Remanejar Tropas - Jogador " + jogadorAtual.getCor());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(1025, 0);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 99, 1);
        JSpinner spinnerTropas = new JSpinner(spinnerModel);
        Map<Pais, Integer> lista = new HashMap<>();

        while (true) {
            updateMeusPaisesComboBox(meusPaisesComboBox);
            resetaComboBox(paisesFronteirasComboBox);

            getContentPane().removeAll();
            repaint();

            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            JButton remanejarButton = new JButton("Remanejar");
            JButton cancelarButton = new JButton("Finalizar");

            remanejarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String origem = (String) meusPaisesComboBox.getSelectedItem();
                    String destino = (String) paisesFronteirasComboBox.getSelectedItem();
                    int quantidadeTropas = (int) spinnerTropas.getValue();

                    if (destino == null || origem.equals("Selecione o pais de origem")) {
                        JOptionPane.showMessageDialog(null, "Selecione países válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    realizarRemanejamento(origem, destino, quantidadeTropas, lista);
                    GameMap.atualizarElipses();

                    updateMeusPaisesComboBox(meusPaisesComboBox);
                    resetaComboBox(paisesFronteirasComboBox);
                }
            });

            cancelarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            JPanel botoesPanel = new JPanel();
            botoesPanel.setLayout(new FlowLayout());
            botoesPanel.add(remanejarButton);
            botoesPanel.add(cancelarButton);

            add(new JLabel("Selecione o território de origem:"));
            add(meusPaisesComboBox);
            add(new JLabel("Selecione o território de destino:"));
            add(paisesFronteirasComboBox);
            add(new JLabel("Quantidade de tropas a serem remanejadas:"));
            add(spinnerTropas);
            add(botoesPanel);

            meusPaisesComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedOrigem = (String) meusPaisesComboBox.getSelectedItem();
                    if (selectedOrigem != null && !selectedOrigem.equals("Selecione o pais de origem")) {
                        updateFronteiraComboBox(selectedOrigem, paisesFronteirasComboBox);

                        // Set the maximum value of the spinner to the troops in the selected origin country
                        int maxTroops = api.StringtoPais(selectedOrigem).getTropas() - 1;
                        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, maxTroops, 1);
                        spinnerTropas.setModel(spinnerModel);
                    } else {
                        resetaComboBox(paisesFronteirasComboBox);

                        // If no valid country is selected, set the maximum value of the spinner to 1 or a positive integer
                        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
                        spinnerTropas.setModel(spinnerModel);
                    }
                }
            });


            setVisible(true);

            try {
                while (isVisible()) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if (!isVisible()) {
                break;
            }
        }
        adicionarTropasAposRemanejar(lista);
        lista.clear();
        GameMap.atualizarElipses();
        System.out.printf("FIM DO LOOP");
    }



    public void realizarRemanejamento(String origem, String destino, int quantidadeTropas, Map<Pais, Integer> list) {
        System.out.printf("Remanejamento de tropas: %d tropas de %s para %s\n", quantidadeTropas, origem, destino);
        if (quantidadeTropas < (api.StringtoPais(origem).getTropas())) {
            ApiAcess api = ApiAcess.getInstancia();
            api.StringtoPais(origem).removeTropas(quantidadeTropas);
            list.put(api.StringtoPais(destino), quantidadeTropas);
        } else {
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

    public void updateMeusPaisesComboBox(JComboBox<String> meusPaisesComboBox) {
        meusPaisesComboBox.removeAllItems();
        meusPaisesComboBox.addItem("Selecione o pais de origem");
        for (Pais territorio : jogadorAtual.getTerritoriosPossuidos()) {
            if (territorio.getTropas() > 1 && !obterVizinhosPossuidos(territorio.getNome()).isEmpty()) {
                meusPaisesComboBox.addItem(territorio.getNome());
            }
        }
    }

    public void resetaComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        comboBox.addItem("Selecione o pais alvo");
    }

    public void updateFronteiraComboBox(String selectedOrigem, JComboBox<String> alvoComboBox) {
        ArrayList<Pais> vizinhosPossuidos = obterVizinhosPossuidos(selectedOrigem);
        alvoComboBox.removeAllItems();
        for (Pais pais : vizinhosPossuidos) {
            alvoComboBox.addItem(pais.getNome());
        }
    }

    public ArrayList<Pais> obterVizinhosPossuidos(String territorio) {
        ArrayList<Pais> possuidos = new ArrayList<>();
        Pais pais = api.StringtoPais(territorio);
        String[] fronteiras = pais.getFronteiras();
        for (String fronteira : fronteiras) {
            if (jogadorAtual.getTerritoriosPossuidos().contains(api.StringtoPais(fronteira))) {
                possuidos.add(api.StringtoPais(fronteira));
            }
        }
        return possuidos;
    }

    public boolean podeRemanejar(){
        for(Pais pais : jogadorAtual.getTerritoriosPossuidos()) {
            if (pais.getTropas() > 1 && !obterVizinhosPossuidos(pais.getNome()).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    public void remanejarTropas() {
        for (Jogador jogador : jogadores) {
            jogadorAtual = jogador;
            if (podeRemanejar()){
                configureFrame();
            }
        }
    }
}
