package com.example.liter_alura;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {
    @JsonAlias("results")
    private Book[] books;

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BookResponse{books=[");
        if (books != null) {
            for (int i = 0; i < books.length; i++) {
                sb.append(books[i].toString());
                if (i < books.length - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
