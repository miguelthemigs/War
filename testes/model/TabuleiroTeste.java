package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TabuleiroTeste {

	@Test
	public void test() {
		Tabuleiro a = new Tabuleiro();
		Pais b = new Pais(Cartas.Territorio.allTerritorios[47].getPais(), Cartas.Territorio.allTerritorios[47].getFronteira());
		assertEquals(a.Australia, b);
	}

}
