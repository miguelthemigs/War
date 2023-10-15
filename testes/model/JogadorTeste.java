package model;

import org.junit.*;
import static org.junit.Assert.*;

public class JogadorTeste {

	private Jogador jogador;

	@Before
	public void inicializa() {
		jogador = new Jogador(Jogador.Cor.azul);
	}
	@Test
	public void testGetCor() {
		assertEquals(Jogador.Cor.azul, jogador.getCor());
	}
	@Test
	public void testSetObjetivo() {
		jogador.setObjetivo("Conquistar a Europa");
		assertEquals("Conquistar a Europa", jogador.getObjetivo());
	}
	@Test
	public void testSetTropasParaAdicionar() {
		jogador.setTropasParaAdicionar(5);
		assertEquals(5, jogador.getTropasParaAdicionar());
	}
	//Outras funções já testadas na API
}

