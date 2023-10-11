package model;

class Cartas {
    // cada jogador tera um objetivo sorteado, ao objetivo ser sorteado, ele sera removido da lista, e outro sera sorteado
    public static String[] objetivo = { // 14 objetivos
    "Destruir totalmente o jogador VERDE\nSe você é o VERDE, ou não há VERDE na partida, ou se esses exercitos já foram destruidos por outro jogador, você deve conquistar 24 terrítorios.",
    "Destruir totalmente o jogador AZUL\nSe você é o AZUL, ou não há AZUL na partida, ou se esses exercitos já foram destruidos por outro jogador, você deve conquistar 24 terrítorios.",
    "Destruir totalmente o jogador AMARELO\nSe você é o AMARELO, ou não há AMARELO na partida, ou se esses exercitos já foram destruidos por outro jogador, você deve conquistar 24 terrítorios.",
    "Destruir totalmente o jogador VERMELHO\nSe você é o VERMELHO, ou não há VERMELHO na partida, ou se esses exercitos já foram destruidos por outro jogador, você deve conquistar 24 terrítorios.",
    "Destruir totalmente o jogador PRETO\nSe você é o PRETO, ou não há PRETO na partida, ou se esses exercitos já foram destruidos por outro jogador, você deve conquistar 24 terrítorios.",
     "Destruir totalmente o jogador BRANCO\nSe você é o BRANCO, ou não há BRANCO na partida, ou se esses exercitos já foram destruidos por outro jogador, você deve conquistar 24 terrítorios.",
    "Conquistar na totalidade a America do Norte e a Africa",
    "Conquistar na totalidade a Asia e a Africa",
    "Conquistar na totalidade a America do Norte e a Oceania",
    "Conquistar na totalidade Asia e America do Sul",
    "Conquistar na totalidade a Europa, a America do Sul e mais um continente a sua escolha",
     "Conquistar na totalidade a Europa, a Oceania e mais um continente a sua escolha",
     "Conquistar 24 territórios a sua escolha",
     "Conquistar 18 territorios com pelo menos 2 exercitos em cada"
    };
    public Integer[] ListaCartasCoringa = {
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

        public Cartas.Territorio[] allTerritorios = {
                new Cartas.Territorio("África do Sul", Cartas.Poligono.triangulo, new String[]{"Angola", "Somália"}),
                new Cartas.Territorio("Angola", Cartas.Poligono.quadrado, new String[]{"África do Sul","Nigéria","Somália"}),
                new Cartas.Territorio("Argélia", Cartas.Poligono.bola, new String[]{"Egito","Espanha","França","Itália","Nigéria"}),
                new Cartas.Territorio("Egito", Cartas.Poligono.triangulo, new String[]{"Argélia","Jordânia","Nigéria","Romênia","Somália"}),
                new Cartas.Territorio("Nigéria", Cartas.Poligono.bola, new String[]{"Angola","Argélia","Brasil","Egito","Somália"}),
                new Cartas.Territorio("Somália", Cartas.Poligono.quadrado, new String[]{"África do Sul","Angola","Arábia Saudita","Egito","Nigéria"}),

                new Cartas.Territorio("Alasca", Cartas.Poligono.triangulo, new String[]{"Calgary","Siberia","Vancouver"}),
                new Cartas.Territorio("Calgary", Cartas.Poligono.bola, new String[]{"Alasca","Groenlandia","Vancouver"}),
                new Cartas.Territorio("California", Cartas.Poligono.quadrado, new String[]{"México","Texas","Vancouver"}),
                new Cartas.Territorio("Groenlândia", Cartas.Poligono.bola, new String[]{"Calgary","Québec","Reino Unido"}),
                new Cartas.Territorio("Nova York", Cartas.Poligono.quadrado, new String[]{"Québec","Texas"}),
                new Cartas.Territorio("México", Cartas.Poligono.quadrado, new String[]{"California","Texas","Venezuela"}),
                new Cartas.Territorio("Québec", Cartas.Poligono.bola, new String[]{"Groenlândia","Nova York","Texas","Vancouver"}),
                new Cartas.Territorio("Texas", Cartas.Poligono.triangulo, new String[]{"California","México","Nova York","Québec","Vancouver"}),
                new Cartas.Territorio("Vancouver", Cartas.Poligono.triangulo, new String[]{"Alasca","Calgary","California","Québec","Texas"}),

                new Cartas.Territorio("Arábia Saudita", Cartas.Poligono.bola, new String[]{"Iraque","Jordânia","Somália"}),
                new Cartas.Territorio("Bangladesh", Cartas.Poligono.bola, new String[]{"Coréia do Sul","Índia","Indonésia","Tailândia"}),
                new Cartas.Territorio("Cazaquistão", Cartas.Poligono.bola, new String[]{"China","Japão","Letônia","Mongólia","Sibéria","turquia"}),
                new Cartas.Territorio("China", Cartas.Poligono.quadrado, new String[]{"Cazaquistão","Coréia do Norte","Coréia do Sul","Índia","Mongólia","Paquistão","Turquia"}),
                new Cartas.Territorio("Coréia do Norte", Cartas.Poligono.quadrado, new String[]{"China","Coréia do sul", "Japão"}),
                new Cartas.Territorio("Coréia do Sul", Cartas.Poligono.triangulo, new String[]{"Bangladesh","China","Coréia do Norte","Índia","Tailândia"}),
                new Cartas.Territorio("Estônia", Cartas.Poligono.bola, new String[]{"Letônia","Russia","Suécia"}),
                new Cartas.Territorio("Índia", Cartas.Poligono.triangulo, new String[]{"Bangladesh","China","Coréia do Sul","Paquistão"}),
                new Cartas.Territorio("Irã", Cartas.Poligono.quadrado, new String[]{"Iraque","Paquistão","Síria"}),
                new Cartas.Territorio("Iraque", Cartas.Poligono.triangulo, new String[]{"Arábia Saudita","Irã","Jordânia","Síria"}),
                new Cartas.Territorio("Japão", Cartas.Poligono.bola, new String[]{"Cazaquistão","Coréia do Norte","Mongólia"}),
                new Cartas.Territorio("Jordânia", Cartas.Poligono.quadrado, new String[]{"Arábia Saudita","Egito","Iraque","Síria"}),
                new Cartas.Territorio("Letônia", Cartas.Poligono.quadrado, new String[]{"Cazaquistão","Estônia","Polônia","Rússia","Suécia","Turquia","Ucrânia"}),
                new Cartas.Territorio("Mongólia", Cartas.Poligono.triangulo, new String[]{"Cazaquistão","China","Japão"}),
                new Cartas.Territorio("Paquistão", Cartas.Poligono.bola, new String[]{}),
                new Cartas.Territorio("Russía", Cartas.Poligono.triangulo, new String[]{"Cazaquistão","Estônia","Letônia","Sibéria"}),
                new Cartas.Territorio("Sibéria", Cartas.Poligono.quadrado, new String[]{"Alasca","Cazaquistão","Rússia"}),
                new Cartas.Territorio("Síria", Cartas.Poligono.quadrado, new String[]{}),
                new Cartas.Territorio("Tailândia", Cartas.Poligono.triangulo, new String[]{"Bangladesh","Coréia do Sul"}),
                new Cartas.Territorio("Turquia", Cartas.Poligono.triangulo, new String[]{"Cazaquistão","China","Letônia","Paquistão","Síria","Ucrânia"}),

                new Cartas.Territorio("Argentina", Cartas.Poligono.quadrado, new String[]{"Brasil","Peru"}),
                new Cartas.Territorio("Brasil", Cartas.Poligono.bola, new String[]{"Argentina","Peru","Venezuela","Nigéria"}),
                new Cartas.Territorio("Peru", Cartas.Poligono.triangulo, new String[]{"Brasil","Argentina","Venezuela"}),
                new Cartas.Territorio("Venezuela", Cartas.Poligono.triangulo, new String[]{"Brasil","Peru"}),

                new Cartas.Territorio("Espanha", Poligono.bola, new String[]{"Argélia","França"}),
                new Cartas.Territorio("França", Poligono.triangulo, new String[]{"Argélia","Espanha","Itália","Suécia"}),
                new Cartas.Territorio("Itália", Poligono.quadrado, new String[]{"Argélia","França","Polônia","Romênia","Suécia"}),
                new Cartas.Territorio("Polônia", Poligono.triangulo, new String[]{"Itália","Letônia","Ucrânia"}),
                new Cartas.Territorio("Reino Unido", Poligono.bola, new String[]{"França","Groenlândia"}),
                new Cartas.Territorio("Romênia", Poligono.triangulo, new String[]{"Egito","Itália","Polônia","Ucrânia"}),
                new Cartas.Territorio("Suécia", Poligono.quadrado, new String[]{"Estônia","França","Itália","Letônia"}),
                new Cartas.Territorio("Ucrânia", Cartas.Poligono.bola, new String[]{"Letônia","Polônia","Romênia","Turquia"}),

                new Cartas.Territorio("Austrália", Poligono.triangulo, new String[]{"Indonésia","Nova Zelândia","Perth"}),
                new Cartas.Territorio("Indonésia", Poligono.triangulo, new String[]{"Austrália","Bangladesh","Índia","Nova Zelândia"}),
                new Cartas.Territorio("Nova Zelândia", Poligono.quadrado, new String[]{"Austrália","Indonésia"}),
                new Cartas.Territorio("Perth", Poligono.bola, new String[]{"Austrália"})
        };

        public Cartas.Territorio[] listaTerritoriosAfrica = {
                allTerritorios[0], allTerritorios[1], allTerritorios[2], allTerritorios[3], allTerritorios[4], allTerritorios[5]
        };

        public Cartas.Territorio[] ListaTerritoriosAmericaDoNorte = {
                allTerritorios[6], allTerritorios[7], allTerritorios[8], allTerritorios[9], allTerritorios[10], allTerritorios[11],
                allTerritorios[12],allTerritorios[13],allTerritorios[14]
        };

        public Cartas.Territorio[] ListaTerritoriosAsia = {
                allTerritorios[15], allTerritorios[16], allTerritorios[17], allTerritorios[18], allTerritorios[19], allTerritorios[20],
                allTerritorios[21], allTerritorios[22], allTerritorios[23], allTerritorios[24], allTerritorios[25], allTerritorios[26],
                allTerritorios[27], allTerritorios[28], allTerritorios[29], allTerritorios[30], allTerritorios[31],
                allTerritorios[32], allTerritorios[33], allTerritorios[34]
        };
        public Cartas.Territorio[] ListaTerritoriosAmericaDoSul = {
                allTerritorios[35], allTerritorios[36], allTerritorios[37], allTerritorios[38],
        };

        public Cartas.Territorio[] ListaTerritoriosEuropa = {
                allTerritorios[39], allTerritorios[40], allTerritorios[41], allTerritorios[42], allTerritorios[43], allTerritorios[44],
                allTerritorios[45], allTerritorios[46]
        };

        public Cartas.Territorio[] ListaTerritoriosOceania = {
                allTerritorios[47], allTerritorios[48], allTerritorios[49], allTerritorios[50]
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

