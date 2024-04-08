package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import dao.AnalyticsDAO;
import model.LinkAnalytics;

public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shortURL = request.getParameter("shortURL");
        LinkAnalytics analytics = AnalyticsDAO.getAnalyticsData(shortURL);
        
        request.setAttribute("analytics", analytics);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
