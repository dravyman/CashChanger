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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            System.out.println("#### Post to TransactionServlet");

            HttpSession session = request.getSession(true);
            User user = (User) (session.getAttribute(Global.session_attr_currentUser));

            if (user == null || user.getLogin().isEmpty()) {
                response.sendRedirect("/login");
                return;
            }
            JSONObject jsonObject = new JSONObject(new JSONTokener(request.getReader()));
            String type = jsonObject.getString("type");
            switch (type) {
                case "sendMoney":
                    String userTo = jsonObject.getString("userTo");
                    long amount = jsonObject.getLong("amount");
                    InfoException resModal = sendMoney(user.getLogin(), userTo, amount);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(resModal.toJSONString());
                    break;
            }
        } catch (InfoException ex) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.toJSONString());
        }
    }

    private InfoException sendMoney(String userFrom, String userTo, long amount) throws InfoException {
        checkAmount(amount);
        checkAccountCash(userFrom, amount);
        checkUserExists(userTo);
        DataAdapter.getUser(userFrom).decreaseCash(amount);
        DataAdapter.getUser(userTo).increaseCash(amount);
        return new InfoException(AlertType.INFORMATION.name(), "Успех", "Перевод средств осуществлен успешно", "Вы перевели сумму = " + amount);
    }

    private void checkAmount(long amount) throws InfoException {
        if (amount <= 0) {
            throw new InfoException(AlertType.ERROR.name(), "Неудача", "Перевод средств не осуществлен", "Списание должно быть положительным");
        }
    }

    private void checkAccountCash(String user, long amount) throws InfoException {
        if (DataAdapter.getUser(user).getCurrentMoney() < amount) {
            throw new InfoException(AlertType.ERROR.name(), "Неудача", "Перевод средств не осуществлен", "Недостаточно средств");
        }
    }

    private void checkUserExists(String userTo) throws InfoException {
        if (DataAdapter.getUser(userTo) == null) {
            throw new InfoException(AlertType.ERROR.name(), "Неудача", "Перевод средств не осуществлен", "Пользователь не найден");
        }
    }
}
