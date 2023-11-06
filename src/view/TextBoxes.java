package view;

import model.ApiAcess;
import model.ApiToView;

import javax.swing.*;
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

   public void criarInterface() {



       JFrame frame = new JFrame("Seleção de Territórios");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(500, 400);

       JPanel panel = new JPanel();
       frame.add(panel);

       JButton startButton = new JButton("Iniciar Jogo");

       int[] jogadorAtual = {0}; // Usamos um array para contornar o problema
       List<JSpinner> spinners = new ArrayList<>(); // Lista de spinners

       startButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               ArrayList<Integer> tropasLista = new ArrayList<>();
               int atual = jogadorAtual[0]; // Obtem o valor atual
               if (atual < jogadores.size()) {
                   panel.removeAll(); // Limpa o painel para a próxima iteração

                   Object jogador = jogadores.get(atual);

                   for (Object territorio : ApiToView.retornaTerritorios(jogador)) {
                       SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, ApiToView.retornaTropas(jogador), 1);
                       JSpinner spinnerTropas = new JSpinner(spinnerModel);
                       spinners.add(spinnerTropas);

                       panel.add(new JLabel("Território " + territorio + " - tropas: " + ApiToView.retornaTropas(jogador)));
                       panel.add(spinnerTropas);
                       panel.add(Box.createRigidArea(new Dimension(10, 10)));
                   }

                   panel.add(startButton);
                   frame.setVisible(true);

                   jogadorAtual[0]++; // Vai para o próximo jogador

               }else {
                   // Todos os jogadores fizeram suas seleções, aqui você pode fazer o que for necessário
                   // (por exemplo, iniciar o jogo)
                   for (int i = 0; i < spinners.size(); i++) {
                       JSpinner spinner = spinners.get(i);
                       int tropas = (int) spinner.getValue();
                       tropasLista.add(tropas);
                   }
                   for(Object jogador: jogadores){
                        ArrayList<String> territorios = ApiToView.retornaTerritorios(jogador);
                        for(int i = 0; i < territorios.size(); i++){
                            ApiToView.setarTropas(territorios.get(i), tropasLista.get(i));
                        }
                   }
               }
           }
       });

       panel.add(startButton);
       frame.setVisible(true);



       }



}
