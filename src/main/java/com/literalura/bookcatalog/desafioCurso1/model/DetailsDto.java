package com.literalura.bookcatalog.desafioCurso1.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DetailsDto(Integer vehicleType,
                         @JsonAlias("price") String value,
                         String brand,
                         String model,
                         String modelYear,
                         String fuel) {
}

