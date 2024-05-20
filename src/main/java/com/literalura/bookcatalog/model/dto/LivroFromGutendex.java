package com.literalura.bookcatalog.model.dto;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroFromGutendex(@JsonAlias("Title") String titulo,
                                @JsonAlias("totalSeasons") String autor,
                                @JsonAlias("imdbRating") String idioma,
                                @JsonAlias("imdbRating") Long qtdDownloads) {
}
