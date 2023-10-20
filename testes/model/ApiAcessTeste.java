package model;

import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class ApiAcessTeste {

    private static ApiAcess api;
    @Before
    public void inicializa() {
        // Configuração comum antes de todos os testes
        api = new ApiAcess();
        ArrayList<String> coresEscolhidas = new ArrayList<>();
        coresEscolhidas.add("verde");
        coresEscolhidas.add("branco");
        coresEscolhidas.add("vermelho");
        api.gerarJogadores(coresEscolhidas);
    }
    @Test
    public void testeGerarJogadores() {
        // Verifica se a lista de jogadores foi criada corretamente
        assertEquals("Quantidade de jogadores criadas errado",3, api.jogadores.size());

        // Verifica se cada jogador possui uma cor válida
        for (Jogador jogador : api.jogadores) {
            assertNotNull("Jogador com cor inválida",jogador.getCor());
        }
    }
    @Test
    public void testeSorteiaObjetivo() {
        // Executa o método que você deseja testar
        api.sorteiaObjetivo();

        // Verifica se cada jogador tem um objetivo atribuído
        for (Jogador jogador : api.jogadores) {
            assertNotNull("Jogador sem objetivo",jogador.getObjetivo());
        }

        // Verifica se os objetivos são diferentes entre os jogadores
        for (int i = 0; i < api.jogadores.size() - 1; i++) {
            for (int j = i + 1; j < api.jogadores.size(); j++) {
                assertNotEquals("Objetivos iguais entre jogadores",api.jogadores.get(i).getObjetivo(), api.jogadores.get(j).getObjetivo());
            }
        }
    }
    
    @Test
    public void testeSorteiaTerritorios() {
        // Executa o método que você deseja testar
        api.sorteiaTerritorios();

        // Verifica se cada jogador possui territórios atribuídos
        for (Jogador jogador : api.jogadores) {
            assertTrue("Jogador sem território",jogador.getNumTerritorios() > 0);
        }

        // Verifica se o número total de territórios é o mesmo antes e depois da atribuição
        int totalTerritoriosAntes = api.geraListaSorteioTerritorios().size(); //51
        int totalTerritoriosApos = 0;
        for (Jogador jogador : api.jogadores) {
            totalTerritoriosApos += jogador.getNumTerritorios();
        }
        assertEquals("Mais territórios que o devido",51,totalTerritoriosAntes);
        assertEquals("Número de territórios diferentes antes e depois de atribuição",totalTerritoriosAntes, totalTerritoriosApos);
    }
    @Test
    public void testeChecaContinentesJogador() {
    	int expected= 6;
    	int actual;
        ApiAcess api = new ApiAcess();
        ArrayList<String> coresEscolhidas = new ArrayList<>();
        coresEscolhidas.add("vermelho");
        api.gerarJogadores(coresEscolhidas);

        // Adiciona alguns territórios fictícios aos jogadores para teste
        api.sorteiaTerritorios();

        // Executa o método que você deseja testar
        api.checaContinentesJogador();

        // Verifica se cada jogador tem continentes atribuídos
        for (Jogador jogador : api.jogadores) {
        	actual = jogador.getNumContinentesPossuidos();
            assertEquals("Jogador sem continente atribuído",expected,actual);
        }
    }
    @Test
    public void testeChecarTropasGanhar() {
        // Adiciona alguns territórios fictícios aos jogadores para teste
        for (Jogador jogador : api.jogadores) {
        	api.sorteiaTerritorios();
        }

        // Executa o método que você deseja testar
        api.checarTropasGanhar();

        // Verifica se cada jogador tem tropas para adicionar atribuídas
        for (Jogador jogador : api.jogadores) {
            assertTrue("Jogador sem tropas para ser adicionadas",jogador.getTropasParaAdicionar() >= 3);
        }
    }
    @Test
    public void testePosicionamentoExercitos() {
        // Adiciona alguns territórios fictícios aos jogadores para teste
        for (Jogador jogador : api.jogadores) {
            api.sorteiaTerritorios();
        }

        // Executa o método que você deseja testar
        api.posicionamentoExercitos();

        // Verifica se as tropas foram adicionadas corretamente aos territórios
        for (Jogador jogador : api.jogadores) {
            for (Pais territorio : jogador.getTerritoriosPossuidos()) {
                assertTrue("Tropa adicionada incorretamente",territorio.getTropas() > 0);
            }
        }
    }
    @Test
    public void testaDistribuicaoCartas(){

        assertEquals(53, api.cartasEmJogo.size()); // ter certeza que tem todas as cartas
        for(Jogador jogador: api.jogadores){
            jogador.conquistouTerritorio = true;
            api.distribuiCartas();
        }
        for(Jogador jogador: api.jogadores){
            assertTrue(jogador.getPoligonosPossuidos().size() > 0); // os jogadores devem ter pelo menos uma carta
        }
        assertEquals(50, api.cartasEmJogo.size()); // pois removi da lista 3 cartas
        for(Jogador jogador: api.jogadores)
            assertFalse(jogador.conquistouTerritorio);



    }
    @After
    public  void limpa() {
        // Limpeza após todos os testes
        api = null;  // Define api como null para liberar recursos, se necessário
    }
}

