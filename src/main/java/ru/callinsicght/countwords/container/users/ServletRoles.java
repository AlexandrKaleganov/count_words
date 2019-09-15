package ru.callinsicght.countwords.container.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.callinsicght.countwords.model.Roles;
import ru.callinsicght.countwords.reposiroty.ExceptionNullMethod;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;
import ru.callinsicght.countwords.service.RoleDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * пока только получает список ролей, можно добавить добавление редактирование и удаление ролей
 */
public class ServletRoles extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ServletUserList.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(new ObjectMapper().writeValueAsString(RoleDispatcher.getInstance().access("findAllRoles",
                    new ObjectMapper().readValue("{\"id\":\"1\", \"name\":\"ADMIN\"}", Roles.class))));
            writer.flush();
        } catch (ExceptionSuchObjectAlreadyIs | ExceptionNullMethod e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
