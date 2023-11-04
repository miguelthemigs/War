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


    enum Poligono {
        quadrado,
        bola,
        triangulo,

        coringa
    }
    public static class Territorio {
        private final String pais;
        private final Poligono poligono;

        private final String[] fronteira; // usar .contains()

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

        public String[] getFronteira() {return fronteira;}


        public static final Cartas.Territorio[] allTerritorios = {
                new Cartas.Territorio("ÁfricadoSul", Cartas.Poligono.triangulo, new String[]{"Angola", "Somália"}),
                new Cartas.Territorio("Angola", Cartas.Poligono.quadrado, new String[]{"ÁfricadoSul","Nigéria","Somália"}),
                new Cartas.Territorio("Argélia", Cartas.Poligono.bola, new String[]{"Egito","Espanha","França","Itália","Nigéria"}),
                new Cartas.Territorio("Egito", Cartas.Poligono.triangulo, new String[]{"Argélia","Jordânia","Nigéria","Romênia","Somália"}),
                new Cartas.Territorio("Nigéria", Cartas.Poligono.bola, new String[]{"Angola","Argélia","Brasil","Egito","Somália"}),
                new Cartas.Territorio("Somália", Cartas.Poligono.quadrado, new String[]{"ÁfricadoSul","Angola","Arábia Saudita","Egito","Nigéria"}),

                new Cartas.Territorio("Alasca", Cartas.Poligono.triangulo, new String[]{"Calgary","Siberia","Vancouver"}),
                new Cartas.Territorio("Calgary", Cartas.Poligono.bola, new String[]{"Alasca","Groenlandia","Vancouver"}),
                new Cartas.Territorio("California", Cartas.Poligono.quadrado, new String[]{"México","Texas","Vancouver"}),
                new Cartas.Territorio("Groenlândia", Cartas.Poligono.bola, new String[]{"Calgary","Québec","ReinoUnido"}),
                new Cartas.Territorio("NovaYork", Cartas.Poligono.quadrado, new String[]{"Québec","Texas"}),
                new Cartas.Territorio("México", Cartas.Poligono.quadrado, new String[]{"California","Texas","Venezuela"}),
                new Cartas.Territorio("Québec", Cartas.Poligono.bola, new String[]{"Groenlândia","NovaYork","Texas","Vancouver"}),
                new Cartas.Territorio("Texas", Cartas.Poligono.triangulo, new String[]{"California","México","Nova York","Québec","Vancouver"}),
                new Cartas.Territorio("Vancouver", Cartas.Poligono.triangulo, new String[]{"Alasca","Calgary","California","Québec","Texas"}),

                new Cartas.Territorio("ArábiaSaudita", Cartas.Poligono.bola, new String[]{"Iraque","Jordânia","Somália"}),
                new Cartas.Territorio("Bangladesh", Cartas.Poligono.bola, new String[]{"CoréiadoSul","Índia","Indonésia","Tailândia"}),
                new Cartas.Territorio("Cazaquistão", Cartas.Poligono.bola, new String[]{"China","Japão","Letônia","Mongólia","Sibéria","Turquia"}),
                new Cartas.Territorio("China", Cartas.Poligono.quadrado, new String[]{"Cazaquistão","CoréiadoNorte","CoréiadoSul","Índia","Mongólia","Paquistão","Turquia"}),
                new Cartas.Territorio("CoréiadoNorte", Cartas.Poligono.quadrado, new String[]{"China","Coréiadosul", "Japão"}),
                new Cartas.Territorio("CoréiadoSul", Cartas.Poligono.triangulo, new String[]{"Bangladesh","China","CoréiadoNorte","Índia","Tailândia"}),
                new Cartas.Territorio("Estônia", Cartas.Poligono.bola, new String[]{"Letônia","Russia","Suécia"}),
                new Cartas.Territorio("Índia", Cartas.Poligono.triangulo, new String[]{"Bangladesh","China","CoréiadoSul","Paquistão"}),
                new Cartas.Territorio("Irã", Cartas.Poligono.quadrado, new String[]{"Iraque","Paquistão","Síria"}),
                new Cartas.Territorio("Iraque", Cartas.Poligono.triangulo, new String[]{"ArábiaSaudita","Irã","Jordânia","Síria"}),
                new Cartas.Territorio("Japão", Cartas.Poligono.bola, new String[]{"Cazaquistão","CoréiadoNorte","Mongólia"}),
                new Cartas.Territorio("Jordânia", Cartas.Poligono.quadrado, new String[]{"ArábiaSaudita","Egito","Iraque","Síria"}),
                new Cartas.Territorio("Letônia", Cartas.Poligono.quadrado, new String[]{"Cazaquistão","Estônia","Polônia","Rússia","Suécia","Turquia","Ucrânia"}),
                new Cartas.Territorio("Mongólia", Cartas.Poligono.triangulo, new String[]{"Cazaquistão","China","Japão"}),
                new Cartas.Territorio("Paquistão", Cartas.Poligono.bola, new String[]{}),
                new Cartas.Territorio("Russía", Cartas.Poligono.triangulo, new String[]{"Cazaquistão","Estônia","Letônia","Sibéria"}),
                new Cartas.Territorio("Sibéria", Cartas.Poligono.quadrado, new String[]{"Alasca","Cazaquistão","Rússia"}),
                new Cartas.Territorio("Síria", Cartas.Poligono.quadrado, new String[]{}),
                new Cartas.Territorio("Tailândia", Cartas.Poligono.triangulo, new String[]{"Bangladesh","CoréiadoSul"}),
                new Cartas.Territorio("Turquia", Cartas.Poligono.triangulo, new String[]{"Cazaquistão","China","Letônia","Paquistão","Síria","Ucrânia"}),

                new Cartas.Territorio("Argentina", Cartas.Poligono.quadrado, new String[]{"Brasil","Peru"}),
                new Cartas.Territorio("Brasil", Cartas.Poligono.bola, new String[]{"Argentina","Peru","Venezuela","Nigéria"}),
                new Cartas.Territorio("Peru", Cartas.Poligono.triangulo, new String[]{"Brasil","Argentina","Venezuela"}),
                new Cartas.Territorio("Venezuela", Cartas.Poligono.triangulo, new String[]{"Brasil","Peru"}),

                new Cartas.Territorio("Espanha", Poligono.bola, new String[]{"Argélia","França"}),
                new Cartas.Territorio("França", Poligono.triangulo, new String[]{"Argélia","Espanha","Itália","Suécia"}),
                new Cartas.Territorio("Itália", Poligono.quadrado, new String[]{"Argélia","França","Polônia","Romênia","Suécia"}),
                new Cartas.Territorio("Polônia", Poligono.triangulo, new String[]{"Itália","Letônia","Ucrânia"}),
                new Cartas.Territorio("ReinoUnido", Poligono.bola, new String[]{"França","Groenlândia"}),
                new Cartas.Territorio("Romênia", Poligono.triangulo, new String[]{"Egito","Itália","Polônia","Ucrânia"}),
                new Cartas.Territorio("Suécia", Poligono.quadrado, new String[]{"Estônia","França","Itália","Letônia"}),
                new Cartas.Territorio("Ucrânia", Cartas.Poligono.bola, new String[]{"Letônia","Polônia","Romênia","Turquia"}),

                new Cartas.Territorio("Austrália", Poligono.triangulo, new String[]{"Indonésia","Nova Zelândia","Perth"}),
                new Cartas.Territorio("Indonésia", Poligono.triangulo, new String[]{"Austrália","Bangladesh","Índia","NovaZelândia"}),
                new Cartas.Territorio("NovaZelândia", Poligono.quadrado, new String[]{"Austrália","Indonésia"}),
                new Cartas.Territorio("Perth", Poligono.bola, new String[]{"Austrália"}),

                new Cartas.Territorio("Coringa1", Poligono.coringa, new String[]{}),
                new Cartas.Territorio("Coringa2", Poligono.coringa, new String[]{}),
        };
}
}



