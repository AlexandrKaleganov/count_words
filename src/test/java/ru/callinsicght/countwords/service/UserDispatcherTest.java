package ru.callinsicght.countwords.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ru.callinsicght.countwords.model.User;
import ru.callinsicght.countwords.reposiroty.ExceptionNullMethod;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;

import java.io.IOException;

public class UserDispatcherTest {

    @Test
    public void getInstance() throws IOException, ExceptionSuchObjectAlreadyIs {
        User temp = new ObjectMapper().readValue(String.format("{\"login\":\"%s\", \"password\":\"%s\"}",
                "root", "root"), User.class);
        System.out.println(temp);
        User test1 = null;
        try {
            test1 = UserDispatcher.getInstance().access("findByLoginPass", temp);
        } catch (ExceptionNullMethod exceptionNullMethod) {
            exceptionNullMethod.printStackTrace();
        }
        System.out.println(test1);
        String jsonRole = "{ \"name\":\"%s\"}";
        String jsonUser = "{\"name\":\"name\", \"login\":\"%s\", \"roles\":{\"id\":\"%s\"}, \"password\":\"pass\"}";
    }
}