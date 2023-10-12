package model;

class Pais {
    private String nome;
    private int tropas;
    public String[] fronteiras;


    public Pais(String nome, String[] fronteiras) {
        this.nome = nome;
        this.fronteiras = fronteiras;
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

    public String[] getFronteiras() {
        return fronteiras;
    }
    public void setFronteiras(String[] fronteiras) {
        this.fronteiras = fronteiras;
    }

    //Pais Angola = new Pais("Angola");
    //Pais Africa = new Pais("Africa");
    //Pais Madagascar = new Pais("Madagascar");

    //Fronteiras
    //Angola.setFronteiras(new Pais[]{Africa, Madagascar});

    //Continentes
    //Pais[] America = {Africa, Madagascar};


}
