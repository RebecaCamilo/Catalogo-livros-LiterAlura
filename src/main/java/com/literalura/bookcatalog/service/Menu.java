package com.literalura.bookcatalog.service;

import com.literalura.bookcatalog.model.domain.Book;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Menu {
    Scanner sc = new Scanner(System.in);

    public String showMenu() {
            System.out.println("***********************************************");
            System.out.println("""
                    [1] Buscar livro pelo título
                    [2] Listar livros registrados
                    [3] Listar autores registrados
                    [4] Listar autores vivos em um ano específico
                    [5] Listar livros em um idioma específico
                    [0] Sair""");
            System.out.println("***********************************************");

            return sc.nextLine();
    }

    public String getBookByTitle() {
        System.out.println("Informe o nome do livro que deseja procurar");
        return sc.nextLine();
    }

    public void showBookInfo(Book book) {
        System.out.println("******************** LIVRO ********************");
        System.out.println("Título: " + book.getTitle());
        System.out.println("Autor: " + book.getAuthor().getName());
        System.out.println("Idioma: " + book.getLanguages());
        System.out.println("Número de downloads: " + book.getDownloads());
        System.out.println("***********************************************");
        System.out.println();
    }

}
