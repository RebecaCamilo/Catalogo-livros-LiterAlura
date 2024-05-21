package com.literalura.bookcatalog.service;

import com.literalura.bookcatalog.model.dto.AuthorDto;
import com.literalura.bookcatalog.model.dto.BookDto;
import com.literalura.bookcatalog.model.dto.ResultDto;
import com.literalura.bookcatalog.provider.api.GutendexApi;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BookService {

    GutendexApi gutendexApi = new GutendexApi();
    DataConverter dataConverter = new DataConverterImpl();

    public void getBookByTitle(String title) {
        searchBookInGutendexApi(title);

    }

    public void searchBookInGutendexApi(String title) {
        HttpRequest req = gutendexApi.createRequestToGetBook(title);
        HttpResponse<String> res = gutendexApi.getResponse(req);

        ResultDto bookResp = dataConverter.obterDados(res.body(), ResultDto.class);
        if (!bookResp.results().isEmpty()) {
            System.out.println("-----------------------------------------");
            List<AuthorDto> author = bookResp.results().get(0).authors();
            System.out.println(author);
            System.out.println("-----------------------------------------");
            BookDto bookRecord = bookResp.results().get(0);
            System.out.println(bookRecord);
        } else {
            System.out.println("Título não encontrado");
        }

    }
}
