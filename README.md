# Liter Alura 

Este projeto é uma aplicação Java Spring Boot que interage com a API Gutendex para gerenciar um catálogo de livros. A aplicação permite buscar livros por título, listar todos os livros e autores, e realizar consultas avançadas sobre idiomas e autores vivos.

## Funcionalidades
* **Busca de Livros:** Permite buscar livros por título usando a API Gutendex e salvar no banco de dados.
* **Listagem de Livros:** Exibe todos os livros que foram buscados e salvos.
* **Listagem de Autores:** Mostra todos os autores dos livros salvos.
* **Autores vivos em um ano:** Lista autores que estavam vivos em um ano específico fornecido pelo usuário.
* **Contagem de Livros por Idioma:** Mostra a quantidade de livros em um idioma específico.

## Tecnologias
*    **Java:** Linguagem de programação principal.
*    **Spring Boot:** Framework para desenvolvimento da aplicação.
*    **Spring Data JPA:** Para interações com o banco de dados.
*    **PostgreSQL:** Banco de dados relacional.
*    **Gutendex API:** API externa para obter dados sobre livros.
*    **Apache HttpClient:** Para fazer requisições HTTP.
*    **Jackson:** Biblioteca para manipulação de JSON.