package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import model.User;
import util.PasswordHasher;
import dao.UserDAO;

public class AuthenticationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = new User(username, password);
            if (UserDAO.createUser(user)) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } else if ("login".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = UserDAO.getUserByUsername(username);
            if (user != null && PasswordHasher.checkPassword(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        }
    }
}
