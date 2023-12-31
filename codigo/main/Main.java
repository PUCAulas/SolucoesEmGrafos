package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
	public static void main(String[] args) {
	    Grafo grafo = lerGrafoDeArquivo("grafos.txt");
	    Scanner scanner = new Scanner(System.in);

	    int escolha;
	    do {
	        System.out.println("Menu:");
	        System.out.println("1. Verificar existência de estrada");
	        System.out.println("2. Cidades inalcançáveis");
	        System.out.println("3. Recomendação de visitação em todas as cidades e estradas");
	        System.out.println("4. Recomendação de rota de menor distância");
	        System.out.println("5. Sair");
	        System.out.print("Escolha uma opção: ");
	        escolha = scanner.nextInt();
	        scanner.nextLine(); 

	        switch (escolha) {
	            case 1:
	                verificarExistenciaDeEstrada(grafo, scanner);
	                break;
	            case 2:
	                listarCidadesInalcançaveis(grafo);
	                break;
	            case 3:
	                recomendarVisitação(grafo);
	                break;
	            case 4:
	                recomendarRotaDeMenorDistancia(grafo, scanner);
	                break;
	            case 5:
	                System.out.println("\nSaindo...\n");
	                break;
	            default:
	                System.out.println("\nOpção inválida. Tente novamente.\n");
	        }

	        System.out.print("\nPressione Enter para continuar...\n");
	        scanner.nextLine();
	    } while (escolha != 5);
	}

    private static Grafo lerGrafoDeArquivo(String nomeArquivo) {
        Grafo grafo = new Grafo();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(": ");
                String nomeCidade = partes[0];
                String[] estradas = partes[1].split(", ");
                Cidade cidade = new Cidade(grafo.getCidades().size() + 1, nomeCidade);
                grafo.adicionarCidade(cidade);

                for (String estradaStr : estradas) {
                    String[] estradaParts = estradaStr.split(" \\(");
                    String cidadeDestinoNome = estradaParts[0];
                    int peso = Integer.parseInt(estradaParts[1].replaceAll("\\D", ""));
                    Cidade cidadeDestino = grafo.getCidadePorNome(cidadeDestinoNome);
                    if (cidadeDestino != null) {
                        grafo.adicionarEstrada(new Estrada(cidade, cidadeDestino, peso));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return grafo;
    }

    //1. Verificar existência de estrada
    private static void verificarExistenciaDeEstrada(Grafo grafo, Scanner scanner) {
        System.out.print("\nDigite o nome da cidade de origem: ");
        String origemNome = scanner.nextLine();
        System.out.print("Digite o nome da cidade de destino: ");
        String destinoNome = scanner.nextLine();

        Cidade origem = grafo.getCidadePorNome(origemNome);
        Cidade destino = grafo.getCidadePorNome(destinoNome);

        if (origem != null && destino != null) {
            if (grafo.existeEstrada(origem, destino)) {
                System.out.println("\nExiste uma estrada entre " + origemNome + " e " + destinoNome + "\n");
            } else {
                System.out.println("\nNão existe uma estrada entre " + origemNome + " e " + destinoNome);
            }
        } else {
            System.out.println("\nCidades não encontradas.");
        }
    }

    //2. Cidades inalcançáveis
    private static void listarCidadesInalcançaveis(Grafo grafo) {
        List<Cidade> inalcancaveis = grafo.cidadesInalcancaveis();

        if (inalcancaveis.isEmpty()) {
            System.out.println("\nTodas as cidades são alcançáveis a partir de outras cidades.");
        } else {
            System.out.println("\nCidades inalcançáveis:");
            for (Cidade cidade : inalcancaveis) {
                System.out.println(cidade.obterNome());
            }
        }
    }

    //3. Recomendação de visitação em todas as cidades e estradas
    private static void recomendarVisitação(Grafo grafo) {
        List<String> recomendacoes = grafo.recomendarVisitas();
        for (String recomendacao : recomendacoes) {
            System.out.println(recomendacao);
        }
    }

  //4. Recomendação de rota de menor distância
    private static void recomendarRotaDeMenorDistancia(Grafo grafo, Scanner scanner) {
        System.out.print("\nDigite o nome da cidade de partida: ");
        String partidaNome = scanner.nextLine();
        Cidade partida = grafo.getCidadePorNome(partidaNome);

        if (partida != null) {
            List<Cidade> menorRota = grafo.menorRota(partida);
            if (menorRota != null) {
                int distanciaTotal = 0;
                System.out.println("\nRota de menor distância a partir de " + partidaNome + ":");
                for (int i = 0; i < menorRota.size() - 1; i++) {
                    Cidade cidadeOrigem = menorRota.get(i);
                    Cidade cidadeDestino = menorRota.get(i + 1);
                    int distancia = grafo.calcularDistanciaTotal(cidadeOrigem, cidadeDestino);
                    distanciaTotal += distancia;
                    System.out.println("Viaje de " + cidadeOrigem.obterNome() + " para " + cidadeDestino.obterNome() + " (Distância: " + distancia + " km)");
                }
                System.out.println("Distância Total da Rota: " + distanciaTotal + " km");
            } else {
                System.out.println("\nNão foi possível encontrar uma rota de menor distância.");
            }
        } else {
            System.out.println("\nCidade de partida não encontrada.");
        }
    }
}
