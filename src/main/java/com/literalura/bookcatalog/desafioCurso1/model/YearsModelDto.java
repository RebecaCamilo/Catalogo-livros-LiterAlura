package com.literalura.bookcatalog.desafioCurso1.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record YearsModelDto(@JsonAlias("code") String code,
                            @JsonAlias("name") String yearsModelName) {
}
