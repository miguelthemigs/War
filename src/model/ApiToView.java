package model;

import java.util.ArrayList;

public class ApiToView {
    ApiAcess inst = new ApiAcess();
    ArrayList<Integer> corTropas = new ArrayList<>();
    Integer cor;
    Integer tropas;
    private final ArrayList<Pais> listaPaises = inst.geraListaSorteioTerritorios(); // vai ter todos os paises aqui

    public ArrayList<Integer> iterarPaises(){
        for (Pais pais: listaPaises){
            for(Jogador jogador: inst.jogadores){
                if(jogador.getTerritoriosPossuidos().contains(pais)){
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

}
