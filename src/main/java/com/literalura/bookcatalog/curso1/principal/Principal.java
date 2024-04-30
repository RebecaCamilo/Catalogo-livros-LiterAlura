package com.literalura.bookcatalog.curso1.principal;

import com.literalura.bookcatalog.curso1.model.DadosEpisodio;
import com.literalura.bookcatalog.curso1.model.DadosSerie;
import com.literalura.bookcatalog.curso1.model.DadosTemporada;
import com.literalura.bookcatalog.curso1.model.Episodio;
import com.literalura.bookcatalog.curso1.service.ConsumoApi;
import com.literalura.bookcatalog.curso1.service.ConverteDados;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=7057b5f1";

    public void exibeMenu() {
        System.out.println("Digite o nome da série para busca:");
        var nomeSerie = sc.nextLine();

        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO  + nomeSerie.replace(" ", "+")
                    + "&season=" + i + "&apikey=7057b5f1");
            DadosTemporada dadosTemporadaGeral = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporadaGeral);
        }
        temporadas.forEach(System.out::println);

        temporadas.forEach(t -> t.episodios()
                .forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                        .flatMap(t -> t.episodios().stream())
                        .collect(Collectors.toList());

        System.out.println("\nTop 5 episodios");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numeroTeporada(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("A partir de que ano você deseja ver os episódios?");
        var ano = sc.nextInt();
        sc.nextLine(); //limpar o buffer

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                " Episódio: " + e.getTitulo() +
                                " Data de lançamento: " + e.getDataLancamento().format(formatador)
                ));
    }
}
