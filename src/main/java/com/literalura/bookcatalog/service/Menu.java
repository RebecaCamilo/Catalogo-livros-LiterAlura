package com.literalura.bookcatalog.service;

public class Menu {

    public void showMenu() {
        System.out.println("""
                [1] Buscar livro pelo título
                [2] Buscar livros por autor
                [3] Listar autores registrados
                [4] Listar autores vivos em um ano específico
                [5] Listar livros em um idioma específico
                [0] Sair
                """);
    }
}
