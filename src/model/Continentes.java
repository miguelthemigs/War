package model;

import static model.Cartas.Territorio.allTerritorios;

class Continentes {

    public static Cartas.Territorio[] ListaTerritoriosAfrica = {
            allTerritorios[0], allTerritorios[1], allTerritorios[2], allTerritorios[3], allTerritorios[4], allTerritorios[5]
    };
    public static Cartas.Territorio[] ListaTerritoriosAmericaDoNorte = {
            allTerritorios[6], allTerritorios[7], allTerritorios[8], allTerritorios[9], allTerritorios[10], allTerritorios[11],
            allTerritorios[12],allTerritorios[13],allTerritorios[14]
    };

    public static Cartas.Territorio[] ListaTerritoriosAsia = {
            allTerritorios[15], allTerritorios[16], allTerritorios[17], allTerritorios[18], allTerritorios[19], allTerritorios[20],
            allTerritorios[21], allTerritorios[22], allTerritorios[23], allTerritorios[24], allTerritorios[25], allTerritorios[26],
            allTerritorios[27], allTerritorios[28], allTerritorios[29], allTerritorios[30], allTerritorios[31],
            allTerritorios[32], allTerritorios[33], allTerritorios[34]
    };
    public static Cartas.Territorio[] ListaTerritoriosAmericaDoSul = {
            allTerritorios[35], allTerritorios[36], allTerritorios[37], allTerritorios[38],
    };

    public static Cartas.Territorio[] ListaTerritoriosEuropa = {
            allTerritorios[39], allTerritorios[40], allTerritorios[41], allTerritorios[42], allTerritorios[43], allTerritorios[44],
            allTerritorios[45], allTerritorios[46]
    };

    public static Cartas.Territorio[] ListaTerritoriosOceania = {
            allTerritorios[47], allTerritorios[48], allTerritorios[49], allTerritorios[50]
    };


}
/*

class myMain{
    public static void main(String[] args) {
        Continentes cont = new Continentes();
        System.out.println("Africa");
        for(Cartas.Territorio cart: ListaTerritoriosAfrica)
            System.out.println(cart.getPais());
    }


}
*/

