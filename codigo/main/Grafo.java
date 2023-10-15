package main.java;

import java.util.ArrayList;
import java.util.List;

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
    
    public List<String> recomendarVisitas() {
        List<String> recomendacoes = new ArrayList<>();
        
        for (Cidade cidade : cidades) {
            recomendacoes.add("Visite a cidade " + cidade.obterNome());
        }
        
        // Recomendar viagens ao longo das estradas
        for (Estrada estrada : estradas) {
            recomendacoes.add("Viaje de " + estrada.obterOrigem().obterNome() + " para " +
                estrada.obterDestino().obterNome());
        }
        
        return recomendacoes;
    }

    public List<Cidade> menorRota(Cidade partida) {
    	//Caixeiro viajante usando Dijkstra (inicia todo mundo com infinito) e ciclo Hamiltoniano 
    	//Verificar se o grafo tem um ciclo. Se não tem ciclo, retornar mensagem dizendo que não é possível. Se tiver ciclo, identificar a solução que tem menor peso.
		return null;
    }
   
    public List<Cidade> getCidades() {
        return cidades;
    }

    public Cidade getCidadePorNome(String nome) {
        for (Cidade cidade : cidades) {
            if (cidade.obterNome().equalsIgnoreCase(nome)) {
                return cidade;
            }
        }
        return null;
    }
}
