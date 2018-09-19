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
            prepareAllInfoReplacments();
            sendHtmlPageWithReplace(response, "/webapp/html/allUsers.html", replacements);
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
