package model;

import static org.junit.Assert.*;

import org.junit.*;

public class TabuleiroTeste {

	@Test
	public void testeTabuleiro() {
		// Verifica se o nome e as fronteiras são definidos corretamente para alguns países
		assertEquals("Nome de pais definido indevidamente","ÁfricadoSul", Tabuleiro.AfricaSul.getNome());
		assertArrayEquals("Fronteira de pais definido indevidamente",new String[]{"ÁfricadoSul","Nigéria","Somália"}, Tabuleiro.Angola.getFronteiras());
		Pais pais1 = new Pais(Cartas.Territorio.allTerritorios[47].getPais(), Cartas.Territorio.allTerritorios[47].getFronteira());
		Pais pais2 = new Pais(Cartas.Territorio.allTerritorios[48].getPais(), Cartas.Territorio.allTerritorios[48].getFronteira());
		assertEquals("Tabuleiro com nome inserido indevidamente",Tabuleiro.Australia.getNome(), pais1.getNome());
	}
	@Test
	public void testTabuleiroContinentes() {
		// Verifica se os continentes contêm os países corretos
		assertArrayEquals("Tabuleiro com continente inserido indevidamente",new Pais[]{Tabuleiro.Australia, Tabuleiro.Indonesia, Tabuleiro.NovaZelandia, Tabuleiro.Perth},Tabuleiro.Oceania);
	}
}
