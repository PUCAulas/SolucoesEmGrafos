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
            if (estrada.getOrigem() == cidadeOrigem && estrada.getDestino() == cidadeDestino) {
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
    
    //public List<String> recomendarVisitas() {
        //Problema do caixeiro-viajante (Traveling Salesman Problem)
        //return null;
    //}

    //public List<Cidade> menorRota(Cidade partida) {
        //return null;
    //}
}
