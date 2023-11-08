package controller;


import model.ApiToView;

import java.util.ArrayList;

import static model.ApiAcess.jogadores;

public class Goal {
    private final ArrayList<String> objetivos = new ArrayList<>();
    public ArrayList<String> retornaObjetivos(){
        for(Object jogador: jogadores){
            objetivos.add("Objetivo jogador ''"+ ApiToView.retornaCor(jogador) + "'':<br> &nbsp; - " +  ApiToView.retornaObjetivo(jogador));
        }
        return objetivos;
    }
}
