package model;

import static org.junit.Assert.*;

import org.junit.*;

public class TabuleiroTeste {

	@Test
	public void testeTabuleiro() {
		// Verifica se o nome e as fronteiras são definidos corretamente para alguns países
		assertEquals("África do Sul", Tabuleiro.AfricaSul.getNome());
		assertEquals(new String[]{"África do Sul","Nigéria","Somália"}, Tabuleiro.Angola.getFronteiras());
		Pais pais1 = new Pais(Cartas.Territorio.allTerritorios[47].getPais(), Cartas.Territorio.allTerritorios[47].getFronteira());
		Pais pais2 = new Pais(Cartas.Territorio.allTerritorios[48].getPais(), Cartas.Territorio.allTerritorios[48].getFronteira());
		assertEquals(Tabuleiro.Australia.getNome(), pais1.getNome());
		assertNotEquals(Tabuleiro.Australia.getNome(), pais2.getNome());
	}
	@Test
	public void testTabuleiroContinentes() {
		// Verifica se os continentes contêm os países corretos
		assertArrayEquals(new Pais[]{Tabuleiro.Australia, Tabuleiro.Indonesia, Tabuleiro.NovaZelandia, Tabuleiro.Perth},Tabuleiro.Oceania);
	}
}
