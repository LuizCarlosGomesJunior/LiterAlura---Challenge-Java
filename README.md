#  LiterAlura – Challenge Java

Projeto desenvolvido como parte do **Challenge Back‑End Java da Alura**. A aplicação consome dados da API Gutendex (Project Gutenberg), armazena livros e autores em um banco de dados PostgreSQL e oferece um menu interativo no terminal para consulta.

---

##  Funcionalidades

- Buscar livros pelo título (API Gutendex)  
- Salvar livros e autores no banco de dados  
- Listar livros registrados  
- Listar autores registrados  
- Filtrar autores vivos em determinado ano  
- Filtrar livros por idioma  
- Interface via terminal (CLI)

---

##  Tecnologias utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- **PostgreSQL**  
- Jackson (JSON ↔ DTO)  
- HTTP Client (Java)  
- Maven

---

##  Estrutura do projeto

```bash
literalura/
├── src/
│   └── main/
│       ├── java/
│       │   └── br/com/alura/literalura/
│       │       ├── model/         # Entidades JPA: Livro, Autor
│       │       ├── dto/           # DTOs: ResultadoBusca, LivroDto, AutorDto
│       │       ├── repository/    # Interfaces dos repositórios JPA
│       │       ├── service/       # Consumo da API Gutendex + conversão de dados
│       │       ├── principal/     # Menu interativo (classe Principal)
│       │       └── LiteraluraApplication.java  # Classe principal do Spring Boot
│       └── resources/
│           └── application.properties  # Configuração do PostgreSQL
└── pom.xml                            # Gerenciador de dependências Maven
