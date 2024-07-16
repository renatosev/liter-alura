package com.example.liter_alura;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    @JsonProperty("results")
    private Book[] results;

    public Book[] getResults() {
        return results;
    }

    public void setResults(Book[] results) {
        this.results = results;
    }
}
