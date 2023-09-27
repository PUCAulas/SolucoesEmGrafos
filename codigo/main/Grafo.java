package main.java;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
	private List<Cidade> cidades;
    private List<Estrada> estradas;

    public Grafo() {
        cidades = new ArrayList<>();
        estradas = new ArrayList<>();
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

    //public List<Cidade> cidadesInalcancaveis()
        
    
    //public List<Cidade> recomendarVisitas()
       

    //public List<Cidade> menorRota(Cidade rodoviaria)
        
}