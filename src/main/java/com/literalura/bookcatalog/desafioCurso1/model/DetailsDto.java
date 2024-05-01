package com.literalura.bookcatalog.desafioCurso1.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DetailsDto(@JsonAlias("price") String value,
                         @JsonAlias("fuel") String fuel) {
}
