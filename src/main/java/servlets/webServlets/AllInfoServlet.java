package servlets.webServlets;

import authorization.DataAdapter;
import authorization.User;
import utils.Global;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AllInfoServlet extends CopyHtmlPageServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("#### Get to AllInfoServlet");

        HttpSession session = request.getSession(true);
        User user = (User) (session.getAttribute(Global.session_attr_currentUser));

        if (user == null || user.getLogin().isEmpty()) {
            request.getRequestDispatcher("/login").forward(request, response);
        } else {
            prepareAllInfoReplacments();
            sendHtmlPageWithReplace(response, "/webapp/html/allUsers.html", replacements);
        }
    }

    private void prepareAllInfoReplacments() {
        StringBuilder allUsers = new StringBuilder("<ol id=\"allUsers\">");
        DataAdapter.getAllUser().forEach((login, userInfo) -> {
            String userInfoLine = "<li>Login: " + login + " : " + userInfo.getCurrentMoney() + "</li>";
            allUsers.append(userInfoLine);
        });
        allUsers.append("</ol>");
        replacements.put("<ol id=\"allUsers\"></ol>", allUsers.toString());
    }
}
