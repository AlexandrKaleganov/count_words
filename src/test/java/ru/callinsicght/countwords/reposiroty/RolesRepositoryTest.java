package ru.callinsicght.countwords.reposiroty;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.Test;
import ru.callinsicght.countwords.model.Roles;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;

import java.io.IOException;

import static org.apache.log4j.Logger.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;

public class RolesRepositoryTest {
    private final static Logger LOGGER = getLogger(RolesRepositoryTest.class);

    @Test
    public void findAll() throws IOException, ExceptionSuchObjectAlreadyIs {
        String jsonRole = "{\"id\":\"1\", \"name\":\"ADMIN\"}";
        Roles role = RolesRepository.getInstance().add(new ObjectMapper().readValue(jsonRole, Roles.class));
        assertThat(RolesRepository.getInstance().findById(role).getName(), Is.is("ADMIN"));
    }
}