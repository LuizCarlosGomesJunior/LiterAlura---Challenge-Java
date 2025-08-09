package br.com.alura.literalura.principal;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0){
            var menu = """
                Escolha o número de sua opção:
                1 - buscar livro pelo título
                2 - listar livros registrados
                3 - listar autores registrados
                4 - listar autores vivos em um determinado ano
                5 - listar livros em um determinado idioma
                0 - sair
                """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");



            }
        }

    }

    private void listarLivrosPorIdioma() {
    }

    private void listarAutoresVivosPorAno() {
    }

    private void listarAutoresRegistrados() {
    }

    private void listarLivrosRegistrados() {
    }

    private void buscarLivroPeloTitulo() {
    }
}
