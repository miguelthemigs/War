package model;

import static org.junit.Assert.*;

import org.junit.*;

public class CartasTeste {

	@Test
	public void teste() {
		Cartas.Territorio territorio = new Cartas.Territorio("Angola", Cartas.Poligono.quadrado,new String[]{"África do Sul","Nigéria","Somália"});
		assertEquals("Angola",territorio.getPais());
		assertEquals(new String[]{"África do Sul","Nigéria","Somália"},territorio.getFronteira());
		assertEquals(Cartas.Poligono.quadrado,territorio.getPoligono());
	}

}
