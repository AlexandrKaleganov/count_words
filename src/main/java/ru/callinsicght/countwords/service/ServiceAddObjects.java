package ru.callinsicght.countwords.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.callinsicght.countwords.model.Roles;
import ru.callinsicght.countwords.model.User;
import ru.callinsicght.countwords.reposiroty.ExceptionNullMethod;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;

import java.io.IOException;

public class ServiceAddObjects {
    private static final Logger LOGGER = Logger.getLogger(ServiceAddObjects.class);
    private static final ServiceAddObjects INSTANCE = new ServiceAddObjects();

    public static ServiceAddObjects getInstance() {
        return INSTANCE;
    }



    public User addUser(String json) {
        return this.addModel(json, j -> new ObjectMapper().readValue(j, User.class));
    }

    public Roles addRole(String json) {
        return this.addModel(json, j -> new ObjectMapper().readValue(j, Roles.class));
    }



    /**
     * рефактор try catch
     *
     * @param json   прилетает json строка, которую мы будем конвертировать в объект
     * @param mapper {@link FankEx} будет принимать json строку и возвращать объект
     * @param <E>    объект, который мы получем после конвертации json строрки в объект
     * @return обект полученный после конвертации строки в объект
     * @author Alexander Kaleganov
     */
    private <E> E addModel(String json, FankEx<String, E> mapper) {
        E rsl = null;
        try {
            rsl = mapper.submit(json);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ExceptionSuchObjectAlreadyIs | ExceptionNullMethod exceptionSuchObjectAlreadyIs) {
            exceptionSuchObjectAlreadyIs.printStackTrace();
        }
        return rsl;
    }

}
