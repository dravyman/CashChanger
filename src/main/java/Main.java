import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.IndexServlet;
import org.eclipse.jetty.server.Server;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(8081);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        servletContextHandler.setSessionHandler(new SessionHandler());
        servletContextHandler.setResourceBase("src/main/webapp");

        ServletHolder indexServletHolder = new ServletHolder(new IndexServlet());
        servletContextHandler.addServlet(indexServletHolder, "/indexServlet");

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
