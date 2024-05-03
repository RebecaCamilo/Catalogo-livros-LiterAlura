package com.literalura.bookcatalog.desafioCurso1.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GenericInfoDto(Integer code,
                             @JsonAlias("name") String description) {
}
