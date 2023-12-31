package main.java;

public class Estrada {
    private Cidade origem;
    private Cidade destino;
    private int peso;

    public Estrada(Cidade origem, Cidade destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Cidade obterOrigem() {
        return origem;
    }

    public Cidade obterDestino() {
        return destino;
    }

    public int obterPeso() {
        return peso;
    }
}
