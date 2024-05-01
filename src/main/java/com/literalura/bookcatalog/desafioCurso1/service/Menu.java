package com.literalura.bookcatalog.desafioCurso1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.literalura.bookcatalog.desafioCurso1.model.BrandDto;

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

    public static Integer menuBrand(Scanner sc, ConvertData converter, String json) throws JsonProcessingException {
        List<BrandDto> brands = converter.mapDataToList(json, BrandDto.class);

        while (true) {
            System.out.println("Digite o código da marca de veículo que deseja buscar:");
            System.out.println("***********************************************");
            brands.stream()
                    .sorted(Comparator.comparing(BrandDto::code))
                    .forEach(b -> System.out.printf("Cod: %03d | %s\n", b.code(), b.brandName()));
            System.out.println("***********************************************");

            try {
                Integer escolha = sc.nextInt();
                boolean codigoValido = brands.stream()
                        .anyMatch(b -> b.code().equals(escolha));
                if (codigoValido) {
                    return escolha;
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
