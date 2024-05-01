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
		String type = Menu.menuVehicleType(sc);

		String jsonBrands = ApiConsumer.getData("https://parallelum.com.br/fipe/api/v2/" + type + "/brands");
		List<BrandDto> brands = converter.mapDataToList(jsonBrands, BrandDto.class);
		Integer brandCode = Menu.menuBrand(sc, brands);
		String brandName = brands.stream()
				.filter(brand -> brand.code().equals(brandCode))
				.map(BrandDto::brandName)
				.findFirst().get();

		String jsonModels = ApiConsumer.getData("https://parallelum.com.br/fipe/api/v2/" + type + "/brands/" + brandCode + "/models");
		List<ModelDto> models = converter.mapDataToList(jsonModels, ModelDto.class);
		Integer modelCode = Menu.menuModel(sc, models);
		String modelName = models.stream()
				.filter(m -> m.code().equals(modelCode))
				.map(ModelDto::modelName)
				.findFirst().get();

		String jsonYearsModel = ApiConsumer.getData("https://parallelum.com.br/fipe/api/v2/" + type + "/brands/" + brandCode + "/models/" + modelCode + "/years");
		List<YearsModelDto> yearsModel = converter.mapDataToList(jsonYearsModel, YearsModelDto.class);

		List<Vehicle> vehicles = new ArrayList<>();
		String jsonValue;
		DetailsDto valueDto = null;
		Vehicle vehicle;
		for (int i = 0; i < yearsModel.size(); i++) {
			try {
				jsonValue = ApiConsumer.getData("https://parallelum.com.br/fipe/api/v2/" + type + "/brands/" + brandCode + "/models/" + modelCode + "/years/" + yearsModel.get(i).code());
				valueDto = converter.mapDataToObject(jsonValue, DetailsDto.class);
			} catch (NullPointerException e) {
				System.out.println("Não foi possível encontrar as informações desejadas.");
			}
			vehicle = new Vehicle(valueDto, "teste", "teste", yearsModel.get(i));
			System.out.println(vehicle);
			vehicles.add(vehicle);
		}

	}
}
