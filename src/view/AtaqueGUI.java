
package view;

import model.ApiAcess;

import javax.swing.*;

public class AtaqueGUI extends JFrame {
    ApiAcess api = ApiAcess.getInstancia();
    static AtaqueGUI instancia = null;
    public static AtaqueGUI getInstancia() {
        if (instancia == null) {
            instancia = new AtaqueGUI();
        }
        return instancia;
    }
    public void mostraAtaque(){
        api.ataque();
    }

}

