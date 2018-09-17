package servlets;

import servlets.webServlets.CopyHtmlPageServlet;
import utils.Global;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutServlet extends CopyHtmlPageServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("#### Post to LogOutServlet");
        HttpSession session = request.getSession(true);
        session.setAttribute(Global.session_attr_currentUser, null);
        response.sendRedirect("/login");
    }
}
