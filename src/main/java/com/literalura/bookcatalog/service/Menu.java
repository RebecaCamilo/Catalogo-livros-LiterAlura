package com.literalura.bookcatalog.service;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    BookService bookService = new BookService();

    public void showMenu() {
        while (true) {
            System.out.println("***********************************************");
            System.out.println("""
                    [1] Buscar livro pelo título
                    [2] Buscar livros por autor
                    [3] Listar autores registrados
                    [4] Listar autores vivos em um ano específico
                    [5] Listar livros em um idioma específico
                    [0] Sair""");
            System.out.println("***********************************************");

            String op = sc.nextLine();
            switch (op) {
                case "1":
                    getBookByTitle();
                    break;
                case "2":
                    System.out.println("Digitou 2");
                    break;
                case "3":
                    System.out.println("Digitou 3");
                    break;
                case "4":
                    System.out.println("Digitou 4");
                    break;
                case "5":
                    System.out.println("Digitou 5");
                    break;
                case "0":
                    System.out.println("Finalizando programa...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    // Salvar livro no db
    private void getBookByTitle() {
        System.out.println("Informe o nome do livro que deseja procurar");
        String title = sc.nextLine();

        bookService.getBookByTitle(title);
    }

    // Salvar autor no db

    // Listar autores registrados no db

    // Listar autores vivos em um ano específico no db

    // Listar livros em um idioma específico no db

}
