package solucoesEmGrafos;

public class Cidade {
	private int id;
    private String nome;

    public Cidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int obterId() {
        return id;
    }

    public String obterNome() {
        return nome;
    }
}