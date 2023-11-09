package view;
import javax.swing.*;

import static model.ApiAcess.jogadores;
public class Round {

    public void trocaCartasPoligono() {

        int resposta = JOptionPane.showConfirmDialog(null, "Deseja Atacar", "Trocar Cartas", JOptionPane.YES_NO_OPTION);

        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

        // ... Restante do código para a troca de cartas
    }


}
