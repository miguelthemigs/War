package model;

import static org.junit.Assert.*;
import org.junit.*;

public class PaisTeste {

	@Test
	public void test() {
		Pais a = new Pais("nome",null);
		assertEquals("nome",a.getNome());
		a.setTropas(1);
		assertEquals(1,a.getTropas());
		a.addTropas(2);
		assertEquals(3,a.getTropas());
		a.removeTropas(1);
		assertEquals(2,a.getTropas());
	}

}
