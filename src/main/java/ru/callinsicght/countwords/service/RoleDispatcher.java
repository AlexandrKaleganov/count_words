package ru.callinsicght.countwords.service;

import org.apache.log4j.Logger;
import ru.callinsicght.countwords.model.Mod;
import ru.callinsicght.countwords.model.Roles;
import ru.callinsicght.countwords.model.User;
import ru.callinsicght.countwords.reposiroty.ExceptionNullMethod;
import ru.callinsicght.countwords.reposiroty.RolesRepository;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.apache.log4j.Logger.getLogger;

/**
 *
 * @author Alexander Kaleganov
 * @version 0.0.1
 */
public class RoleDispatcher {
    private final static Logger LOGGER = getLogger(RoleDispatcher.class);

    private final Map<String, FankEx<Mod, Optional>> userDispatcher = new HashMap<>();
    private final static RoleDispatcher INSTANCE = new RoleDispatcher().init();

    public static RoleDispatcher getInstance() {
        return INSTANCE;
    }

    /**
     * Load initial hashmap functional.
     */
    public RoleDispatcher init() {
        //управление ролями
        this.userDispatcher.put("findAllRoles", (ticket) ->
                Optional.of(RolesRepository.getInstance().findAll()));
        return this;
    }

    /**
     * @param key    параметр указывает на ключ из хеш мапы, в заваисмости от ключа выбирается реализация функционального метода
     * @param ticket в оптионал передаётся передаётася обект модели {@link (Mod, Roles, User)}
     * @param <E>    параметр который вернётся, возможно это будет Lист объектов из базы, либо один объект
     */
    public <E> E access(String key, Mod ticket) throws IOException, ExceptionSuchObjectAlreadyIs, ExceptionNullMethod {
        //noinspection unchecked,OptionalGetWithoutIsPresent
        return (E) this.userDispatcher.get(key).submit(ticket).get();
    }
}
