package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.AutorDto;
import br.com.alura.literalura.dto.LivroDto;
import br.com.alura.literalura.dto.ResultadoBusca;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    // Guarda o último resultado para usar nas outras opções
    private ResultadoBusca ultimoResultado;

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

    private void buscarLivroPeloTitulo() {
        System.out.println("Digite o título do livro:");
        var livro = leitura.nextLine();
        var json = consumoApi.obterDados(ENDERECO + livro.replace(" ", "+"));

        ultimoResultado = conversor.obterDados(json, ResultadoBusca.class);

        if (ultimoResultado.getResults() == null || ultimoResultado.getResults().isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }

        System.out.println("Livros encontrados:");
        for (LivroDto livroDto : ultimoResultado.getResults()) {
            imprimirLivro(livroDto);
        }
    }

    private void listarLivrosRegistrados() {
        if (ultimoResultado == null || ultimoResultado.getResults().isEmpty()) {
            System.out.println("Nenhum livro registrado. Faça uma busca primeiro.");
            return;
        }

        System.out.println("Lista de livros registrados:");
        for (LivroDto livroDto : ultimoResultado.getResults()) {
            imprimirLivro(livroDto);
        }
    }

    private void listarAutoresRegistrados() {
        if (ultimoResultado == null || ultimoResultado.getResults().isEmpty()) {
            System.out.println("Nenhum autor registrado. Faça uma busca primeiro.");
            return;
        }

        System.out.println("Lista de autores registrados:");
        ultimoResultado.getResults().stream()
                .flatMap(l -> l.getAuthors().stream())
                .distinct()
                .forEach(autor -> System.out.println("- " + autor.getName()));
    }

    private void listarAutoresVivosPorAno() {
        if (ultimoResultado == null || ultimoResultado.getResults().isEmpty()) {
            System.out.println("Nenhum dado disponível. Faça uma busca primeiro.");
            return;
        }

        System.out.println("Digite o ano para verificar autores vivos:");
        int ano = leitura.nextInt();
        leitura.nextLine();

        System.out.println("Autores vivos no ano " + ano + ":");
        ultimoResultado.getResults().stream()
                .flatMap(l -> l.getAuthors().stream())
                .distinct()
                .filter(autor -> (autor.getBirth_year() != null && autor.getBirth_year() <= ano)
                        && (autor.getDeath_year() == null || autor.getDeath_year() >= ano))
                .forEach(autor -> System.out.println("- " + autor.getName()));
    }

    private void listarLivrosPorIdioma() {
        if (ultimoResultado == null || ultimoResultado.getResults().isEmpty()) {
            System.out.println("Nenhum dado disponível. Faça uma busca primeiro.");
            return;
        }

        var menuIdioma = """
                Insira o idioma para realizar a busca:
                es- espanhol
                en- inglês
                fr- francês
                pt- portugues
                """;

        System.out.println(menuIdioma);
        String idioma = leitura.nextLine();


        List<LivroDto> filtrados = ultimoResultado.getResults().stream()
                .filter(livro -> livro.getLanguages().contains(idioma))
                .toList();

        if (filtrados.isEmpty()) {
            System.out.println("Não existem livros encontrados nesse idioma no banco de dados");
            return;
        }

        System.out.println("----- LIVRO -----");
        for (LivroDto livro : filtrados) {
            imprimirLivro(livro);
        }
    }

    private void imprimirLivro(LivroDto livroDto) {
        System.out.println("Título: " + livroDto.getTitle());
        System.out.println("Idiomas: " + String.join(", ", livroDto.getLanguages()));
        System.out.println("Downloads: " + livroDto.getDownload_count());
        System.out.print("Autor(es): ");
        if (livroDto.getAuthors() != null) {
            for (AutorDto autor : livroDto.getAuthors()) {
                System.out.print(autor.getName() + "; ");
            }
        }
        System.out.println("\n-------------------------");
    }
}
