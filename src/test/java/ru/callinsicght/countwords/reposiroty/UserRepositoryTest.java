package ru.callinsicght.countwords.reposiroty;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.Test;
import ru.callinsicght.countwords.model.Roles;
import ru.callinsicght.countwords.model.User;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;

import java.io.IOException;
import java.util.function.Consumer;

import static org.apache.log4j.Logger.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserRepositoryTest {
    private final static Logger LOGGER = getLogger(UserRepositoryTest.class);

    private String jsonRole = "{ \"name\":\"%s\"}";
    private String jsonUser = "{\"name\":\"name\", \"login\":\"%s\", \"roles\":{\"id\":\"%s\"}, \"password\":\"pass\"}";


    @Test
    public void add() throws IOException, ExceptionSuchObjectAlreadyIs {
        Roles role =
                RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "odmin9"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser,
                "login10", role.getId()), User.class));
        User user2 = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser,
                "login12", role.getId()), User.class));
        LOGGER.info("пароль = " + user.getPassword());
        assertThat(UserRepository.getInstance().findById(user).getLogin(), Is.is("login10"));
    }

//    @Test
//    public void delete() {
//        this.testAll((db, u) -> {
//            db.delete(u);
//            assertThat(db.findByID(u).getId(), Is.is(0));
//        });
//    }

    @Test
    public void edit() throws IOException {
        Roles role =
                RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "odmin10"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser,
                "login11", role.getId()), User.class));
        LOGGER.info("пароль = " + user.getPassword());
        user.setName("вася");
        UserRepository.getInstance().edit(user);
        assertThat(UserRepository.getInstance().findById(user).getName(), Is.is("вася"));
    }

    @Test
    public void findAll() throws IOException, ExceptionSuchObjectAlreadyIs {
        Roles role =
                RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "odmin11"), Roles.class));
       UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser,
                "login12", role.getId()), User.class));
            assertThat(UserRepository.getInstance().findAll().size() > 0, Is.is(true));
    }

    @Test
    public void findByID() throws IOException {
        Roles role =
                RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "odmin12"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser,
                "login13", role.getId()), User.class));
            LOGGER.error(user.getId() + " id =");
            assertThat(UserRepository.getInstance().findById(user).getLogin(), Is.is("login13"));
    }


    @Test
    public void findByLoginPass() throws IOException {
        Roles role =
                RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "odmin13"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser,
                "login14", role.getId()), User.class));
            user.setPassword("pass");
            assertThat(UserRepository.getInstance().findByLoginPass(user).getLogin(), Is.is("login14"));
    }

    @Test
    public void findByLogin() throws IOException {
        Roles role =
                RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "odmin14"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser,
                "login15", role.getId()), User.class));
            assertThat(UserRepository.getInstance().findByLogin(user).getLogin(), Is.is("login15"));
    }
}