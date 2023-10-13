package model;

import java.util.*;

public class ApiAcess {
    String[] objetivos = Cartas.objetivo; // devo tirar um onjetivo da lista a cada sorteio

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

   private ArrayList<Pais> geraListaSorteioTerritorios(){
       ArrayList<Pais> listaTodosPaises = new ArrayList<>();
       listaTodosPaises.addAll(Arrays.asList(Tabuleiro.Africa));
       listaTodosPaises.addAll(Arrays.asList(Tabuleiro.Asia));
       listaTodosPaises.addAll(Arrays.asList(Tabuleiro.AmericaNorte));
       listaTodosPaises.addAll(Arrays.asList(Tabuleiro.AmericaSul));
       listaTodosPaises.addAll(Arrays.asList(Tabuleiro.Europa));
       listaTodosPaises.addAll(Arrays.asList(Tabuleiro.Oceania));
       return listaTodosPaises;


   }
   public void sorteiaTerritorios(ArrayList<Jogador> jogadores){
       ArrayList<Pais> listaTodosPaises;
       listaTodosPaises = geraListaSorteioTerritorios();
       Collections.shuffle(listaTodosPaises);
       while(listaTodosPaises.size() != 0){
           for(Jogador jogador: jogadores){
               if(listaTodosPaises.size() >= 1){
                   jogador.addTerritoriosPossuidos(listaTodosPaises.get(0));
                   listaTodosPaises.get(0).addTropas(1); // eu add 1 exercito nesse pais
                   listaTodosPaises.remove(listaTodosPaises.get(0));

               }
           }
       }

   }
   public void imprimeListaTerritorios(ArrayList<Pais> territorios){
       for(Pais pais: territorios){
           System.out.println(pais.getNome());
       }
   }

}
/*
class myMain{
    public static void main(String[] args) {
        ApiAcess api = new ApiAcess();
        ArrayList<Pais> paises = new ArrayList<>();
        paises = api.geraListaSorteioTerritorios();
        for(Pais pais: paises){
            System.out.println(pais.getNome());
        }
    }
}
*/
