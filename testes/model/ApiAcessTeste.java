package model;
/*
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;


public class ApiAcessTeste {
	@Test
	public void Teste(){
		ApiAcess api = new ApiAcess();
		ArrayList<String> coresEscolhidas = new ArrayList<>();
		coresEscolhidas.add("azul");
		api.gerarJogadores(coresEscolhidas);
		Jogador a = new Jogador(Jogador.Cor.valueOf("azul"));
		assertTrue(api.jogadores.contains(a));
	}
}
*/
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;

public class ApiAcessTeste {

    
    @Test
    public void testGerarJogadores() {
        ApiAcess api = new ApiAcess();
        ArrayList<String> coresEscolhidas = new ArrayList<>();
        coresEscolhidas.add("vermelho");
        coresEscolhidas.add("azul");
        coresEscolhidas.add("verde");

        // Executa o método que você deseja testar
        api.gerarJogadores(coresEscolhidas);

        // Verifica se a lista de jogadores foi criada corretamente
        assertEquals(3, api.jogadores.size());

        // Verifica se cada jogador possui uma cor válida
        for (Jogador jogador : api.jogadores) {
            assertNotNull(jogador.getCor());
        }
    }
    @Test
    public void testSorteiaObjetivo() {
        ApiAcess api = new ApiAcess();
        ArrayList<String> coresEscolhidas = new ArrayList<>();
        coresEscolhidas.add("vermelho");
        coresEscolhidas.add("azul");
        coresEscolhidas.add("verde");
        api.gerarJogadores(coresEscolhidas);

        // Executa o método que você deseja testar
        api.sorteiaObjetivo();

        // Verifica se cada jogador tem um objetivo atribuído
        for (Jogador jogador : api.jogadores) {
            assertNotNull(jogador.getObjetivo());
        }

        // Verifica se os objetivos são diferentes entre os jogadores
        for (int i = 0; i < api.jogadores.size() - 1; i++) {
            for (int j = i + 1; j < api.jogadores.size(); j++) {
                assertNotEquals(api.jogadores.get(i).getObjetivo(), api.jogadores.get(j).getObjetivo());
            }
        }
    }
    
    @Test
    public void testSorteiaTerritorios() {
        ApiAcess api = new ApiAcess();
        ArrayList<String> coresEscolhidas = new ArrayList<>();
        coresEscolhidas.add("vermelho");
        coresEscolhidas.add("azul");
        coresEscolhidas.add("verde");
        api.gerarJogadores(coresEscolhidas);

        // Executa o método que você deseja testar
        api.sorteiaTerritorios();

        // Verifica se cada jogador possui territórios atribuídos
        for (Jogador jogador : api.jogadores) {
            assertTrue(jogador.getNumTerritorios() > 0);
        }

        // Verifica se o número total de territórios é o mesmo antes e depois da atribuição
        int totalTerritoriosAntes = api.geraListaSorteioTerritorios().size(); //51
        int totalTerritoriosApos = 0;
        for (Jogador jogador : api.jogadores) {
            totalTerritoriosApos += jogador.getNumTerritorios();
        }
        assertEquals(51,totalTerritoriosAntes);
        assertEquals(totalTerritoriosAntes, totalTerritoriosApos);
    }
    @Test
    public void testChecaContinentesJogador() {
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
            assertEquals(expected,actual);
        }
    }
    @Test
    public void testChecarTropasGanhar() {
        ApiAcess api = new ApiAcess();
        ArrayList<String> coresEscolhidas = new ArrayList<>();
        coresEscolhidas.add("vermelho");
        coresEscolhidas.add("azul");
        coresEscolhidas.add("verde");
        api.gerarJogadores(coresEscolhidas);

        // Adiciona alguns territórios fictícios aos jogadores para teste
        for (Jogador jogador : api.jogadores) {
        	api.sorteiaTerritorios();
        }

        // Executa o método que você deseja testar
        api.checarTropasGanhar();

        // Verifica se cada jogador tem tropas para adicionar atribuídas
        for (Jogador jogador : api.jogadores) {
            assertTrue(jogador.getTropasParaAdicionar() >= 3);
        }
    }
}

