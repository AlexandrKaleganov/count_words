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
import static org.junit.Assert.assertTrue;


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
        assertThat(dataBase.getId() > 0, Is.is(true));
    }

    @Test(expected = ExceptionSuchObjectAlreadyIs.class)
    public void addThrows() throws IOException, ExceptionSuchObjectAlreadyIs {
        LOGGER.info(" test = " + "addThrows");
        Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "role20"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser, "13", role.getId()), User.class));
        String newDb = String.format(jsonBD, "localhost1", user.getId());
        String newDb2 = String.format(jsonBD, "localhost1", user.getId());
        LOGGER.info("newDb = " + newDb);
        DataBase dataBase = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb, DataBase.class));
        DataBase dataBase2 = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb2, DataBase.class));
    }

    @Test
    public void delete() throws IOException, ExceptionSuchObjectAlreadyIs {
        LOGGER.info(" test = " + "delete");
        Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "role21"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser, "14", role.getId()), User.class));
        String newDb = String.format(jsonBD, "localhost2", user.getId());
        LOGGER.info("newDb = " + newDb);
        DataBase dataBase = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb, DataBase.class));
        DataBaseRepository.getInstance().delete(dataBase);
        assertThat(DataBaseRepository.getInstance().findById(dataBase).getId(), Is.is(0));
    }

    @Test
    public void edit() throws IOException, ExceptionSuchObjectAlreadyIs {
        LOGGER.info(" test = " + "edit");
        Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "role24"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser, "15", role.getId()), User.class));
        String newDb = String.format(jsonBD, "localhost3", user.getId());
        LOGGER.info("newDb = " + newDb);
        DataBase dataBase = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb, DataBase.class));
        dataBase.setIpBd("localhost4");
        DataBaseRepository.getInstance().edit(dataBase);
        assertThat(DataBaseRepository.getInstance().findById(dataBase).getIpBd(), Is.is("localhost4"));
    }

    @Test
    public void findAll() throws IOException, ExceptionSuchObjectAlreadyIs {
        LOGGER.info(" test = " + "findAll");
        Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "role25"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser, "16", role.getId()), User.class));
        String newDb = String.format(jsonBD, "localhost5", user.getId());
        LOGGER.info("newDb = " + newDb);
        DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb, DataBase.class));
        assertTrue(DataBaseRepository.getInstance().findAll().get(0).getId() > 0);
    }

    @Test
    public void findByID() throws IOException, ExceptionSuchObjectAlreadyIs {
        LOGGER.info(" test = " + "findByID");
        Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "role26"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser, "17", role.getId()), User.class));
        String newDb = String.format(jsonBD, "localhost7", user.getId());
        LOGGER.info("newDb = " + newDb);
        DataBase dataBase = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb, DataBase.class));
        assertThat(DataBaseRepository.getInstance().findById(dataBase).getIpBd(), Is.is(dataBase.getIpBd()));
    }

    @Test
    public void findByIp() throws IOException, ExceptionSuchObjectAlreadyIs {
        LOGGER.info(" test = " + "findByIp");
        Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "role27"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser, "18", role.getId()), User.class));
        String newDb = String.format(jsonBD, "localhost8", user.getId());
        LOGGER.info("newDb = " + newDb);
        DataBase dataBase = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb, DataBase.class));
        assertThat(DataBaseRepository.getInstance().findByIp(dataBase).getIpBd(), Is.is(dataBase.getIpBd()));
    }

    @Test
    public void findByName() throws IOException, ExceptionSuchObjectAlreadyIs {
        LOGGER.info(" test = " + "findByName");
        Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "role28"), Roles.class));
        User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser, "19", role.getId()), User.class));
        String newDb = String.format(jsonBD, "localhost9", user.getId());
        LOGGER.info("newDb = " + newDb);
        DataBase dataBase = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb, DataBase.class));
        assertThat(DataBaseRepository.getInstance().findByName(dataBase).get(0).getName(), Is.is(dataBase.getName()));
    }

    @Test(expected = ExceptionNullMethod.class)
    public void findByLoginPass() throws ExceptionNullMethod {
        LOGGER.info(" test = " + "findByLoginPass");
        DataBase dataBase = null;
        try {
            Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonRole, "role29"), Roles.class));
            User user = UserRepository.getInstance().add(new ObjectMapper().readValue(String.format(jsonUser, "20", role.getId()), User.class));
            String newDb = String.format(jsonBD, "localhost10", user.getId());
            LOGGER.info("newDb = " + newDb);
            dataBase = DataBaseRepository.getInstance().add(new ObjectMapper().readValue(newDb, DataBase.class));
        } catch (IOException | ExceptionSuchObjectAlreadyIs e) {
            LOGGER.error(e.getMessage(), e);
        }
        DataBaseRepository.getInstance().findByLoginPass(dataBase);
    }

    @Test
    public void findByLogin() {
    }
}