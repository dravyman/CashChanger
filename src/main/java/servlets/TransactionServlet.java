package servlets;

import authorization.DataAdapter;
import authorization.User;
import exception.InfoException;
import exception.AlertType;
import utils.Global;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class TransactionServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println("#### Get to TransactionServlet");

            HttpSession session = request.getSession(true);
            User user = (User) (session.getAttribute(Global.session_attr_currentUser));

            if (user == null || user.getLogin().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.sendRedirect("/login");
                return;
            }
            JSONObject jsonObject = new JSONObject(new JSONTokener(request.getReader()));
            String type = jsonObject.getString("type");
            switch (type) {
                case "sendMoney":
                    String userTo = jsonObject.getString("userTo");
                    long amount = jsonObject.getLong("amount");
                    checkAccountCash(user, amount);
                    checkUserExists(userTo);
                    break;
            }
        } catch (InfoException ex) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.toJSONString());
        }
    }

    private void checkAccountCash(User user, long amount) throws InfoException {
        if (DataAdapter.getUser(user.getLogin()).getCurrentMoney() < amount) {
            throw new InfoException(AlertType.ERROR.name(), "Неудача", "Перевод средств не осуществлен", "Недостаточно средств");
        }
    }

    private void checkUserExists(String userTo) throws InfoException {
        if (DataAdapter.getUser(userTo) == null) {
            throw new InfoException(AlertType.ERROR.name(), "Неудача", "Перевод средств не осуществлен", "Пользователь не найден");
        }
    }
}
