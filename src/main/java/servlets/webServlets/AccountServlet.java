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
            prepareAccpontReplacements(user);
            sendHtmlPageWithReplace(response, "/webapp/html/account.html", replacements);
        }
    }

    private void prepareAccpontReplacements(User user) {
        replacements.put("<replace id=\"1\"></replace>", user.getLogin());
        replacements.put("<replace id=\"2\"></replace>", String.valueOf(user.getCurrentMoney()));
    }
}
