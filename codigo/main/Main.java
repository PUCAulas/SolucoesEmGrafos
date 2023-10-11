package main.java;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar Cidade");
            System.out.println("2. Adicionar Estrada");
            System.out.println("3. Verificar Existência de Estrada");
            System.out.println("4. Cidades Inalcançáveis");
            System.out.println("5. Recomendação de Visitação em Todas as Cidades e Estradas");
            System.out.println("6. Recomendação de Rota de Menor Distância");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    System.out.print("Informe o ID da cidade: ");
                    int cidadeId = scanner.nextInt();
                    scanner.nextLine();  // Consumir a quebra de linha
                    System.out.print("Informe o nome da cidade: ");
                    String cidadeNome = scanner.nextLine();
                    Cidade cidade = new Cidade(cidadeId, cidadeNome);
                    grafo.adicionarCidade(cidade);
                    System.out.println("Cidade adicionada com sucesso!");
                    break;
                case 2:
                    System.out.print("Informe o ID da cidade de origem: ");
                    int origemId = scanner.nextInt();
                    System.out.print("Informe o ID da cidade de destino: ");
                    int destinoId = scanner.nextInt();
                    System.out.print("Informe o peso da estrada: ");
                    int peso = scanner.nextInt();
                    Cidade origem = buscarCidadePorId(grafo, origemId);
                    Cidade destino = buscarCidadePorId(grafo, destinoId);
                    if (origem != null && destino != null) {
                        Estrada estrada = new Estrada(origem, destino, peso);
                        grafo.adicionarEstrada(estrada);
                        System.out.println("Estrada adicionada com sucesso!");
                    } else {
                        System.out.println("Cidade de origem ou destino não encontrada.");
                    }
                    break;
                case 3:
                    System.out.print("Informe o ID da cidade de origem: ");
                    int origemIdVerificar = scanner.nextInt();
                    System.out.print("Informe o ID da cidade de destino: ");
                    int destinoIdVerificar = scanner.nextInt();
                    Cidade origemVerificar = buscarCidadePorId(grafo, origemIdVerificar);
                    Cidade destinoVerificar = buscarCidadePorId(grafo, destinoIdVerificar);
                    if (origemVerificar != null && destinoVerificar != null) {
                        if (grafo.existeEstrada(origemVerificar, destinoVerificar)) {
                            System.out.println("Existe uma estrada entre as cidades.");
                        } else {
                            System.out.println("Não existe uma estrada entre as cidades.");
                        }
                    } else {
                        System.out.println("Cidade de origem ou destino não encontrada.");
                    }
                    break;
                case 4:
                    List<Cidade> inalcancaveis = grafo.cidadesInalcancaveis();
                    System.out.println("Cidades inalcançáveis:");
                    for (Cidade inalcancavel : inalcancaveis) {
                        System.out.println(inalcancavel.obterNome());
                    }
                    break;
                case 5:
                    System.out.println("Recomendação de Visitação em Todas as Cidades e Estradas:");
                    // Implemente a lógica para recomendar a visitação em todas as cidades e estradas aqui.
                    break;
                case 6:
                    System.out.println("Recomendação de Rota de Menor Distância:");
                    // Implemente a lógica para recomendar a rota de menor distância aqui.
                    break;
                case 7:
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    
    private static Cidade buscarCidadePorId(Grafo grafo, int cidadeId) {
        for (Cidade cidade : grafo.getCidades()) {
            if (cidade.obterId() == cidadeId) {
                return cidade;
            }
        }
        return null;
    }
}
