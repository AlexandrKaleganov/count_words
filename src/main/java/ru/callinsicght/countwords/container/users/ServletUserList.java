package ru.callinsicght.countwords.container.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.callinsicght.countwords.model.User;
import ru.callinsicght.countwords.reposiroty.ExceptionNullMethod;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;
import ru.callinsicght.countwords.service.UserDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUserList extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ServletUserList.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/vievs/users/userlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getParameter("action");
        if (action.contains("findByIdUser")) {
            try {
                LOGGER.info("action = "  + action);
                req.setAttribute("user", UserDispatcher.getInstance().access(action, new User(Integer.valueOf(req.getParameter("us")))));
                req.getRequestDispatcher("WEB-INF/vievs/users/edit.jsp").forward(req, resp);
            } catch (IOException | ExceptionSuchObjectAlreadyIs | ExceptionNullMethod e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else if (action.contains("deleteUser")) {
            try {
                req.setAttribute("user", UserDispatcher.getInstance().access("deleteUser", new User(Integer.valueOf(req.getParameter("us")))));
            } catch (IOException | ExceptionSuchObjectAlreadyIs | ExceptionNullMethod e) {
                LOGGER.error(e.getMessage(), e);
            }
            try {
                req.getRequestDispatcher("WEB-INF/vievs/users/userlist.jsp").forward(req, resp);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            try {
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                try {
                    LOGGER.info("в сервле пришёл пользователь = " + req.getParameter("us"));
//                    тут приходит пользователь в json
                    writer.append(new ObjectMapper().writeValueAsString(UserDispatcher.getInstance().access(action,
                            new ObjectMapper().readValue(req.getParameter("us"), User.class))));
                } catch (IOException | ExceptionSuchObjectAlreadyIs | ExceptionNullMethod e) {
                    LOGGER.error(e.getMessage(), e);

                }
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
