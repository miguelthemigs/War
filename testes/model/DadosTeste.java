package model;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class DadosTeste {

	@Test
	public void test() {
		int dA = Dados.jogarAmarelo();
		int dV = Dados.jogarVermelho();
		Integer[] c = {1, 2, 3, 4, 5, 6};
		List<Integer> lista = Arrays.asList(c);
		assertTrue("Valor amarelo sorteado fora da lista",lista.contains(dA));
		assertTrue("Valor vermelho sorteado fora da lista",lista.contains(dV));
	}

}
