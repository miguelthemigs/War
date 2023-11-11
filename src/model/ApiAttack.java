package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    private void atualizarAlvosComboBox() {
        alvosComboBox.removeAllItems();
        String territorioSelecionado = (String) territoriosComboBox.getSelectedItem();
        if (territorioSelecionado != null) {
            for (Pais vizinho : obterVizinhosNaoPossuidos(territorioSelecionado, jogadorAtual)) {
                alvosComboBox.addItem(vizinho.getNome());
            }
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

    private void realizarAtaque() {
        String territorioSelecionado = (String) territoriosComboBox.getSelectedItem();
        String alvoSelecionado = (String) alvosComboBox.getSelectedItem();
        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Você deseja atacar " + alvoSelecionado + "?",
                "Atacar Território",
                JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            // Implemente aqui a lógica de ataque
            // Pode chamar outra função ou método para realizar o ataque
            // e fazer as atualizações necessárias no jogo
            JOptionPane.showMessageDialog(null, "Atacando " + alvoSelecionado + " a partir de " + territorioSelecionado);
        }
        jogadorAtualIndex++;
    }
}






