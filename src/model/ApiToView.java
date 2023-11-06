package model;

import java.util.ArrayList;

public class ApiToView {
    static ApiAcess inst = new ApiAcess();
    ArrayList<Integer> corTropas = new ArrayList<>();
    Integer cor;
    Integer tropas;
    private static final ArrayList<Pais> listaPaises = inst.geraListaSorteioTerritorios(); // vai ter todos os paises aqui

    public ArrayList<Integer> iterarPaises(){
        for (Pais pais: listaPaises){
            cor = -1;
            for(Jogador jogador: ApiAcess.jogadores){

                if(jogador.getTerritoriosPossuidos().contains(pais)){
                    //System.out.println("Jogador" +jogador.getCor()+ "tem pais"+ pais  );
                    if(jogador.getCor().equals(Jogador.Cor.vermelho))
                        cor = 0;
                    else if(jogador.getCor().equals(Jogador.Cor.azul))
                        cor = 1;
                    else if(jogador.getCor().equals(Jogador.Cor.preto))
                        cor = 2;
                    else if(jogador.getCor().equals(Jogador.Cor.branco))
                        cor = 3;
                    else if(jogador.getCor().equals(Jogador.Cor.verde))
                        cor = 4;
                    else if(jogador.getCor().equals(Jogador.Cor.amarelo))
                        cor = 5;
                }
            }
            tropas = pais.getTropas();
            corTropas.add(cor*100 + tropas);
        }
        return corTropas;
    }
    public static Jogador.Cor retornaCor(Object jog){
        return ((Jogador) jog).getCor();
    }

    public static int retornaTropas(Object jog){
        return ((Jogador) jog).getTropasParaAdicionar();
    }

    public static void setarTropas(String territorio, int tropas){
        for(Pais pais: listaPaises){
            if(territorio.equals(pais.getNome())){
                pais.addTropas(tropas);
            }
        }
    }

    public static ArrayList<String> retornaTerritorios(Object jog){
        ArrayList<Pais> lista_territorios = ((Jogador) jog).getTerritoriosPossuidos();
        ArrayList<String> new_lista = new ArrayList<>();
        for(Pais pais: lista_territorios){
            new_lista.add(pais.getNome());
        }
        return new_lista;
    }
}
