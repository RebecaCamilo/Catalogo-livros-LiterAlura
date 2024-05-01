package com.literalura.bookcatalog.desafioCurso1.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ConvertData {

    <T> T mapData(String json, Class<T> classe);
    <T> List<T> mapDataToList(String json, Class<T> classe) throws JsonProcessingException;
}
