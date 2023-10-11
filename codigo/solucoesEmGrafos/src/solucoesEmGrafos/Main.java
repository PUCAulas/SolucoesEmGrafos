package solucoesEmGrafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        Scanner scanner = new Scanner(System.in);
        lerArquivoConfiguracao(grafo);
        
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
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Nome da cidade: ");
                    String nomeCidade = scanner.nextLine();
                    Cidade novaCidade = new Cidade(grafo.cidades.size() + 1, nomeCidade);
                    grafo.adicionarCidade(novaCidade);
                    break;
                case 2:
                    System.out.print("ID da cidade de origem: ");
                    int idOrigem = scanner.nextInt();
                    System.out.print("ID da cidade de destino: ");
                    int idDestino = scanner.nextInt();
                    System.out.print("Peso da estrada: ");
                    int peso = scanner.nextInt();
                    grafo.adicionarEstrada(new Estrada(grafo.cidades.get(idOrigem - 1), grafo.cidades.get(idDestino - 1), peso));
                    break;
                case 3:
                    System.out.print("ID da cidade de origem: ");
                    int idOrigemEstrada = scanner.nextInt();
                    System.out.print("ID da cidade de destino: ");
                    int idDestinoEstrada = scanner.nextInt();
                    boolean existe = grafo.existeEstrada(grafo.cidades.get(idOrigemEstrada - 1), grafo.cidades.get(idDestinoEstrada - 1));
                    if (existe) {
                        System.out.println("Existe uma estrada entre as cidades.");
                    } else {
                        System.out.println("Não existe uma estrada entre as cidades.");
                    }
                    break;
                case 4:
                    List<Cidade> inalcancaveis = grafo.cidadesInalcancaveis();
                    System.out.println("Cidades inalcançáveis:");
                    for (Cidade cidade : inalcancaveis) {
                        System.out.println(cidade.obterNome());
                    }
                    break;
                case 5:
                	 System.out.println("Recomendação de Visitação em Todas as Cidades e Estradas:");
                	    List<Cidade> cidadesVisitadas = new ArrayList<>();
                	    dfsRecomendacao(grafo, grafo.cidades.get(0), cidadesVisitadas);
                    break;
                case 6:
                	 System.out.print("ID da cidade de origem: ");
                	    int idOrigemRota = scanner.nextInt();
                	    System.out.print("ID da cidade de destino: ");
                	    int idDestinoRota = scanner.nextInt();

                	    Cidade origemRota = grafo.cidades.get(idOrigemRota - 1);
                	    Cidade destinoRota = grafo.cidades.get(idDestinoRota - 1);

                	    List<Cidade> rotaMenorDistancia = grafo.menorRota(origemRota, destinoRota);

                	    System.out.println("Rota de Menor Distância:");
                	    for (Cidade cidade : rotaMenorDistancia) {
                	        System.out.println(cidade.obterNome());
                	    }
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
        public static void lerArquivoConfiguracao(Grafo grafo) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("grafos.txt"));
                String linha;

                while ((linha = reader.readLine()) != null) {
                    if (linha.equals("Cidades:")) {
                        while ((linha = reader.readLine()) != null && !linha.equals("Estradas:")) {
                            String[] cidadeInfo = linha.split(", ");
                            int id = Integer.parseInt(cidadeInfo[0]);
                            String nome = cidadeInfo[1];
                            grafo.adicionarCidade(new Cidade(id, nome));
                        }
                    } else if (linha.equals("Estradas:")) {
                        while ((linha = reader.readLine()) != null) {
                            String[] estradaInfo = linha.split(", ");
                            int idOrigem = Integer.parseInt(estradaInfo[0]);
                            int idDestino = Integer.parseInt(estradaInfo[1]);
                            int peso = Integer.parseInt(estradaInfo[2]);
                            grafo.adicionarEstrada(new Estrada(grafo.cidades.get(idOrigem - 1), grafo.cidades.get(idDestino - 1), peso));
                        }
                    }
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

	private static void dfsRecomendacao(Grafo grafo, Cidade cidade, List<Cidade> cidadesVisitadas) {
		// TODO Auto-generated method stub
		
	}
}
