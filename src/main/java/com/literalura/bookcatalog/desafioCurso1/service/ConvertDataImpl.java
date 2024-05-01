package com.literalura.bookcatalog.desafioCurso1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConvertDataImpl implements ConvertData {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T mapDataToObject(String json, Class<T> classType) {
        try {
            return mapper.readValue(json, classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> mapDataToList(String json, Class<T> classType) throws JsonProcessingException {
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, classType));
    }

}
