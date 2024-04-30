package com.literalura.bookcatalog.curso1.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(@JsonAlias("Season") Integer numeroTeporada,
                             @JsonAlias("Episodes") List<DadosEpisodio> episodios) {
}
