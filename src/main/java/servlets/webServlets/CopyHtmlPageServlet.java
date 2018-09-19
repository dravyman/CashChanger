package servlets.webServlets;

import authorization.User;

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
                wri.write(s + "\n");
                s = bufReader.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


    void sendHtmlPageWithReplace(HttpServletResponse response, String pagePath) {
        sendHtmlPageWithReplace(response, pagePath, replacements);
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
                builder.append(s).append("\n");
            } while (s != null);
            replacements.forEach((x, y) -> localReplace(builder, x, y));
            wri.write(builder.toString() + "\n");
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

    protected void prepareReplacements() {
        replacements = new HashMap<>();
        replacements.put("<style></style>", getStyle("/webapp/css/style.css"));
    }

    private String getStyle(String cssPath) {
        StringBuilder builder = new StringBuilder("<style>\n");
        try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(cssPath)))) {
            String s;
            do {
                s = bufReader.readLine();
                if (s == null) break;
                builder.append(s).append("\n");
            } while (s != null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        builder.append("</style>");
        return builder.toString();
    }
}
