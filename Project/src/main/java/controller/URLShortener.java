package controller;

import java.util.HashMap;
import java.util.Map;

public class URLShortener {
    private static Map<String, String> urlMap = new HashMap<>();

    public static synchronized String shortenURL(String longURL) {
        String shortURL = generateShortURL(longURL);
        urlMap.put(shortURL, longURL);
        return shortURL;
    }

    public static synchronized String getLongURL(String shortURL) {
        return urlMap.get(shortURL);
    }

    private static String generateShortURL(String longURL) {
        int hashCode = longURL.hashCode();
        
        String hashString = String.valueOf(Math.abs(hashCode));
        
        String shortURL = "http://your_domain.com/" + hashString;
        
        return shortURL;
    }
}
