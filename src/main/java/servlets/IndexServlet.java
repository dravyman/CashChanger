package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import utils.Global;
import authorization.User;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Get to IndexServlet");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession(true);
        User user = (User) (session.getAttribute(Global.session_attr_currentUser));

        System.out.println(session.getServletContext().getRealPath("/"));

        if (user != null && !user.getLogin().isEmpty()) {
            request.getRequestDispatcher("jsp/main.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }


        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("<h1>Hello world</h1>");
    }
}
