package model;

import java.util.ArrayList;

class Cartas {
    // cada jogador tera um objetivo sorteado, ao objetivo ser sorteado, ele sera removido da lista, e outrp sera sorteado
    public static String[] objetivo = {"Destruir totalmente o jogador VERDE\nSe você é o VERDE, ou não há VERDE na partida, deve conquistar 24 terrítorios.",
            "Destruir totalmente o jogador AZUL\nSe você é o AZUL, ou não há AZUL na partida, deve conquistar 24 terrítorios.",
    "Destruir totalmente o jogador AMARELO\nSe você é o AMARELO, ou não há AMARELO na partida, deve conquistar 24 terrítorios.",
    "Destruir totalmente o jogador ROXO\nSe você é o ROXO, ou não há ROXO na partida, deve conquistar 24 terrítorios.",
    "Destruir totalmente o jogador LARANJA\nSe você é o LARANJA, ou não há LARANJA na partida, deve conquistar 24 terrítorios.",
    "Conquistar por completo a ÁFRICA e a AMÉRICA DO NORTE",
    "Conquistar por completo a ÁSIA e a ÁFRICA",
    "Conquistar por completo a AMÉRICA DO NORTE  e a OCEANIA",
    "Conquistar por completo a ÁSIA e a AMÉRICA DO SUL",
            "Conquistar por completo a AMÉRICA DO SUL e EUROPA, e mais um continente qualquer",
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
        private String pais;
        private Poligono poligono;

        public Territorio(String pais, Poligono poligono) {
            this.pais = pais;
            this.poligono = poligono;
        }
        public Territorio(){
        }

        public String getPais() {
            return pais;
        }

        public void setPais(String pais) {
            this.pais = pais;
        }

        public Poligono getPoligono() {
            return poligono;
        }

        public void setPoligono(Poligono poligono) {
            this.poligono = poligono;
        }
    }
}

 class myMain{
    public static void main(String[] args) {
        // nomear os territorios em passar para uma estrutura que os armazenam (nao sera nessa main)
        Cartas.Territorio territorio = new Cartas.Territorio("Brazil", Cartas.Poligono.quadrado);
        System.out.println(territorio.getPoligono());
    }
}
