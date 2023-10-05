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

    enum Poligono {
        quadrado,
        bola,
        triangulo
    }
    public static class Territorio {
        private List<Territorio> territorios;
        private String pais;
        private Poligono poligono;

        public Territorio(String pais, Poligono poligono) {
            this.pais = pais;
            this.poligono = poligono;
        }
        public void adicionarTerritorio(Territorio territorio) {
            this.territorios.add(territorio);
        }

        public String getPais() {
            return pais;
        }

        public Poligono getPoligono() {
            return poligono;
        }



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

