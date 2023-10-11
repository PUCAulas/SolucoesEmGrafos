package test.java;

import main.java.Cidade;
import main.java.Estrada;
import main.java.Grafo;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GrafoTest {
    private Grafo grafo;
    private Cidade cidadeA;
    private Cidade cidadeB;
    private Cidade cidadeC;

    @Before
    public void setUp() {
        grafo = new Grafo();
        cidadeA = new Cidade(1, "Cidade A");
        cidadeB = new Cidade(2, "Cidade B");
        cidadeC = new Cidade(3, "Cidade C");
        grafo.adicionarCidade(cidadeA);
        grafo.adicionarCidade(cidadeB);
        grafo.adicionarCidade(cidadeC);
        grafo.adicionarEstrada(new Estrada(cidadeA, cidadeB, 100));
    }

    @Test
    public void testExisteEstradaExistente() {
        assertTrue(grafo.existeEstrada(cidadeA, cidadeB));
    }

    @Test
    public void testExisteEstradaNaoExistente() {
        assertFalse(grafo.existeEstrada(cidadeA, cidadeC));
    }

    @Test
    public void testCidadesInalcancaveis() {
        List<Cidade> inalcancaveis = grafo.cidadesInalcancaveis();
        assertEquals(1, inalcancaveis.size());
        assertEquals(cidadeC, inalcancaveis.get(0));
    }
}
