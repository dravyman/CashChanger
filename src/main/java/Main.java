import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.TransactionServlet;
import servlets.webServlets.AccountServlet;
import servlets.webServlets.IndexServlet;
import org.eclipse.jetty.server.Server;
import servlets.webServlets.AllInfoServlet;
import servlets.LogOutServlet;

import java.awt.*;
import java.net.URI;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(8081);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        servletContextHandler.setSessionHandler(new SessionHandler());
        servletContextHandler.setResourceBase("src/main/webapp");

        servletContextHandler.addServlet(new ServletHolder(new IndexServlet()), "/login");
        servletContextHandler.addServlet(new ServletHolder(new AllInfoServlet()), "/info");
        servletContextHandler.addServlet(new ServletHolder(new AccountServlet()), "/account");
        servletContextHandler.addServlet(new ServletHolder(new LogOutServlet()), "/logout");
        servletContextHandler.addServlet(new ServletHolder(new TransactionServlet()), "/transactions");

        server.setHandler(servletContextHandler);

        try {
            server.start();
            System.out.println("Host have started");
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:8081/login"));
            }
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
