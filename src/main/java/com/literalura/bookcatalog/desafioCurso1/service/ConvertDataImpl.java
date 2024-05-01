package com.literalura.bookcatalog.desafioCurso1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConvertDataImpl implements ConvertData {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T mapData(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> mapDataToList(String json, Class<T> classe) throws JsonProcessingException {
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, classe));
    }
}
