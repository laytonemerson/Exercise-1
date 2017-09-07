package edu.matc.controller;

import edu.matc.persistence.UserData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String searchType = req.getParameter("searchType");
        String searchTerm = req.getParameter("searchTerm");

        req.removeAttribute("noTermSent");

        if (searchTerm.equals("") & !searchType.equals("all_users")) {
            boolean noTermSent = true;
            req.setAttribute("noTermSent", noTermSent);
        } else {
            UserData userData = new UserData();
            req.setAttribute("users", userData.userSearch(searchType, searchTerm));
          }

        req.setAttribute("title", "User Search Results");
        String url = "/results.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
