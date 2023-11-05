package view;

import model.ApiAcess;

import javax.swing.*;


public class TextBoxes {
   // primeiro devemos colocar a combobox, para cada jogador, e mostrar os territorios que ele tem, e ele escolher quantas e aonde colocar
    // depois, no clique do botao adicionar, ter um actionlistener, que chama uma funcao do controller, que posiciona as tropas para gente, e repinta o painel com as elipses

   public static void criarInterface() {
       JFrame frame = new JFrame("Seleção de Territórios");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(400, 300);

       JPanel panel = new JPanel();
       frame.add(panel);

       JComboBox<Integer> comboBoxPlayer = new JComboBox<>();



   }


}
