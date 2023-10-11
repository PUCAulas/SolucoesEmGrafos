package solucoesEmGrafos;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Grafo {
    List<Cidade> cidades;
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
            if (estrada.obterOrigem() == cidadeOrigem && estrada.obterDestino() == cidadeDestino) {
                return true;
            }
        }
        return false;
    }

    public List<Cidade> cidadesInalcancaveis() {
        List<Cidade> inalcancaveis = new ArrayList<>();
        for (Cidade cidade : cidades) {
            boolean alcancaOutraCidade = false;
            for (Cidade outraCidade : cidades) {
                if (!cidade.equals(outraCidade) && existeEstrada(cidade, outraCidade)) {
                    alcancaOutraCidade = true;
                    break;
                }
            }
            if (!alcancaOutraCidade) {
                inalcancaveis.add(cidade);
            }
        }
        return inalcancaveis;
         
    }
    
    public void dfsRecomendacao(Grafo grafo, Cidade cidade, List<Cidade> cidadesVisitadas) {
        if (!cidadesVisitadas.contains(cidade)) {
            cidadesVisitadas.add(cidade);
            System.out.println("Visite a cidade: " + cidade.obterNome());

            for (Cidade vizinha : obterCidadesVizinhas(cidade)) {
                if (!cidadesVisitadas.contains(vizinha)) {
                    System.out.println("Pegue a estrada para: " + vizinha.obterNome());
                    dfsRecomendacao(grafo, vizinha, cidadesVisitadas);
                }
            }
        }
    }

    public List<Cidade> obterCidadesVizinhas(Cidade cidade) {
        List<Cidade> vizinhas = new ArrayList<>();
        for (Estrada estrada : estradas) {
            if (estrada.obterOrigem() == cidade) {
                vizinhas.add(estrada.obterDestino());
            }
        }
        return vizinhas;
    }

public List<Cidade> menorRota(Cidade origem, Cidade destino) {
    Map<Cidade, Integer> distancia = new HashMap<>();
    Map<Cidade, Cidade> predecessores = new HashMap<>();
    PriorityQueue<Cidade> filaPrioridade = new PriorityQueue<>((c1, c2) -> distancia.get(c1) - distancia.get(c2));

    // Inicialize a distância das cidades como infinito, exceto a cidade de origem com distância 0.
    for (Cidade cidade : cidades) {
        distancia.put(cidade, Integer.MAX_VALUE);
    }
    distancia.put(origem, 0);

    filaPrioridade.add(origem);

    while (!filaPrioridade.isEmpty()) {
        Cidade cidadeAtual = filaPrioridade.poll();

        if (cidadeAtual == destino) {
            break; // Encontramos o destino, saímos do loop.
        }

        for (Cidade vizinha : obterCidadesVizinhas(cidadeAtual)) {
            int distanciaAtual = distancia.get(cidadeAtual);
            int distanciaNova = distanciaAtual + obterDistanciaEntreCidades(cidadeAtual, vizinha);

            if (distanciaNova < distancia.get(vizinha)) {
                distancia.put(vizinha, distanciaNova);
                predecessores.put(vizinha, cidadeAtual);
                filaPrioridade.add(vizinha);
            }
        }
    }

    // Reconstrua o caminho da cidade de destino até a cidade de origem.
    List<Cidade> rota = new ArrayList<>();
    Cidade cidadeAtual = destino;
    while (cidadeAtual != null) {
        rota.add(cidadeAtual);
        cidadeAtual = predecessores.get(cidadeAtual);
    }
    Collections.reverse(rota);

    return rota;
}

public int obterDistanciaEntreCidades(Cidade cidadeOrigem, Cidade cidadeDestino) {
    for (Estrada estrada : estradas) {
        if (estrada.obterOrigem() == cidadeOrigem && estrada.obterDestino() == cidadeDestino) {
            return estrada.obterPeso();
        }
    }
    return Integer.MAX_VALUE; // Caso não haja uma estrada direta entre as cidades.
}
}