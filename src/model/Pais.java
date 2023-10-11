package model;

public class Pais {
    private String nome;
    private int tropas;
    private Pais[] fronteiras;

    public Pais(String nome) {
        this.nome = nome;
    }

    public int getTropas() {
        return tropas;
    }

    public void setTropas(int tropas) {
        this.tropas = tropas;
    }


    Pais Angola = new Pais("Angola");
    Pais Africa = new Pais("Africa");
    Pais Madagascar = new Pais("Madagascar");

    //Fronteiras
    Angola.fronteiras =


}

//{
// new Pais = Angola Pais("Angola")
// new Pais = Brasil Pais("Brasil")
// new Pais = Portugal Pais("Portugal")
// ...

// Angola ([Brasil, Portugal])

// Brasil.fronteiras = {Chile, Argentina}
// new Pais = Portugal Pais("Portugal")
//}