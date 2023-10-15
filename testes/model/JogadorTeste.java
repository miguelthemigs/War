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
		assertEquals("Jogador com cor inserida indevidamente",Jogador.Cor.azul, jogador.getCor());
	}
	@Test
	public void testSetObjetivo() {
		jogador.setObjetivo("Conquistar a Europa");
		assertEquals("Jogador com objetivo inserido indevidamente","Conquistar a Europa", jogador.getObjetivo());
	}
	@Test
	public void testSetTropasParaAdicionar() {
		jogador.setTropasParaAdicionar(5);
		assertEquals("Tropas para jogador adicionar inserido indevidamente",5, jogador.getTropasParaAdicionar());
	}
	//Outras funções já testadas na API
}

