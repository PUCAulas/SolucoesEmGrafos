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
            System.out.println("1. Verificar Existência de Estrada");
            System.out.println("2. Cidades Inalcançáveis");
            System.out.println("3. Recomendação de Visitação em Todas as Cidades e Estradas");
            System.out.println("4. Recomendação de Rota de Menor Distância");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();

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
                    System.out.println("Saindo...\n");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.\n");
            }
         
            System.out.print("Pressione Enter para continuar...");
            scanner.nextLine(); 
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

    private static void verificarExistenciaDeEstrada(Grafo grafo, Scanner scanner) {
        System.out.print("Digite o nome da cidade de origem: ");
        String origemNome = scanner.next();
        System.out.print("Digite o nome da cidade de destino: ");
        String destinoNome = scanner.next();

        Cidade origem = grafo.getCidadePorNome(origemNome);
        Cidade destino = grafo.getCidadePorNome(destinoNome);

        if (origem != null && destino != null) {
            if (grafo.existeEstrada(origem, destino)) {
                System.out.println("Existe uma estrada entre " + origemNome + " e " + destinoNome + "\n");
            } else {
                System.out.println("Não existe uma estrada entre " + origemNome + " e " + destinoNome);
            }
        } else {
            System.out.println("Cidades não encontradas.");
        }
    }

    private static void listarCidadesInalcançaveis(Grafo grafo) {
        List<Cidade> inalcancaveis = grafo.cidadesInalcancaveis();

        if (inalcancaveis.isEmpty()) {
            System.out.println("Todas as cidades são alcançáveis a partir de outras cidades.");
        } else {
            System.out.println("Cidades inalcançáveis:");
            for (Cidade cidade : inalcancaveis) {
                System.out.println(cidade.obterNome());
            }
        }
    }

    private static void recomendarVisitação(Grafo grafo) {
        List<String> recomendacoes = grafo.recomendarVisitas();
        for (String recomendacao : recomendacoes) {
            System.out.println(recomendacao);
        }
    }

    private static void recomendarRotaDeMenorDistancia(Grafo grafo, Scanner scanner) {
        System.out.print("Digite o nome da cidade de partida: ");
        String partidaNome = scanner.next();
        Cidade partida = grafo.getCidadePorNome(partidaNome);

        if (partida != null) {
            List<Cidade> menorRota = grafo.menorRota(partida);
            if (menorRota != null) {
                System.out.println("Rota de menor distância a partir de " + partidaNome + ":");
                for (Cidade cidade : menorRota) {
                    System.out.println(cidade.obterNome());
                }
            } else {
                System.out.println("Não foi possível encontrar uma rota de menor distância.");
            }
        } else {
            System.out.println("Cidade de partida não encontrada.");
        }
    }
}
