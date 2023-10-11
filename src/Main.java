import model.Pais;

public class Main {
    public static void main(String[] args) {

        System.out.println("""
                                       BEM VINDO AO
                 .----------------. .----------------. .----------------.\s
                | .--------------. | .--------------. | .--------------. |
                | | _____  _____ | | |      __      | | |  _______     | |
                | ||_   _||_   _|| | |     /  \\     | | | |_   __ \\    | |
                | |  | | /\\ | |  | | |    / /\\ \\    | | |   | |__) |   | |
                | |  | |/  \\| |  | | |   / ____ \\   | | |   |  __ /    | |
                | |  |   /\\   |  | | | _/ /    \\ \\_ | | |  _| |  \\ \\_  | |
                | |  |__/  \\__|  | | ||____|  |____|| | | |____| |___| | |
                | |              | | |              | | |              | |
                | '--------------' | '--------------' | '--------------' |
                 '----------------' '----------------' '----------------'\s""");

        // Inicializando
        Pais Argelia = new Pais("Argélia");
        Pais Nigeria = new Pais("Nigéria");
        Pais Angola = new Pais("Angola");
        Pais Egito = new Pais("Egito");
        Pais Somalia = new Pais("Somália");
        Pais AfricaSul = new Pais("África do Sul");

        Pais Mexico = new Pais("México");
        Pais California = new Pais("Califórnia");
        Pais Texas = new Pais("Texas");
        Pais Vancouver = new Pais("Vancouver");
        Pais NovaYork = new Pais("Nova York");
        Pais Quebec = new Pais("Quebec");
        Pais Alasca = new Pais("Alasca");
        Pais Calgary = new Pais("Calgary");
        Pais Groelandia = new Pais("Groelândia");

        Pais Letonia = new Pais("Letônia");
        Pais Estonia = new Pais("Estônia");
        Pais Turquia = new Pais("Turquia");
        Pais Siberia = new Pais("Sibéria");
        Pais Siria = new Pais("Síria");
        Pais Tailandia = new Pais("Tailândia");
        Pais Russia = new Pais("Rússia");
        Pais Cazaquistao = new Pais("Cazaquistão");
        Pais China = new Pais("China");
        Pais Mongolia = new Pais("Mongólia");
        Pais ArabiaSaudita = new Pais("Arábia Saudita");
        Pais Bangladesh = new Pais("Bangladesh");
        Pais CoreiadoNorte = new Pais("Coreia do Norte");
        Pais CoreiadoSul = new Pais("Coreia do Sul");
        Pais Paquistao = new Pais("Paquistão");
        Pais India = new Pais("Índia");
        Pais Ira = new Pais("Irã");
        Pais Iraque = new Pais("Iraque");
        Pais Japao = new Pais("Japão");
        Pais Jordania = new Pais("Jordânia");

        Pais Brasil = new Pais("Brasil");
        Pais Argentina = new Pais("Argentina");
        Pais Peru = new Pais("Peru");
        Pais Venezuela = new Pais("Venezuela");

        Pais ReinoUnido = new Pais("Reino Unido");
        Pais Franca = new Pais("França");
        Pais Espanha = new Pais("Espanha");
        Pais Italia = new Pais("Itália");
        Pais Suecia = new Pais("Suécia");
        Pais Polonia = new Pais("Polônia");
        Pais Romenia = new Pais("Romênia");
        Pais Ucrania = new Pais("Ucrânia");

        Pais Australia = new Pais("Austrália");
        Pais Indonesia = new Pais("Indonésia");
        Pais NovaZelandia = new Pais("Nova Zelândia");
        Pais Perth = new Pais("Perth");


        //Fronteiras
        //Angola.setFronteiras(new Pais[]{Africa, Madagascar});

        //Continentes
        //Pais[] America = {Africa, Madagascar};

    }
}