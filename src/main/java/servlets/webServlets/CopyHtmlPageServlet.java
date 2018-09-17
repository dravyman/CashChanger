package servlets.webServlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public abstract class CopyHtmlPageServlet extends HttpServlet {

    Map<String, String> replacements = new HashMap<>();

    void sendHtmlPage(HttpServletResponse response, String pagePath) {
        try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(pagePath)))) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter wri = response.getWriter();
            String s = bufReader.readLine();
            while (s != null) {
                wri.write(s);
                s = bufReader.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    void sendHtmlPageWithReplace(HttpServletResponse response, String pagePath, Map<String, String> replacements) {
        try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(pagePath)))) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter wri = response.getWriter();
            String s;
            StringBuilder builder = new StringBuilder();
            do {
                s = bufReader.readLine();
                if (s == null) break;
                builder.append(s);
            } while (s != null);
            replacements.forEach((x, y) -> localReplace(builder, x, y));
            wri.write(builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void localReplace(StringBuilder builder, String x, String y) {
        while (builder.indexOf(x) >= 0) {
            int start = builder.indexOf(x);
            builder.replace(start, start + x.length(), y);
        }
    }
}
