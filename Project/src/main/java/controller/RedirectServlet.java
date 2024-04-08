package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RedirectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shortURL = request.getPathInfo().substring(1); // Get the short URL from the request
        String longURL = URLShortener.getLongURL(shortURL);
        if (longURL != null) {
            response.sendRedirect(longURL);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
