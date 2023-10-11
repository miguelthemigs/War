package model;

import model.Cartas.Territorio;

import java.util.ArrayList;

public class Jogador {
    private enum Cor {
        vermelho, azul, verde, amarelo, preto, branco
    }
    private Cor cor;
    private int premio;
    private int numContinentes;
    private int numTerritorios;
    private ArrayList<Territorio> territoriosPossuidos; // aqui so usarei o nome dos paises getPais()
    private ArrayList<Continentes> continentesPossuidos;
    private ArrayList<Territorio> poligonosPossuidos; // aqui so usarei os poligonos getPoligono()

    // deve ter Objetivo objetivo
    // deve numero de cartas e Cartas cartas

    public Jogador() {
    }

    public Jogador(Cor cor){
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }


}

