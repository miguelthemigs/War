package model;

public class Pais {
    private String nome;
    private int tropas;
    private Pais[] fronteiras;


    public Pais(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public int getTropas() {
        return tropas;
    }
    public void setTropas(int tropas) {
        this.tropas = tropas;
    }
    public void addTropas(int tropas) {
        this.tropas += tropas;
    }
    public void removeTropas(int tropas) {
        this.tropas -= tropas;
    }

    public Pais[] getFronteiras() {
        return fronteiras;
    }
    public void setFronteiras(Pais[] fronteiras) {
        this.fronteiras = fronteiras;
    }

}
