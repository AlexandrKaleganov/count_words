package ru.callinsicght.countwords.service;

import org.apache.log4j.Logger;
import ru.callinsicght.countwords.model.Mod;
import ru.callinsicght.countwords.model.User;
import ru.callinsicght.countwords.model.Roles;
import ru.callinsicght.countwords.reposiroty.ExceptionNullMethod;
import ru.callinsicght.countwords.reposiroty.Store;
import ru.callinsicght.countwords.reposiroty.UserRepository;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.apache.log4j.Logger.getLogger;

/**
 * @author Alexander Kaleganov
 * @version 0.0.1
 */
public class UserDispatcher {
    private final static Logger LOGGER = getLogger(UserDispatcher.class);

    private final Store<User> userRepository = UserRepository.getInstance();
    private final Map<String, FankEx<Mod, Optional>> userDispatcher = new HashMap<>();
    private final static UserDispatcher INSTANCE = new UserDispatcher().init();

    public static UserDispatcher getInstance() {
        return INSTANCE;
    }
    /**
     * Load initial hashmap functional.
     */
    public UserDispatcher init() {
        //упавление пользователями
        this.userDispatcher.put("findByLoginPass", (ticket) ->
                Optional.of(
                        userRepository.findByLoginPass((User) ticket)));
        this.userDispatcher.put("getListUser", (ticket) ->
                Optional.of(UserRepository.getInstance().findAll()));
        this.userDispatcher.put("findByLogin", (ticket) ->
                Optional.of(UserRepository.getInstance().findByLogin((User) ticket)));
        this.userDispatcher.put("findByIdUser", (ticket) ->
                Optional.of(UserRepository.getInstance().findById((User) ticket)));
        this.userDispatcher.put("deleteUser", (ticket) ->
                Optional.of(UserRepository.getInstance().delete((User) ticket)));
        this.userDispatcher.put("addOrUpdate", (ticket) ->
                Optional.of(UserRepository.getInstance().edit((User) ticket)));
        this.userDispatcher.put("addUser", (ticket) ->
                Optional.of(UserRepository.getInstance().add((User) ticket)));
        return this;
    }

    /**
     * @param key    параметр указывает на ключ из хеш мапы, в заваисмости от ключа выбирается реализация функционального метода
     * @param ticket в оптионал передаётся передаётася обект модели {@link (Mod, Roles, User)}
     * @param <E>    параметр который вернётся, возможно это будет Lист объектов из базы, либо один объект
     */
    public <E> E access(String key, Mod ticket) throws IOException, ExceptionSuchObjectAlreadyIs, ExceptionNullMethod {
        LOGGER.info("объект который пришёл в диспатчер" + ticket);
        LOGGER.info("ключ который получил метод = " + key);
        //noinspection unchecked,OptionalGetWithoutIsPresent
        return (E) this.userDispatcher.get(key).submit(ticket).get();
    }
}
