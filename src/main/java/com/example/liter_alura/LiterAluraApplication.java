package com.example.liter_alura;

import com.example.liter_alura.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private GutendexClient gutendexClient;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;

	private final List<Book> books;

	public LiterAluraApplication() {
		this.books = new ArrayList<>();
	}

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Menu:");
			System.out.println("1. Buscar livro por título");
			System.out.println("2. Listar todos os livros buscados");
			System.out.println("3. Listar todos os autores");
			System.out.println("4. Listar autores vivos em determinado ano");
			System.out.println("5. Contar livros por idioma");
			System.out.println("6. Sair");
			System.out.print("Selecione uma opção: ");
			int option = scanner.nextInt();
			scanner.nextLine();  // Consumir a nova linha

			switch (option) {
				case 1:
					searchBookByTitle(scanner);
					break;
				case 2:
					listAllBooks();
					break;
				case 3:
					listAllAuthors();
					break;
				case 4:
					listLivingAuthorsByYear(scanner);
					break;
				case 5:
					countBooksByLanguage(scanner);
					break;
				case 6:
					System.out.println("Saindo...");
					return;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	private void searchBookByTitle(Scanner scanner) {
		System.out.print("Digite o título do livro: ");
		String title = scanner.nextLine().replace(" ", "+");
		BookResponse response = gutendexClient.searchBooks(title);
		if (response != null && response.getBooks() != null && response.getBooks().length > 0) {
			Book book = response.getBooks()[0];

			Author author = book.getAuthors()[0];
			author = authorRepository.save(author);

			book.setAuthor(author);
			book.setApiId(book.getApiId());
			book = bookRepository.save(book);
			books.add(book);
			System.out.println("Livro encontrado: ");
			System.out.println(book);
		} else {
			System.out.println("Nenhum livro encontrado com esse título.");
		}
	}

	private void listAllBooks() {
		if (books.isEmpty()) {
			System.out.println("Nenhum livro buscado até o momento.");
		} else {
			System.out.println("Lista de todos os livros buscados:");
			for (Book book : books) {
				System.out.println(book);
			}
		}
	}

	private void listAllAuthors() {
		if (books.isEmpty()) {
			System.out.println("Nenhum livro buscado até o momento.");
		} else {
			System.out.println("Lista de todos os autores:");
			for (Book book : books) {
				if (book.getAuthor() != null) {
					System.out.println(book.getAuthor());
				}
			}
		}
	}

	private void listLivingAuthorsByYear(Scanner scanner) {
		System.out.print("Digite o ano: ");
		int year = scanner.nextInt();
		scanner.nextLine();

		List<Author> authors = authorRepository.findAuthorsAliveInYear(year);
		if (authors.isEmpty()) {
			System.out.println("Nenhum autor encontrado vivo no ano " + year);
		} else {
			System.out.println("Autores vivos no ano " + year + ":");
			for (Author author : authors) {
				System.out.println(author);
			}
		}
	}

	private void countBooksByLanguage(Scanner scanner) {
		System.out.print("Digite o idioma: ");
		String language = scanner.nextLine();

		long count = bookRepository.countBooksByLanguage(language);
		System.out.println("Número de livros no idioma '" + language + "': " + count);
	}
}
