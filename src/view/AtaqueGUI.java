
package view;

import model.ApiAcess;
import model.ApiAttack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

