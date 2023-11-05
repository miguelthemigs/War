package model;

import model.Cartas.Territorio;

import java.util.ArrayList;

class Jogador {
    public enum Cor {
        vermelho, azul, verde, amarelo, preto, branco
    }
    private Cor cor;
    private int premio;
    private int tropasParaAdicionar; // exercitos para o jogador adicionar
    private int numContinentes;
    private ArrayList<Pais> territoriosPossuidos;
    private ArrayList<String> continentesPossuidos;
    private ArrayList<Territorio> poligonosPossuidos; // aqui so usarei os poligonos getPoligono()
    private String objetivo;

    public boolean conquistouTerritorio = false;

    public boolean ganhouJogo = false;

    public Jogador() {
    }

    public int getPremio() {
        return premio;
    }

    public void addPremio() {
        this.premio++;
    }

    public Jogador(Cor cor){
        this.cor = cor;
        this.territoriosPossuidos = new ArrayList<Pais>();
        this.continentesPossuidos = new ArrayList<String>();
        this.poligonosPossuidos = new ArrayList<Territorio>();
    }

    public ArrayList<Territorio> getPoligonosPossuidos() {
        return poligonosPossuidos;
    }

    public void setPoligonosPossuidos(ArrayList<Cartas.Territorio> poligonosPossuidos) {
        this.poligonosPossuidos = poligonosPossuidos;
    }

    public void addPoligonosPossuidos(Cartas.Territorio poligonosPossuidos) {
        this.poligonosPossuidos.add(poligonosPossuidos);
    }

    public void removePoligonosPossuidos(Cartas.Territorio poligonosPossuidos) {
        this.poligonosPossuidos.remove(poligonosPossuidos);
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

    public void setTropasParaAdicionar(int tropasParaAdicionar) {
        this.tropasParaAdicionar = tropasParaAdicionar;
    }

    public int getTropasParaAdicionar() {
        return tropasParaAdicionar;
    }

    public void diminuiTropasParaAdicionar(int tropas){
        tropasParaAdicionar -= tropas;
    }
    public void adicionaTropasParaAdicionar(int tropas){
        tropasParaAdicionar += tropas;
    }

    public ArrayList<String> getContinentesPossuidos() {
        return continentesPossuidos;
    }

    public int getNumContinentesPossuidos() {
        return continentesPossuidos.size();
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


    public ArrayList<String> checaContinentes(){
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
                continentesPossuidos.add(contNome[contIndice]);
            }
            contIndice++;
        }
        return continentesPossuidos;
    }
}

