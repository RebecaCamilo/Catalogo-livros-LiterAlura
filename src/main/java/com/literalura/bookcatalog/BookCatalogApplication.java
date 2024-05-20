package com.literalura.bookcatalog;

import com.literalura.bookcatalog.model.dto.BookRecord;
import com.literalura.bookcatalog.provider.api.GutendexApi;
import com.literalura.bookcatalog.service.DataConverter;
import com.literalura.bookcatalog.service.DataConverterImpl;
import com.literalura.bookcatalog.service.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

@SpringBootApplication
public class BookCatalogApplication implements CommandLineRunner {

	Scanner scan = new Scanner(System.in);
	GutendexApi gutendexApi = new GutendexApi();
	DataConverter dataConverter = new DataConverterImpl();

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var menu = new Menu();
		menu.showMenu();
		var title = "dom casmurro"; //scan.nextLine();
		System.out.println(title);

		HttpRequest req = gutendexApi.createRequestToGetBook(title);
		HttpResponse<String> res = gutendexApi.getResponse(req);

		System.out.println(res.body());

		System.out.println("AGORA AQUI");

		BookRecord book = dataConverter.obterDados(res.body(), BookRecord.class);
		System.out.println(book);

	}

}
