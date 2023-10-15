package model;

import static org.junit.Assert.*;
import org.junit.*;

public class PaisTeste {

	@Test
	public void test() {
		Pais a = new Pais("nome",null);
		assertEquals("Nome inserido indevidamente","nome",a.getNome());
		a.setTropas(1);
		assertEquals("Tropas definidas indevidamente",1,a.getTropas());
		a.addTropas(2);
		assertEquals("Tropas adicionadas indevidamente",3,a.getTropas());
		a.removeTropas(1);
		assertEquals("Tropas removidas indevidamente",2,a.getTropas());
	}

}
