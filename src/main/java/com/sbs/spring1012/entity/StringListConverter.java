package com.sbs.spring1012.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    //db에 json형식으로 저장되게하는 메서드
    @Override
    public String convertToDatabaseColumn(List dataList){
        try {
            return mapper.writeValueAsString(dataList);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    //json형식으로 저장된 문자열을 List로 변환하는 메서드
    @Override
    public List<String> convertToEntityAttribute(String data){
        try {
            return mapper.readValue(data,List.class);
        }catch (JsonProcessingException e){
            throw  new RuntimeException(e);
        }
    }
}
