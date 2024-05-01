package com.literalura.bookcatalog.desafioCurso1.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ConvertData {

    <T> T mapDataToObject(String json, Class<T> classType) throws JsonProcessingException;
    <T> List<T> mapDataToList(String json, Class<T> classType) throws JsonProcessingException;
}
