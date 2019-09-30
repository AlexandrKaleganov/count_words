package ru.callinsicght.countwords.controller.users;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.callinsicght.countwords.model.User;
import ru.callinsicght.countwords.reposiroty.ExceptionNullMethod;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;
import ru.callinsicght.countwords.service.ServiceAddObjects;
import ru.callinsicght.countwords.service.UserDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/api")
public class ServletUserList {
    private static final Logger LOGGER = Logger.getLogger(ServletUserList.class);

    /**
     * переход на страницу с пользователями
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    protected String doGet() {
        return "users/userList";
    }

    /**
     * @return получение списка всех пользователей
     */
    @GetMapping(value = "users/list")
    protected ResponseEntity<ArrayList<User>> findAll() throws ExceptionSuchObjectAlreadyIs, ExceptionNullMethod, IOException {
        LOGGER.info("тянем список пользователей ");
        return ResponseEntity.ok().body(UserDispatcher.getInstance().access("getListUser", new User()));
    }

    @GetMapping(value = "users/findById/{id}")
    public String findById(ModelMap map, @PathVariable int id) throws ExceptionSuchObjectAlreadyIs, ExceptionNullMethod, IOException {
        User users = UserDispatcher.getInstance().access("findByIdUser", new User(id));
        LOGGER.info(users);
        map.addAttribute("user", users);
        return "users/edit";
    }

    @GetMapping(value = "users/deleteUser/{id}")
    public String deleteUser(ModelMap map, @PathVariable int id) throws ExceptionSuchObjectAlreadyIs, ExceptionNullMethod, IOException {
        User users = UserDispatcher.getInstance().access("deleteUser", new User(id));
        return "redirect:api/users";
    }

    @RequestMapping(value = "users/addUser", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(HttpServletRequest req, HttpServletResponse resp) throws ExceptionSuchObjectAlreadyIs, ExceptionNullMethod, IOException {
        return ResponseEntity.ok().body(UserDispatcher.getInstance().access(req.getParameter("action"),  ServiceAddObjects.getInstance().addUser(req.getParameter("users"))));
    }
}