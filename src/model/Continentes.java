package model;

import java.util.Map;

class Continentes {

    private enum Cont{
        ASIA, AMERICA_DO_SUL, AMERICA_DO_NORTE, OCEANIA, EUROPA, AFRICA
    }

    private Map<Cont, Cartas.Territorio> MapasContinente;

    private void addTerritorioEmContinente(Cont continente, Cartas.Territorio territorio){
        MapasContinente.put(continente, territorio);
    }




}


