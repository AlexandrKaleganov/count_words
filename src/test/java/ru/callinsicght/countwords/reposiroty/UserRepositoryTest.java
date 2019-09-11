package ru.callinsicght.countwords.reposiroty;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.Test;
import ru.callinsicght.countwords.model.Roles;
import ru.callinsicght.countwords.model.User;

import java.io.IOException;
import java.util.function.BiConsumer;

import static org.apache.log4j.Logger.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserRepositoryTest {
    private final static Logger LOGGER = getLogger(UserRepositoryTest.class);

    private String jsonUser = "{\"name\":\"name\", \"login\":\"login2\", \"roles\":{\"id\":\"1\",\"name\":\"ADMIN\"}, \"password\":\"pass\"}";
    private String jsonRole = "{\"id\":\"1\", \"name\":\"ADMIN\"}";
    private Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(jsonRole, Roles.class));
    private User user = UserRepository.getInstance().add(new ObjectMapper().readValue(jsonUser, User.class));

    public UserRepositoryTest() throws IOException {
    }

    private void testAll(BiConsumer<UserRepository, User> fank) {
        fank.accept(UserRepository.getInstance(), this.user);
    }

    @Test
    public void add() {
        LOGGER.info("пароль = " + this.user.getPassword());
        this.testAll((db, user) -> {
            LOGGER.info("user пришёл в метод = " + user);
            User user1 = UserRepository.getInstance().findByLogin(user);
            LOGGER.info("User = " + user1);
            assertThat(user1.getLogin(), Is.is("login2"));
        });
    }

//    @Test
//    public void delete() {
//        this.testAll((db, u) -> {
//            db.delete(u);
//            assertThat(db.findByID(u).getId(), Is.is(0));
//        });
//    }

    @Test
    public void edit() {
        LOGGER.info("пароль = " + this.user.getPassword());
        this.testAll((db, u) -> {
            u.setName("вася");
            db.edit(u);
            assertThat(db.findByID(u).getName(), Is.is("вася"));
        });
    }

    @Test
    public void findAll() {
        LOGGER.info("пароль = " + this.user.getPassword());
        this.testAll((db, u) -> {
            assertThat(db.findAll().size() > 0, Is.is(true));
        });
    }

    @Test
    public void findByID() {
        LOGGER.info("пароль = " + this.user.getPassword());
        this.testAll((db, u) -> {
            LOGGER.error(u.getId() + " id =");
            User user = UserRepository.getInstance().findByLogin(u);
            assertThat(db.findByID(user).getLogin(), Is.is("login2"));
        });
    }


    @Test
    public void findByLoginPass() {
        LOGGER.info("c " + this.user.getPassword());
        this.testAll((db, u) -> {
            u.setPassword("pass");
            assertThat(db.findByLoginPass(u).getLogin(), Is.is("login2"));
        });
    }

    @Test
    public void findByLogin() {
        LOGGER.info("пароль = " + this.user.getPassword());
        this.testAll((db, u) -> {
            assertThat(db.findByLogin(u).getLogin(), Is.is("login2"));
        });
    }
}