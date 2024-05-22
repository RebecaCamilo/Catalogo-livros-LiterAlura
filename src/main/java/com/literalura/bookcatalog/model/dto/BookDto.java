package com.literalura.bookcatalog.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDto(@JsonAlias String title,
                      @JsonAlias AuthorDto author,
                      @JsonAlias String language,
                      @JsonAlias("download_count") Integer downloads) {

}
