package model;

import java.util.List;

class Cartas {
    // cada jogador tera um objetivo sorteado, ao objetivo ser sorteado, ele sera removido da lista, e outro sera sorteado
    public static String[] objetivo = { // 14 objetivos
    "Destruir totalmente o jogador VERDE\nSe você é o VERDE, ou não há VERDE na partida, deve conquistar 24 terrítorios.",
    "Destruir totalmente o jogador AZUL\nSe você é o AZUL, ou não há AZUL na partida, deve conquistar 24 terrítorios.",
    "Destruir totalmente o jogador AMARELO\nSe você é o AMARELO, ou não há AMARELO na partida, deve conquistar 24 terrítorios.",
    "Destruir totalmente o jogadoR VERMELHO\nSe você é o VERMELHO, ou não há VERMELHO na partida, deve conquistar 24 terrítorios.",
    "Destruir totalmente o jogador PRETO\nSe você é o PRETO, ou não há PRETO na partida, deve conquistar 24 terrítorios.",
     "Destruir totalmente o jogador BRANCO\nSe você é o BRANCO, ou não há BRANCO na partida, deve conquistar 24 terrítorios.",
    "Conquistar por completo a ÁFRICA e a AMÉRICA DO NORTE",
    "Conquistar por completo a ÁSIA e a ÁFRICA",
    "Conquistar por completo a AMÉRICA DO NORTE  e a OCEANIA",
    "Conquistar por completo a ÁSIA e a AMÉRICA DO SUL",
    "Conquistar por completo a AMÉRICA DO SUL e EUROPA, e mais um continente de sua escolha",
     "Conquistar por completo a EUROPA e a OCEANIA, e mais um continente de sua escolha",
     "Conquistar 24 territórios de sua escolha",
     "Conquistar 18 territórios e ocupar cada um deles com pelo menos 2 exércitos"
    };
    private Integer[] ListaCartasCoringa = {
        100, 100
    };


    enum Poligono {
        quadrado,
        bola,
        triangulo
    }
    public static class Territorio {
        private String pais;
        private Poligono poligono;

        private String[] fronteira; // usar .contains()

        public Territorio(String pais, Poligono poligono, String[] fronteira) {
            this.pais = pais;
            this.poligono = poligono;
            this.fronteira = fronteira;
        }

        public String getPais() {
            return pais;
        }

        public Poligono getPoligono() {
            return poligono;
        }

        public Cartas.Territorio[] listaTerritoriosAfrica = {
                new Cartas.Territorio("África do Sul", Cartas.Poligono.triangulo, new String[]{"Angola", "Somália"}),
                new Cartas.Territorio("Angola", Cartas.Poligono.quadrado),
                new Cartas.Territorio("Argelia", Cartas.Poligono.bola),
                new Cartas.Territorio("Egito", Cartas.Poligono.triangulo),
                new Cartas.Territorio("Nigéria", Cartas.Poligono.bola),
                new Cartas.Territorio("Somália", Cartas.Poligono.quadrado)
        };

        public Cartas.Territorio[] ListaTerritoriosAmericaDoNorte = {
                new Cartas.Territorio("Alasca", Cartas.Poligono.triangulo),
                new Cartas.Territorio("Calgary", Cartas.Poligono.bola),
                new Cartas.Territorio("California", Cartas.Poligono.quadrado),
                new Cartas.Territorio("Groenlândia", Cartas.Poligono.bola),
                new Cartas.Territorio("Nova York", Cartas.Poligono.quadrado),
                new Cartas.Territorio("México", Cartas.Poligono.quadrado),
                new Cartas.Territorio("Québec", Cartas.Poligono.bola),
                new Cartas.Territorio("Texas", Cartas.Poligono.triangulo),
                new Cartas.Territorio("Vancouver", Cartas.Poligono.triangulo)
        };

        public Cartas.Territorio[] ListaTerritoriosAsia = {
                new Cartas.Territorio("Arábia Saudita", Cartas.Poligono.bola),
                new Cartas.Territorio("Bangladesh", Cartas.Poligono.bola),
                new Cartas.Territorio("Cazaquistão", Cartas.Poligono.bola),
                new Cartas.Territorio("China", Cartas.Poligono.quadrado),
                new Cartas.Territorio("Coréia do Norte", Cartas.Poligono.quadrado),
                new Cartas.Territorio("Coréia do Sul", Cartas.Poligono.triangulo),
                new Cartas.Territorio("Estônia", Cartas.Poligono.bola),
                new Cartas.Territorio("Índia", Cartas.Poligono.triangulo),
                new Cartas.Territorio("Irã", Cartas.Poligono.quadrado),
                new Cartas.Territorio("Iraque", Cartas.Poligono.triangulo),
                new Cartas.Territorio("Japão", Cartas.Poligono.bola),
                new Cartas.Territorio("Jordânia", Cartas.Poligono.quadrado),
                new Cartas.Territorio("Letônia", Cartas.Poligono.quadrado),
                new Cartas.Territorio("Mongólia", Cartas.Poligono.triangulo),
                new Cartas.Territorio("Paquistão", Cartas.Poligono.bola),
                new Cartas.Territorio("Russía", Cartas.Poligono.triangulo),
                new Cartas.Territorio("Sibéria", Cartas.Poligono.quadrado),
                new Cartas.Territorio("Síria", Cartas.Poligono.quadrado),
                new Cartas.Territorio("Tailândia", Cartas.Poligono.triangulo),
                new Cartas.Territorio("Turquia", Cartas.Poligono.triangulo)
        };
        public Cartas.Territorio[] ListaTerritoriosAmericaDoSul = {
                new Cartas.Territorio("Argentina", Cartas.Poligono.quadrado),
                new Cartas.Territorio("Brasil", Cartas.Poligono.bola),
                new Cartas.Territorio("Peru", Cartas.Poligono.triangulo),
                new Cartas.Territorio("Venezuela", Cartas.Poligono.triangulo),
        };

        public Cartas.Territorio[] ListaTerritoriosEuropa = {
                new Cartas.Territorio("Espanha", Poligono.bola),
                new Cartas.Territorio("França", Poligono.triangulo),
                new Cartas.Territorio("Itália", Poligono.quadrado),
                new Cartas.Territorio("Polônia", Poligono.triangulo),
                new Cartas.Territorio("Reino Unido", Poligono.bola),
                new Cartas.Territorio("Romênia", Poligono.triangulo),
                new Cartas.Territorio("Suécia", Poligono.quadrado),
                new Cartas.Territorio("Ucrânia", Cartas.Poligono.bola),

        };

        public Cartas.Territorio[] ListaTerritoriosOceania = {
                new Cartas.Territorio("Austrália", Poligono.triangulo),
                new Cartas.Territorio("Indonésia", Poligono.triangulo),
                new Cartas.Territorio("Nova Zelândia", Poligono.quadrado),
                new Cartas.Territorio("Perth", Poligono.bola),
        };

}
}

/*
 class myMain{
    public static void main(String[] args) {
        // nomear os territorios em passar para uma estrutura que os armazenam (nao sera nessa main, sera no continente, que estara no tabuleiro)
        Cartas.Territorio territorio = new Cartas.Territorio("Brasil", Cartas.Poligono.quadrado);
        System.out.println(territorio.getPoligono());
    }
}
*/

