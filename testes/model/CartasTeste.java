package model;

import static org.junit.Assert.*;

import org.junit.*;

public class CartasTeste {

	@Test
	public void teste() {
		Cartas.Territorio territorio = new Cartas.Territorio("Angola", Cartas.Poligono.quadrado,new String[]{"África do Sul","Nigéria","Somália"});
		assertEquals("Nome de pais definido indevidamente","Angola",territorio.getPais());
		assertArrayEquals("Fronteiras de pais definidas indevidamente",new String[]{"África do Sul","Nigéria","Somália"},territorio.getFronteira());
		assertEquals("Polígono de pais definido indevidamente",Cartas.Poligono.quadrado,territorio.getPoligono());
	}

}
