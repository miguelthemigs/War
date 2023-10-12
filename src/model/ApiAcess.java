package model;

import java.util.ArrayList;
import java.util.Random;

public class ApiAcess {
    String[] objetivos = Cartas.objetivo; // devo tirar um onjetivo da lista a cada sorteio
   /* public void sorteiaObjetivo(ArrayList<Jogador> jogadores){
        Random rand = new Random();
        ArrayList<Integer> indicesAntigos = new ArrayList<>();
        int i = 0;

        while(i < jogadores.size()){
            int indiceAleatorio = rand.nextInt(jogadores.size());
            if(!indicesAntigos.contains(indiceAleatorio)){
                jogadores.get(i).setObjetivo(objetivos[indiceAleatorio]);
                indicesAntigos.add(indiceAleatorio);
                i++;

            }
        }
*/
   public void sorteiaObjetivo(ArrayList<Jogador> jogadores){
       Random rand = new Random();
       ArrayList<Integer> indicesAntigos = new ArrayList<>();

       while(indicesAntigos.size() < jogadores.size()){ // Enquanto não sorteou para todos os jogadores
           int indiceAleatorio = rand.nextInt(objetivos.length);
           if(!indicesAntigos.contains(indiceAleatorio)){
               int indiceJogador = indicesAntigos.size(); // Usa o tamanho da lista como índice
               jogadores.get(indiceJogador).setObjetivo(objetivos[indiceAleatorio]);
               indicesAntigos.add(indiceAleatorio);
           }
       }
   }

}
