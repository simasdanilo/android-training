package com.simasdanilo.training;

import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONObject;

public class BookService {

    public static Response findBook(String query){
        try {
            String responseJSONString = BookApi.getBookInfo(query);

            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(responseJSONString);
            // Get the JSONArray of book items.
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            // Initialize iterator and results fields.
            int i = 0;
            String title = null;
            String authors = null;

            // Look for results in the items array, exiting
            // when both the title and author
            // are found or when all items have been checked.
            while (i < itemsArray.length() &&
                    (authors == null && title == null)) {
                // Get the current item information.
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Move to the next item.
                i++;
            }

            // If both are found, display the result.
            boolean foundBook = title != null && authors != null;
            if (foundBook) {
                Book book = new Book(title,authors);
                return Response.success(book);
            } else {
                return Response.failed("No book found!");
            }

        } catch (Exception e) {
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
            return Response.failed("Error unexpected!");
        }
    }

}
