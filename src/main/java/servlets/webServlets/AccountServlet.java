package servlets.webServlets;

import authorization.User;
import utils.Global;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountServlet extends CopyHtmlPageServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("#### Get to AccountServlet");

        HttpSession session = request.getSession(true);
        User user = (User) (session.getAttribute(Global.session_attr_currentUser));

        if (user == null || user.getLogin().isEmpty()) {
            request.getRequestDispatcher("/login").forward(request, response);
        } else {
            prepareReplacements(user);
            sendHtmlPageWithReplace(response, "/webapp/html/account.html", replacements);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("#### Post to AccountServlet");

        HttpSession session = request.getSession(true);
        User user = (User) (session.getAttribute(Global.session_attr_currentUser));

        if (user == null || user.getLogin().isEmpty()) {
            response.sendRedirect("/login");
        } else {
            response.getWriter().write(user.toJSONString());
        }

    }

    protected void prepareReplacements(User user) {
        super.prepareReplacements();
        replacements.put("#replaceMe1", "\"" + user.getLogin() + "\"");
        replacements.put("#replaceMe2", String.valueOf(user.getCurrentMoney()));
    }
}
