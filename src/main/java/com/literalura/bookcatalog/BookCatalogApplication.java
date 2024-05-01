package com.literalura.bookcatalog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.literalura.bookcatalog.desafioCurso1.conexao.api.ApiConsumer;
import com.literalura.bookcatalog.desafioCurso1.service.ConvertData;
import com.literalura.bookcatalog.desafioCurso1.service.ConvertDataImpl;
import com.literalura.bookcatalog.desafioCurso1.service.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BookCatalogApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws JsonProcessingException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Seja bem vinde ao consultor da FIPE!");

		String type = Menu.menuVehicleType(sc);

		String jsonBrands = ApiConsumer.getData("https://parallelum.com.br/fipe/api/v2/" + type + "/brands");

		System.out.println(jsonBrands);

		ConvertData converter = new ConvertDataImpl();

		Integer brandCode = Menu.menuBrand(sc, converter, jsonBrands);

		String jsonModels = ApiConsumer.getData("https://parallelum.com.br/fipe/api/v2/" + type + "/brands/" + brandCode + "/models");
		System.out.println(jsonModels);



	}
}
