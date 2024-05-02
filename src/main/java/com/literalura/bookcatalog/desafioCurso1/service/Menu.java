package com.literalura.bookcatalog.desafioCurso1.service;

import com.literalura.bookcatalog.desafioCurso1.model.BrandDto;
import com.literalura.bookcatalog.desafioCurso1.model.ModelDto;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static String menuVehicleType(Scanner sc) {
        while (true) {
            System.out.println("Selecione a opção com o tipo de veículo que você quer buscar:");
            System.out.println("***********************************************");
            System.out.println("""
                    [1] carro
                    [2] moto
                    [3] caminhão
                    [0] Sair""");
            System.out.println("***********************************************");

            String op = sc.nextLine();
            switch (op) {
                case "1":
                    return "cars";
                case "2":
                    return "motorcycles";
                case "3":
                    return "trucks";
                case "0":
                    System.out.println("Finalizando programa...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public static Integer menuBrand(Scanner sc, List<BrandDto> brands) {

        while (true) {
            System.out.println("***********************************************");
            brands.stream()
                    .sorted(Comparator.comparing(BrandDto::code))
                    .forEach(b -> System.out.printf("Cod: %03d | %s\n", b.code(), b.brandName()));
            System.out.println("***********************************************");
            System.out.println("Digite o código da marca do veículo para consulta:");

            try {
                Integer choice = sc.nextInt();
                boolean validCode = brands.stream()
                        .anyMatch(b -> b.code().equals(choice));
                if (validCode) {
                    return choice;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Opção inválida.");
                sc.nextLine();
            }

        }
    }

    public static Integer menuModel(Scanner sc, List<ModelDto> models) {

        while (true) {
            System.out.println("***********************************************");
            models.stream()
                    .sorted(Comparator.comparing(ModelDto::code))
                    .forEach(m -> System.out.printf("Cod: %05d | %s\n", m.code(), m.modelName()));
            System.out.println("***********************************************");
            System.out.println("Digite um trecho do nome do veículo para consulta:");

            try {
                sc.nextLine();
                String choice = sc.nextLine();
                boolean validName = models.stream()
                        .anyMatch(m -> m.modelName().toUpperCase().contains(choice.toUpperCase()));
                if (validName) {
                    System.out.println("***********************************************");
                    models.stream()
                            .filter(m -> m.modelName().toUpperCase().contains(choice.toUpperCase()))
                            .sorted(Comparator.comparing(ModelDto::code))
                            .forEach(m -> System.out.printf("Cod: %05d | %s\n", m.code(), m.modelName()));
                    System.out.println("***********************************************");
                    System.out.println("Digite o código do nome do veículo para consulta:");
                    return sc.nextInt();
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Opção inválida.");
                sc.nextLine();
            }



        }
    }
}
