package model;

class Pais {
    private final String nome;
    private int tropas;
    public String[] fronteiras;
    private Jogador dono;


    public Pais(String nome, String[] fronteiras) {
        this.nome = nome;
        this.fronteiras = fronteiras;
    }
    public String getNome() {
        return nome;
    }

    public void setDono(Jogador dono) {
        this.dono = dono;
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

    public String[] getFronteiras() {
        return fronteiras;
    }
    public void setFronteiras(String[] fronteiras) {
        this.fronteiras = fronteiras;
    }



}
