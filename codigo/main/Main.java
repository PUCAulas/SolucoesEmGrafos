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
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
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

}
