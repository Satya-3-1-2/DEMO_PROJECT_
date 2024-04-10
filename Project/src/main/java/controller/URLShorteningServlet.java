package controller;

import dao.URLDAO;
import model.ShortenedURL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shorten")
public class URLShorteningServlet extends HttpServlet {

    private URLDAO urlDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        urlDAO = new URLDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String longURL = request.getParameter("longURL");

        String shortURL = generateShortURL(longURL);

        ShortenedURL shortenedURL = new ShortenedURL(longURL, shortURL);
        urlDAO.addURL(shortenedURL);

        response.sendRedirect("dashboard.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("redirect".equals(action)) {
            String shortURL = request.getParameter("shortURL");
            
            
            if (shortURL != null) {
                response.sendRedirect(shortURL/* long URL corresponding to shortURL */);
            } else {
                request.setAttribute("error", "Short URL not found");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }


    private String generateShortURL(String longURL) {
        return "http://yourdomain.com/" + Math.random();
    }
}
