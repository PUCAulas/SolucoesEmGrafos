package test.java;

import main.java.Cidade;
import main.java.Estrada;
import main.java.Grafo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.List;

public class GrafoTest {

    private Grafo grafo;

    @Before
    public void setUp() {
        grafo = new Grafo();
    }

    @Test
    public void testExisteEstrada() {
        Cidade cidadeA = new Cidade(1, "Cidade A");
        Cidade cidadeB = new Cidade(2, "Cidade B");
        Cidade cidadeC = new Cidade(3, "Cidade C");

        grafo.adicionarCidade(cidadeA);
        grafo.adicionarCidade(cidadeB);
        grafo.adicionarCidade(cidadeC);

        Estrada estradaAB = new Estrada(cidadeA, cidadeB, 10);
        grafo.adicionarEstrada(estradaAB);

        assertTrue(grafo.existeEstrada(cidadeA, cidadeB));
        assertFalse(grafo.existeEstrada(cidadeA, cidadeC));
    }

    @Test
    public void testCidadesInalcancaveis() {
        Cidade cidadeA = new Cidade(1, "Cidade A");
        Cidade cidadeB = new Cidade(2, "Cidade B");
        Cidade cidadeC = new Cidade(3, "Cidade C");

        grafo.adicionarCidade(cidadeA);
        grafo.adicionarCidade(cidadeB);
        grafo.adicionarCidade(cidadeC);

        Estrada estradaAB = new Estrada(cidadeA, cidadeB, 10);

        grafo.adicionarEstrada(estradaAB);

        List<Cidade> inalcancaveis = grafo.cidadesInalcancaveis();

        assertEquals(1, inalcancaveis.size());
        assertTrue(inalcancaveis.contains(cidadeC));
    }
}
