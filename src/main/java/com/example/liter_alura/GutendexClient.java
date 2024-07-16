package com.example.liter_alura;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GutendexClient {

    private static final String API_URL = "https://gutendex.com/books";
    private final ObjectMapper objectMapper;

    public GutendexClient() {
        this.objectMapper = new ObjectMapper();
    }

    public BookResponse searchBooks(String query) {
        String url = API_URL + "?search=" + query;
        HttpGet request = new HttpGet(url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                return objectMapper.readValue(result, BookResponse.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
