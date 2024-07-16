package com.example.liter_alura;

import com.example.liter_alura.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND (a.deathYear > :year OR a.deathYear = 0)")
    List<Author> findAuthorsAliveInYear(@Param("year") int year);
}
