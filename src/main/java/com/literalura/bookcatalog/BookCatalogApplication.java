package com.literalura.bookcatalog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.literalura.bookcatalog.desafioCurso1.conexao.api.ApiConsumer;
import com.literalura.bookcatalog.desafioCurso1.model.*;
import com.literalura.bookcatalog.desafioCurso1.service.ConvertData;
import com.literalura.bookcatalog.desafioCurso1.service.ConvertDataImpl;
import com.literalura.bookcatalog.desafioCurso1.service.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BookCatalogApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws JsonProcessingException {
		Scanner sc = new Scanner(System.in);
		ConvertData converter = new ConvertDataImpl();

		System.out.println("Seja bem vinde ao consultor da FIPE!");
		String type = Menu.showMenuVehicleType(sc);

		String jsonBrands = ApiConsumer.getData("https://parallelum.com.br/fipe/api/v2/" + type + "/brands");
		List<GenericInfoDto> brands = converter.mapDataToList(jsonBrands, GenericInfoDto.class);
		Integer brandCode = Menu.showMenuBrand(sc, brands);

		String jsonModels = ApiConsumer.getData("https://parallelum.com.br/fipe/api/v2/" + type + "/brands/" + brandCode + "/models");
		List<GenericInfoDto> models = converter.mapDataToList(jsonModels, GenericInfoDto.class);
		Integer modelCode = Menu.showMenuModel(sc, models);

		String jsonYearsModel = ApiConsumer.getData("https://parallelum.com.br/fipe/api/v2/" + type + "/brands/" + brandCode + "/models/" + modelCode + "/years");
		List<GenericInfoDto> yearsModel = converter.mapDataToList(jsonYearsModel, GenericInfoDto.class);

		List<Vehicle> vehicles = new ArrayList<>();
		String jsonValue;
		VehicleDetailsDto valueDto = null;
		for (GenericInfoDto yearModel : yearsModel) {
			try {
				jsonValue = ApiConsumer.getData("https://parallelum.com.br/fipe/api/v2/" + type + "/brands/" + brandCode + "/models/" + modelCode + "/years/" + yearModel.code());
				valueDto = converter.mapDataToObject(jsonValue, VehicleDetailsDto.class);
			} catch (NullPointerException e) {
				System.out.println("Não foi possível encontrar as informações desejadas.");
			}
			vehicles.add(new Vehicle(valueDto));
		}

		vehicles.forEach(System.out::println);

	}
}
