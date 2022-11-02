package com.example.dimagi.bot.utils;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by tahir on 13/5/17.
 */

public class UrlBuilder {

    public static final String API_COMMANDS = "/commands";
    public static final String API_EXECUTE_COMMAND = API_COMMANDS + "/execute";

    private String uri;

    private ArrayList<String> parameters;
    private ArrayList<String> sections;

    private boolean isRest;

    public UrlBuilder(String uri) {
        this(uri,true);
    }

    public UrlBuilder(String uri, boolean isRest){
        this.uri = uri;
        parameters = new ArrayList<>();
        this.isRest = isRest;
    }

    public UrlBuilder addParameters(String name, String value) {
        parameters.add(name + "=" + value);
        return this;
    }

    public UrlBuilder addSearchCriteria(String searchCriteriaString) {
        String[] searchCriteria = searchCriteriaString.split("&");
        if (searchCriteria.length > 0) {
            for (String criterion : searchCriteria) {
                parameters.add(criterion);
            }
        }
        return this;
    }

    public UrlBuilder addSection(String section) {
        if (sections == null) {
            sections = new ArrayList<>();
        }
        sections.add(section);
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append(UrlUtils.BASE_URL);
        builder.append(uri);
        if (sections != null && !sections.isEmpty()) {
            for (String section : sections) {
                builder.append("/").append(section.replaceAll("/", ""));
            }
        }
        if (parameters != null && !parameters.isEmpty()) {
            builder.append("?").append(TextUtils.join("&", parameters));
        }
        return builder.toString();
    }


}
