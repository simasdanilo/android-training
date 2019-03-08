package com.simasdanilo.training;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BookApi {
    private static final String LOG_TAG =
            BookApi.class.getSimpleName();
    // Base URL for Books API.
    private static final String BOOK_BASE_URL =  "https://www.googleapis.com/books/v1/volumes?";
    // Parameter for the search string.
    private static final String QUERY_PARAM = "q";
    // Parameter that limits search results.
    private static final String MAX_RESULTS = "maxResults";
    // Parameter to filter by print type.
    private static final String PRINT_TYPE = "printType";

    private static HttpURLConnection getApiConnection(String query) throws IOException {
        HttpURLConnection apiConnection;

        Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, query)
                .appendQueryParameter(MAX_RESULTS, "10")
                .appendQueryParameter(PRINT_TYPE, "books")
                .build();

        URL requestURL = new URL(builtURI.toString());

        apiConnection = (HttpURLConnection) requestURL.openConnection();
        apiConnection.setRequestMethod("GET");

        return apiConnection;
    }

    private static BufferedReader getResponseReader(HttpURLConnection apiConnection) throws IOException {
        // Get the InputStream.
        InputStream inputStream = apiConnection.getInputStream();

        // Create a buffered reader from that input stream.
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        return reader;
    }

    private static String getResponseJSONString(BufferedReader responseReader) throws IOException {
        String responseJSONString = null;

        // Use a StringBuilder to hold the incoming response.
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = responseReader.readLine()) != null) {
            builder.append(line);
            // Since it's JSON, adding a newline isn't necessary (it won't
            // affect parsing) but it does make debugging a *lot* easier
            // if you print out the completed buffer for debugging.
            builder.append("\n");
        }

        if (builder.length() != 0) {
            // Stream is not empty, parse it.
            responseJSONString = builder.toString();
        }

        return responseJSONString;
    }

    private static void closeResponseReader(BufferedReader responseReader) {
        if (responseReader != null) {
            try {
                responseReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void disconnectApi(HttpURLConnection apiConnection) {
        if (apiConnection != null) {
            apiConnection.disconnect();
        }
    }

    public static String getBookInfo(String query){
        HttpURLConnection apiConnection = null;
        BufferedReader responseReader = null;
        String responseJSONString = null;

        try {
            apiConnection = getApiConnection(query);
            apiConnection.connect();

            responseReader = getResponseReader(apiConnection);

            responseJSONString = getResponseJSONString(responseReader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disconnectApi(apiConnection);
            closeResponseReader(responseReader);
        }

        Log.d(LOG_TAG, responseJSONString);

        return responseJSONString;
    }

}
