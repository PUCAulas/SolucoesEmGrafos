package test.java;

import org.junit.Test;
import org.junit.jupiter.api.Test;

public class GrafoTest {
	@Test
    public void testExisteEstrada() {
        Grafo grafo = new Grafo();
        Cidade cidadeA = new Cidade(1, "Cidade A");
        Cidade cidadeB = new Cidade(2, "Cidade B");
        Estrada estradaAB = new Estrada(cidadeA, cidadeB, 100);

        grafo.adicionarCidade(cidadeA);
        grafo.adicionarCidade(cidadeB);
        grafo.adicionarEstrada(estradaAB);

        assertTrue(grafo.existeEstrada(cidadeA, cidadeB));
        assertFalse(grafo.existeEstrada(cidadeB, cidadeA));
    }
}
