package org.contoso.messageclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static <T> T parseJsonToObject(String json, Class<T> clazz) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, clazz);
    }

    public static String parseObjectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
