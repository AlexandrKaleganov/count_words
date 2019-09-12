package ru.callinsicght.countwords.reposiroty;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.Test;
import ru.callinsicght.countwords.model.DataBase;
import ru.callinsicght.countwords.model.Roles;
import ru.callinsicght.countwords.model.User;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;

import java.io.IOException;

import static org.apache.log4j.Logger.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;


public class DataBaseRepositoryTest {
    private final static Logger LOGGER = getLogger(DataBaseRepositoryTest.class);

    private String jsonUser = "{\"name\":\"name\", \"login\":\"%s\", \"roles\":{\"id\":\"%s\"}, \"password\":\"pass\"}";
    private String jsonRole = "{\"name\":\"%s\"}";
    private String jsonBD = "{\"name\":\"irg\", \"ipBd\":\"%s\",\"password\":\"password\", \"user\":{\"id\":\"%s\"}}";

    @Test
    public void add() throws IOException, ExceptionSuchObjectAlreadyIs {
        Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "role19"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser, "12", role.getId()), User.class));
        String newDb = String.format(jsonBD, "localhost", user.getId());
        LOGGER.info("newDb = " + newDb);
        DataBase dataBase = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb, DataBase.class));
        assertThat(dataBase.getId(), Is.is(1));
    }

    @Test(expected = ExceptionSuchObjectAlreadyIs.class)
    public void addThrows() throws IOException, ExceptionSuchObjectAlreadyIs {
        LOGGER.info(" test = " + "addThrows");
        Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "role20"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser, "13", role.getId()), User.class));
        String newDb = String.format(jsonBD,  "localhost1",  user.getId());
        String newDb2 = String.format(jsonBD, "localhost1",  user.getId());
        LOGGER.info("newDb = " + newDb);
        DataBase dataBase = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb, DataBase.class));
        DataBase dataBase2 = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb2, DataBase.class));
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