package com.literalura.bookcatalog.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookRecord(@JsonAlias String title,
//                         @JsonAlias List<Author> authors,
//                         @JsonAlias List<String> languages,
                         @JsonAlias Integer download_count) {

}
