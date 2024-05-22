package com.literalura.bookcatalog.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOOKS")
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Author author;
    private String languages;
    private Integer downloads;
}
