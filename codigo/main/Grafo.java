package main.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


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
    
    //Método que confere se existe estrada de qualquer cidade para qualquer cidade (a)
    public boolean existeEstrada(Cidade cidadeOrigem, Cidade cidadeDestino) {
        for (Estrada estrada : estradas) {
            if (estrada.obterOrigem().equals(cidadeOrigem) && estrada.obterDestino().equals(cidadeDestino)) {
                return true;
            }
        }
        return false;
    }
    //No caso de não ser possível chegar em alguma cidade via transporte terrestre, o método identifica quais cidades são inalcançáveis (b)
    public List<Cidade> cidadesInalcancaveis() {
        List<Cidade> inalcancaveis = new ArrayList<>();
        for (Cidade cidade : cidades) {
            boolean alcancaOutraCidade = false;
            for (Estrada estrada : estradas) {
                if (estrada.obterOrigem().equals(cidade) || estrada.obterDestino().equals(cidade)) {
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
    
    //Método que recomenda uma visita em todas as cidades e todas as estradas (c)
    public List<String> recomendarVisitas() {
        List<String> recomendacoes = new ArrayList<>();
        
        for (Cidade cidade : cidades) {
            recomendacoes.add("Visite a cidade " + cidade.obterNome());
        }
        
        for (Estrada estrada : estradas) {
            recomendacoes.add("Viaje de " + estrada.obterOrigem().obterNome() + " para " +
                estrada.obterDestino().obterNome());
        }
        
        return recomendacoes;
    }

    //Método que recomenda uma rota para um passageiro que deseja partir da rodoviária, percorrer todas as cidades conectadas e retornar à rodoviária, percorrendo a menordistância possível (d)
    public List<Cidade> menorRota(Cidade rodoviaria) {
        List<Cidade> rota = new ArrayList<>();
        Set<Cidade> visitadas = new HashSet<>();
        Queue<Cidade> fila = new LinkedList<>();
        fila.add(rodoviaria);
        visitadas.add(rodoviaria);

        while (!fila.isEmpty()) {
            Cidade cidadeAtual = fila.poll();
            rota.add(cidadeAtual);

            for (Cidade cidadeVizinha : cidadesConectadas(cidadeAtual)) {
                if (!visitadas.contains(cidadeVizinha)) {
                    fila.add(cidadeVizinha);
                    visitadas.add(cidadeVizinha);
                }
            }
        }

        rota.add(rodoviaria); 

        return rota;
    }

    private List<Cidade> cidadesConectadas(Cidade cidade) {
        List<Cidade> cidadesConectadas = new ArrayList<>();
        for (Estrada estrada : estradas) {
            if (estrada.obterOrigem() == cidade) {
                cidadesConectadas.add(estrada.obterDestino());
            } else if (estrada.obterDestino() == cidade) {
                cidadesConectadas.add(estrada.obterOrigem());
            }
        }
        return cidadesConectadas;
    }
   
    public List<Cidade> getCidades() {
        return cidades;
    }

    public Cidade getCidadePorNome(String nome) {
        for (Cidade cidade : cidades) {
            if (cidade.obterNome().trim().equalsIgnoreCase(nome.trim())) {
                return cidade;
            }
        }
        return null;
    }
    
    public int calcularDistanciaTotal(Cidade origem, Cidade destino) {
        for (Estrada estrada : estradas) {
            if ((estrada.obterOrigem() == origem && estrada.obterDestino() == destino)
                    || (estrada.obterOrigem() == destino && estrada.obterDestino() == origem)) {
                return estrada.obterPeso();
            }
        }
        return Integer.MAX_VALUE;
    }
}
