package com.example.liter_alura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT COUNT(b) FROM Book b JOIN b.languages l WHERE l = :language")
    long countBooksByLanguage(@Param("language") String language);
}
