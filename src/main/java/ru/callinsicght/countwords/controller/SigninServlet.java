package ru.callinsicght.countwords.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import ru.callinsicght.countwords.model.User;
import ru.callinsicght.countwords.reposiroty.ExceptionNullMethod;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;
import ru.callinsicght.countwords.service.UserDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.log4j.Logger.getLogger;
@Controller
public class SigninServlet extends HttpServlet {
    private final static Logger LOGGER = getLogger(SigninServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = null;
            User temp = new ObjectMapper().readValue(String.format("{\"login\":\"%s\", \"password\":\"%s\"}", req.getParameter("login"), req.getParameter("pswd")), User.class);
            LOGGER.info("temp = " + temp.toString());
            user = UserDispatcher.getInstance()
                    .access("findByLoginPass", temp
                    );
            LOGGER.info("user = " + user);
            if (user != null && user.getLogin() == null) {
                req.setAttribute("err", "Пользователь или пароль указан не верно");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("userID", user.getId());
                req.getSession().setAttribute("login", user.getLogin());
                req.getSession().setAttribute("role", user.getRoles().getName());
                resp.sendRedirect(String.format("%s/", req.getContextPath()));
            }
        } catch (ExceptionSuchObjectAlreadyIs | ExceptionNullMethod e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
