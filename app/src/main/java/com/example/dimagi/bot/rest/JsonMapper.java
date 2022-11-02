package com.example.dimagi.bot.rest;

import com.example.dimagi.bot.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class JsonMapper {

    public JsonMapper(){}

    public JsonMapper(String json) throws IOException {
        fromJson(json);
    }

    public void fromJson(String json) throws IOException {
        ObjectReader reader = Utils.getObjectReaderForUpdating(this);
        reader.readValue(json);
    }

    public static <T> T listFromJson(String json, TypeReference<T> ref) throws IOException {
        ObjectMapper mapper = Utils.getObjectMapper();
        return mapper.readValue(json, ref);
    }

    public JSONObject toJson() throws JsonProcessingException, JSONException {
        return new JSONObject(Utils.getObjectMapper().writeValueAsString(this));
    }

}
