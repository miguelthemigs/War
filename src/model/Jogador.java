package model;

class Jogador {
    private enum Cor {
        vermelho, azul, verde, amarelo, roxo, laranja
    }
    private Cor cor;
    private int premio;
    private int numContinentes;
    private int numTerritorios;

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

