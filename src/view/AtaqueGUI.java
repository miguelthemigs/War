
package view;

import model.ApiAcess;
import model.ApiRemanejar;

import javax.swing.*;

public class AtaqueGUI extends JFrame {
    ApiAcess api = ApiAcess.getInstancia();
    ApiRemanejar remaneja = ApiRemanejar.getInstancia();
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
    public void mostraRemanejar() {remaneja.remanejarTropas();}

}

