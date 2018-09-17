import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.webServlets.IndexServlet;
import org.eclipse.jetty.server.Server;
import servlets.webServlets.AllInfoServlet;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(8081);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        servletContextHandler.setSessionHandler(new SessionHandler());
        servletContextHandler.setResourceBase("src/main/webapp");

        servletContextHandler.addServlet(new ServletHolder(new IndexServlet()), "/login");
        servletContextHandler.addServlet(new ServletHolder(new AllInfoServlet()), "/info");

        server.setHandler(servletContextHandler);

        try {
            server.start();
            System.out.println("Host have started");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
