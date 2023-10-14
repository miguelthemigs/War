package model;

import model.Cartas.Territorio;

import java.util.ArrayList;

public class Jogador {
    public enum Cor {
        vermelho, azul, verde, amarelo, preto, branco
    }
    private Cor cor;
    private int premio;
    private int tropasParaAdicionar; // exercitos para o jogador adicionar
    private int numContinentes;
    private ArrayList<Pais> territoriosPossuidos;
    private ArrayList<Pais[]> continentesPossuidos;
    private ArrayList<Territorio> poligonosPossuidos; // aqui so usarei os poligonos getPoligono()
    private String objetivo;

    // deve numero de cartas e Cartas cartas

    public Jogador() {
    }

    public Jogador(Cor cor){
        this.cor = cor;
        this.territoriosPossuidos = new ArrayList<Pais>();
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

    public void setNumContinentes(int numContinentes) {
        this.numContinentes = numContinentes;
    }


    public void setContinentesPossuidos(ArrayList<Pais[]> continentesPossuidos) {
        this.continentesPossuidos = continentesPossuidos;
    }

    public ArrayList<Pais> getTerritoriosPossuidos() {
        return territoriosPossuidos;
    }

    public void addTerritoriosPossuidos(Pais territorios) {
        territoriosPossuidos.add(territorios);
    }
    public int getNumTerritorios() {
        return territoriosPossuidos.size();
    }
    public int getNumNovasTropas() {
        return (territoriosPossuidos.size())/2;
    }
    public void checaContinentes(){

        Pais[][] Continentes = {Tabuleiro.Africa, Tabuleiro.AmericaSul, Tabuleiro.AmericaNorte, Tabuleiro.Asia, Tabuleiro.Oceania, Tabuleiro.Europa};
        String[] contNome = {"Africa", "AmericaSul", "AmericaNorte", "Asia", "Oceania", "Europa"};
        int contIndice = 0;

        // Verifique se todos os territórios do continente estão na lista de territórios possuídos
        for (Pais[] continente : Continentes) {
            boolean todosNoContinente = true;
            for (Pais pais : continente) {
                if (!getTerritoriosPossuidos().contains(pais)) {
                    todosNoContinente = false;
                    break;  // Se um território não estiver na lista, não é necessário continuar verificando
                }
            }
            // Se todos os territórios do continente estiverem na lista, imprima "OPAAAA"
            if (todosNoContinente) {
                System.out.println("Este jogador possui o seguinte continente: " + contNome[contIndice]);
            }
            contIndice++;
        }
    }
}

