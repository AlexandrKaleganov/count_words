package ru.callinsicght.countwords.reposiroty;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.Test;
import ru.callinsicght.countwords.model.DataBase;
import ru.callinsicght.countwords.model.Roles;
import ru.callinsicght.countwords.model.User;

import java.io.IOException;

import static org.apache.log4j.Logger.getLogger;


public class DataBaseRepositoryTest {
    private final static Logger LOGGER = getLogger(DataBaseRepositoryTest.class);

    private String jsonUser = "{\"name\":\"name\", \"login\":\"login2\", \"roles\":{\"id\":\"1\",\"name\":\"ADMIN\"}, \"password\":\"pass\"}";
    private String jsonRole = "{\"id\":\"1\", \"name\":\"ADMIN\"}";
    private Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(jsonRole, Roles.class));
    private User user = UserRepository.getInstance().add(new ObjectMapper().readValue(jsonUser, User.class));
    private String jsonBD;


    public DataBaseRepositoryTest() throws IOException {
        jsonBD = "{\"id\":\"%s\", \"name\":\"irg\", \"ipBd\":\"%s\",\"password\":\"password\", \"user\":{\"id\":\"1\"}}";
    }

    @Test
    public void add() throws IOException {
        LOGGER.info(" data = " + "начали");
        String newDb = String.format(jsonBD, "1", "localhost");
        LOGGER.info("newDb = " + newDb);
        DataBase dataBase = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb, DataBase.class));
        LOGGER.info(" data = " + dataBase);
    }

    @Test
    public void delete() {
    }

    @Test
    public void edit() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByID() {
    }

    @Test
    public void findByName() {
    }

    @Test
    public void findByLoginPass() {
    }

    @Test
    public void findByLogin() {
    }
}