package model;

import model.Cartas.Territorio;

import java.util.ArrayList;

public class Jogador {
    public enum Cor {
        vermelho, azul, verde, amarelo, preto, branco
    }
    private Cor cor;
    private int premio;
    private int numContinentes;
    private int numTerritorios;
    private ArrayList<Pais> territoriosPossuidos;
    private ArrayList<Continentes> continentesPossuidos;
    private ArrayList<Territorio> poligonosPossuidos; // aqui so usarei os poligonos getPoligono()
    private String objetivo;

    // deve numero de cartas e Cartas cartas

    public Jogador() {
    }

    public Jogador(Cor cor){
        this.cor = cor;
    }

    public Cor getCor() {
        return this.cor;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getObjetivo() {
        return objetivo;
    }
}

