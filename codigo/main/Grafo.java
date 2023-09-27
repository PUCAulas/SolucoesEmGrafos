package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Grafo {
	private List<Cidade> cidades;
    private List<Estrada> estradas;

    public Grafo() {
        this.cidades = new ArrayList<>();
        this.estradas = new ArrayList<>();
    }

    public void adicionarCidade(Cidade cidade) {
        cidades.add(cidade);
    }

    public void adicionarEstrada(Estrada estrada) {
        estradas.add(estrada);
    }

    public boolean existeEstrada(Cidade cidadeOrigem, Cidade cidadeDestino) {
        for (Estrada estrada : estradas) {
            if (estrada.obterOrigem().equals(cidadeOrigem) && estrada.obterDestino().equals(cidadeDestino)) {
                return true;
            }
        }
        return false;
    }

    public Set<Cidade> cidadesInalcancaveis() {
        Set<Cidade> inalcancaveis = new HashSet<>(cidades);
        for (Estrada estrada : estradas) {
            inalcancaveis.remove(estrada.obterDestino());
        }
        return inalcancaveis;
    }
}
