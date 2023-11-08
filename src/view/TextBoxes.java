package view;

import model.ApiAcess;
import model.ApiToView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static model.ApiAcess.jogadores;


public class TextBoxes {
   // primeiro devemos colocar a combobox, para cada jogador, e mostrar os territorios que ele tem, e ele escolher quantas e aonde colocar
    // depois, no clique do botao adicionar, ter um actionlistener, que chama uma funcao do controller, que posiciona as tropas para gente, e repinta o painel com as elipses
   private static final Object lock = new Object(); // Adicionado aqui
   public void criarInterface(Runnable callback) {



       JFrame frame = new JFrame("Seleção de Territórios");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(500, 700);

       JPanel panel = new JPanel();
       frame.add(panel);

       JButton startButton = new JButton("Iniciar Jogo");

       int[] jogadorAtual = {0}; // Usamos um array para contornar o problema
       List<JSpinner> spinners = new ArrayList<>(); // Lista de spinners

       startButton.addActionListener(new ActionListener() {
           final int[] soma_tropas = {0};
           public void actionPerformed(ActionEvent e) {
               ArrayList<Integer> tropasLista = new ArrayList<>();
               int atual = jogadorAtual[0]; // Obtem o valor atual
               if (atual < jogadores.size()) {
                   panel.removeAll(); // Limpa o painel para a próxima iteração

                   Object jogador = jogadores.get(atual); // meu jogador atual
                   panel.add(new JLabel("Jogador " + ApiToView.retornaCor(jogador) + " tropas: " + ApiToView.retornaTropas(jogador) + "    "));
                   panel.add(Box.createRigidArea(new Dimension(400, 20)));
                   soma_tropas[0] += ApiToView.retornaTropas(jogador);

                   for (Object territorio : ApiToView.retornaTerritorios(jogador)) {
                       SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, ApiToView.retornaTropas(jogador), 1);
                       JSpinner spinnerTropas = new JSpinner(spinnerModel);
                       spinners.add(spinnerTropas);

                       panel.add(new JLabel("                    " + territorio + " - "));
                       panel.add(spinnerTropas);
                       panel.add(Box.createRigidArea(new Dimension(460, 5)));
                       int finalSoma_tropas = soma_tropas[0];
                       System.out.println(finalSoma_tropas);
                       spinnerTropas.addChangeListener(new ChangeListener() {
                           public void stateChanged(ChangeEvent e) {
                               int soma = 0;
                               for (JSpinner spinner : spinners) {
                                   soma += (int) spinner.getValue();

                               }
                               startButton.setEnabled(soma == finalSoma_tropas); // botao so libera se colocar a quantidade certa de tropas
                           }

                       });
                   }

                   panel.add(startButton);
                   frame.setVisible(true);

                   jogadorAtual[0]++; // Vai para o próximo jogador

               }else {
                   // Todos os jogadores fizeram suas seleções, aqui você pode fazer o que for necessário
                   // (por exemplo, iniciar o jogo)
                  // int valorTotalTropas = 0;
                  // for(Object jog: jogadores){
                    //   valorTotalTropas += ApiToView.retornaTropas(jog);
                 //  }
                  // int valorUsadoTropas = 0;
                  // while(valorTotalTropas > valorUsadoTropas){
                       for (int i = 0; i < spinners.size(); i++) {
                           JSpinner spinner = spinners.get(i);
                           int tropas = (int) spinner.getValue();
                           //System.out.println(tropas);
                           tropasLista.add(tropas);
                           //valorUsadoTropas+=tropas;

                       }
                  // }
                   ArrayList<String> territorios = new ArrayList<>();
                   for(Object jogador: jogadores) {
                       territorios.addAll(ApiToView.retornaTerritorios(jogador));
                   }
                    for(int i = 0; i < tropasLista.size(); i++){
                        ApiToView.setarTropas(territorios.get(i), tropasLista.get(i)); // acertar os territorios pois nao estao certos
                        System.out.println("Territorio "+territorios.get(i)+" tropas: "+ tropasLista.get(i));
                    }


                   frame.setVisible(false);
                   panel.removeAll();
                   synchronized (lock) {
                       lock.notify(); // Notifique a Main para continuar
                       startButton.setVisible(false);
                   }
               }
           }
       });

       panel.add(startButton);
       frame.setVisible(true);

       callback.run();


       }
    public Object getLock() {
        return lock;
    }


}
