package com.literalura.bookcatalog.desafioCurso1.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BrandDto(@JsonAlias("code") Integer code,
                       @JsonAlias("name") String brandName) {
}
