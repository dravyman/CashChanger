package servlets.webServlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;

import authorization.DataAdapter;
import utils.Global;
import authorization.User;

public class IndexServlet extends CopyHtmlPageServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("#### Get to IndexServlet");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession(true);
        User user = (User) (session.getAttribute(Global.session_attr_currentUser));

        if (user != null && !user.getLogin().isEmpty()) {
            request.getRequestDispatcher("/info").forward(request, response); // TODO change on /account path
        } else {
            sendHtmlPage(response, "/webapp/html/login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("#### Post to IndexServlet");
        String userName = request.getParameter("username");

        HttpSession session = request.getSession(true);
        User currentUser = DataAdapter.getUser(userName);
        session.setAttribute(Global.session_attr_currentUser, currentUser);

        request.getRequestDispatcher("/account").forward(request, response);
    }
}
