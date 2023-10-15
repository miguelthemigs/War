package model;

import java.util.*;

public class ApiAcess {
    String[] objetivos = Cartas.objetivo; // devo tirar um onjetivo da lista a cada sorteio
    ArrayList<Jogador> jogadores = new ArrayList<>();

   public void sorteiaObjetivo(){
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
       for (int i = 0; i < jogadores.size(); i++){
           System.out.println("\nObjetivo jogador "+ (i+1) + ": "+ jogadores.get(i).getObjetivo());
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
   public void sorteiaTerritorios(){
       ArrayList<Pais> listaTodosPaises;
       listaTodosPaises = geraListaSorteioTerritorios();
       Collections.shuffle(listaTodosPaises);
       while(listaTodosPaises.size() != 0){
           for(Jogador jogador: jogadores){
               if(listaTodosPaises.size() >= 1){
                   jogador.addTerritoriosPossuidos(listaTodosPaises.get(0));
                   listaTodosPaises.get(0).addTropas(1); // eu add 1 exercito nesse pais
                   listaTodosPaises.get(0).setDono(jogador);
                   listaTodosPaises.remove(listaTodosPaises.get(0));

               }
           }
       }
       for (int i = 0; i < jogadores.size(); i++){
           System.out.println("\nTerritorios jogador "+ (i+1));
           imprimeListaTerritorios(jogadores.get(i).getTerritoriosPossuidos());
           System.out.println("Numero de territorios: "+jogadores.get(i).getNumTerritorios());
       }

   }

   public void imprimeListaTerritorios(ArrayList<Pais> territorios){
       for(Pais pais: territorios){
           System.out.println(pais.getNome());
       }
   }

   public void gerarJogadores(ArrayList<String> coresEscolhidas){
       for (String cor : coresEscolhidas) {
           jogadores.add(new Jogador(Jogador.Cor.valueOf(cor)));
       }

       // Agora você tem uma lista de jogadores com as cores escolhidas
       for (int i = 0; i < jogadores.size(); i++) {
           System.out.println("Jogador " + (i+1) + " escolheu a cor: " + jogadores.get(i).getCor());
       }
       System.out.println("Cores escolhidas: " + coresEscolhidas);
       System.out.println("--------------------------------------------------------");
   }

   public void checaContinentesJogador(){
       for (int i = 0; i < jogadores.size(); i++) {
          // System.out.printf("Jogador (%d):\n", i + 1);
           // Chama o método checaContinentes para o jogador atual
           jogadores.get(i).checaContinentes();
           System.out.println("Jogador "+ (i+1) + " tem os seguintes continentes: " + jogadores.get(i).getContinentesPossuidos());
           //System.out.println("Fim\n");
       }
   }

   public void checarTropasGanhar(){
       for (Jogador jogador : jogadores) {
           int tropasGanhar;
           tropasGanhar = jogador.getNumTerritorios() / 2;
           tropasGanhar += checarTropasAMais(jogador);
           if(tropasGanhar < 3)
               tropasGanhar = 3;
           jogador.setTropasParaAdicionar(tropasGanhar);
       }
   }
   // fazer depois funcao de checar troca de cartas. Teremos de add mais tropas
   private int checarTropasAMais(Jogador jogador){
       ArrayList<String> cont = jogador.checaContinentes();
       int tropas = 0;
       if (cont.contains("Africa")){
           tropas+=3;
       }
       if (cont.contains("Europa")){
           tropas+=5;
       }
       if (cont.contains("Asia")){
           tropas+=7;
       }
       if (cont.contains("Oceania")){
           tropas+=2;
       }
       if (cont.contains("AmericaSul")){
           tropas+=2;
       }
       if (cont.contains("AmericaNorte")){
           tropas+=5;
       }
       return tropas;
   }

   public void imprimeTropasARecber(){
       for (Jogador jogador : jogadores) {
           System.out.println("Tropas a receber jogador " + (jogador.getCor()) + ": " + jogador.getTropasParaAdicionar());
       }
   }

    // Método para imprimir informações sobre os territórios e tropas
    private void imprimirTerritoriosETropas(Jogador jogador) {
        System.out.println("Territórios e Tropas:\n");

        for (Pais territorio : jogador.getTerritoriosPossuidos()) {
            System.out.println("Território: " + territorio.getNome());
            System.out.println("Tropas: " + territorio.getTropas());
            System.out.println();
        }
    }
    public void posicionamentoExercitos() {
       for (Jogador jogador : jogadores) {
           System.out.println("\nJogador: " + jogador.getCor());
           imprimirTerritoriosETropas(jogador);

           while (jogador.getTropasParaAdicionar() > 0) {
               System.out.println("Tropas disponíveis: "+ jogador.getTropasParaAdicionar());
               // Aqui você pode pedir ao jogador quantas tropas ele quer adicionar
               // e em qual território
               // Você pode usar Scanner para isso

               // Exemplo:
               Scanner scanner = new Scanner(System.in);
               System.out.println("Em qual território você quer adicionar as tropas?");
               String territorioNome = scanner.next();

               Pais territorio = null;
               for (Pais pais : jogador.getTerritoriosPossuidos()) {
                   if (pais.getNome().equals(territorioNome)) {
                           territorio = pais;break;
                   }
               }

               if (territorio == null) {
                   System.out.println("Território inválido.");
                   continue;
               }

               System.out.println("Quantas tropas você quer adicionar? (Digite 0 para sair)");
               int quantidade = scanner.nextInt();
               if (quantidade > jogador.getTropasParaAdicionar()){
                   System.out.println("Mais tropas do que você tem. Tente novamente");
                   continue;
               }

               if (quantidade == 0) {
                   break;
               }
               territorio.addTropas(quantidade);
               imprimirTerritoriosETropas(jogador);
               jogador.diminuiTropasParaAdicionar(quantidade);

           }
       }
   }
   public void trocaCartasPoligono(){
       Scanner scanner = new Scanner(System.in);

       System.out.println("Deseja trocar cartas? (s/n): ");
       String resposta = scanner.nextLine();

       if (!resposta.equalsIgnoreCase("s")) {
           return;
       }

       System.out.println("Escolha três formas para trocar:");

       for (int i = 0; i < jogadores.size(); i++) {
           System.out.println("\nJogador: "+ jogadores.get(i).getCor());
           for(Cartas.Territorio carta: jogadores.get(i).getPoligonosPossuidos()){
               System.out.println("Forma: " + carta.getPoligono()+ "\nPais: "+ carta.getPais()+"\n");
           }

           System.out.println("Digite o nome do primeiro país:");
           String pais1 = scanner.nextLine();

           System.out.println("Digite o nome do segundo país:");
           String pais2 = scanner.nextLine();

           System.out.println("Digite o nome do terceiro país:");
           String pais3 = scanner.nextLine();

           Cartas.Territorio pais1Selecionada = null;
           Cartas.Territorio pais2Selecionada = null;
           Cartas.Territorio pais3Selecionada = null;

           for (Cartas.Territorio territorio : jogadores.get(i).getPoligonosPossuidos()) {
               if (territorio.getPais().equals(pais1)) {
                   pais1Selecionada = territorio;
               }
               if (territorio.getPais().equals(pais2)) {
                   pais2Selecionada = territorio;
               }
               if (territorio.getPais().equals(pais3)) {
                   pais3Selecionada = territorio;
               }
               if (pais1Selecionada != null && pais2Selecionada != null && pais3Selecionada != null) {
                   break;
               }
           }

           if (pais1Selecionada != null && pais2Selecionada != null && pais3Selecionada != null) {
               // Agora você pode verificar se os três países selecionados são iguais ou diferentes
               if (pais1Selecionada.equals(pais2Selecionada) && pais2Selecionada.equals(pais3Selecionada)) {
                   System.out.println("Troca bem-sucedida! Adicionando tropas ao prêmio...");
                   comparaPremios(jogadores.get(i));
                   jogadores.get(i).removePoligonosPossuidos(pais1Selecionada);
                   jogadores.get(i).removePoligonosPossuidos(pais2Selecionada);
                   jogadores.get(i).removePoligonosPossuidos(pais3Selecionada);
                   // adicionar novamente ao baralho apos criar distribuicao de cartas

               } else if (!pais1Selecionada.equals(pais2Selecionada) && !pais2Selecionada.equals(pais3Selecionada) && !pais1Selecionada.equals(pais3Selecionada)) {
                   System.out.println("Troca bem-sucedida! Adicionando tropas ao prêmio...");
                   comparaPremios(jogadores.get(i));
                   jogadores.get(i).removePoligonosPossuidos(pais1Selecionada);
                   jogadores.get(i).removePoligonosPossuidos(pais2Selecionada);
                   jogadores.get(i).removePoligonosPossuidos(pais3Selecionada);
                   // adicionar novamente ao baralho apos criar distribuicao de cartas

               } else {
                   System.out.println("Combinação inválida de formas.");
               }
           } else {
               System.out.println("Um ou mais países não foram encontrados na lista de polígonos.");
           }
     }
   }
   private void comparaPremios(Jogador jogador){
       if(jogador.getPremio() == 0){
           jogador.adicionaTropasParaAdicionar(4);
       }
       else if(jogador.getPremio() == 1){
           jogador.adicionaTropasParaAdicionar(6);
       }
       else if(jogador.getPremio() == 2){
           jogador.adicionaTropasParaAdicionar(8);
       }
       else if(jogador.getPremio() == 3){
           jogador.adicionaTropasParaAdicionar(10);
       }
       else if(jogador.getPremio() == 4){
           jogador.adicionaTropasParaAdicionar(12);
       }
       else if(jogador.getPremio() == 5){
           jogador.adicionaTropasParaAdicionar(15);
       }
       else if(jogador.getPremio() == 6){
           jogador.adicionaTropasParaAdicionar(20);
       }
       else {
           jogador.adicionaTropasParaAdicionar(25);
       }
       jogador.addPremio();
   }
}



class myMain{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pergunte o número de jogadores

        ArrayList<String> coresEscolhidas = new ArrayList<>();
        String resposta;
        ApiAcess api = new ApiAcess();

        System.out.println("Cores disponíveis: vermelho, verde, azul, amarelo, preto, branco");
        do {
            System.out.print("Digite a cor que você quer ser: ");
            String corEscolhida = scanner.nextLine();

            coresEscolhidas.add(corEscolhida);

            System.out.print("Quer adicionar mais cores? (S/N): ");
            resposta = scanner.nextLine();
        } while (resposta.equalsIgnoreCase("S"));

        api.gerarJogadores(coresEscolhidas);

        // Aqui realizamos e colocamos cada jogador para ter seu objetivo
        api.sorteiaObjetivo();

        // Agora, iremos gerar a lista com todos os territorios, e sortear os territorios e colocar 1 exercito em cada do seu respectivo jogador
        api.sorteiaTerritorios();

        System.out.println("------- Checando se os jogadores possuem algum continente --------");
        // Loop para percorrer todos os jogadores e identificar se eles possuem algum continente
        api.checaContinentesJogador();

        // Vamos chegar as tropas a receber, de cada jogador
        api.checarTropasGanhar();
        api.imprimeTropasARecber();

        // Vamos realizar a distribuição de exércitos de cada jogador em cada território
        api.posicionamentoExercitos();


        // Adicionando polígonos ao jogador
        Cartas.Territorio territorio1 = Cartas.Territorio.allTerritorios[0];
        Cartas.Territorio territorio2 = Cartas.Territorio.allTerritorios[1];
        Cartas.Territorio territorio3 = Cartas.Territorio.allTerritorios[2];

        ArrayList<Cartas.Territorio> poligonosPossuidos = new ArrayList<>();
        poligonosPossuidos.add(territorio1);
        poligonosPossuidos.add(territorio2);
        poligonosPossuidos.add(territorio3);


        api.jogadores.get(0).setPoligonosPossuidos(poligonosPossuidos);

        api.trocaCartasPoligono();

        //System.out.println(api.jogadores.get(0).getPoligonosPossuidos().get(0)); // expected == null
        System.out.println(api.jogadores.get(0).getPremio()); // expected 1

    }
}

