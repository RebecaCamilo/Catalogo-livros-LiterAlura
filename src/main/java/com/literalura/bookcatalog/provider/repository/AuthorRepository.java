package com.literalura.bookcatalog.provider.repository;

import com.literalura.bookcatalog.model.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByName(String name);

    Author findByName(String name);
}
