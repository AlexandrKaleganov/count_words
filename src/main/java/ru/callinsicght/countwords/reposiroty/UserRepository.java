package ru.callinsicght.countwords.reposiroty;

import ru.callinsicght.countwords.model.User;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;

import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
public class UserRepository implements Store<User> {
    private static final UserRepository INSTANCE = new UserRepository();

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public User add(User user) throws ExceptionSuchObjectAlreadyIs {
        User test = this.findByLogin(user);
        if (test.getId() != 0) {
            LOGGER.info("объект с таким IP = " + this.findByLogin(user).getId());
            throw new ExceptionSuchObjectAlreadyIs("объект с таким IP адресом уже есть в бд");
        } else {
            return openSession(session -> {
                session.save(user);
                return session.get(User.class, user.getId());
            });
        }
    }

    @Override
    public User delete(User user) {
        return openSession(session -> {
            User rsl = session.get(User.class, user.getId());
            session.delete(rsl);
            return rsl;
        });
    }

    @Override
    public User edit(User user) {
        User test = this.findByLogin(user);
        if (test.getId() != user.getId() && test.getId() != 0) {
            return user;
        } else {
            return openSession(session -> {
                session.saveOrUpdate(user);
                return session.get(User.class, user.getId());
            });
        }
    }

    @Override
    public List<User> findAll() {
        return openSession(session -> session.createQuery("from User").list());
    }

    @Override
    public User findById(User user) {
        User rsl = null;
        rsl = openSession(session -> session.get(User.class, user.getId()));
        if (rsl == null) {
            rsl = new User(0);
        }
        return rsl;
    }

    @Override
    public List<User> findByName(User user) {
        String sql = "from User where name = '" + user.getName() + "'";
        return refactList(sql);
    }

    @Override
    public User findByLoginPass(User user) {
        LOGGER.info("пользователь которого ищем = " + user);
        String sql = "from User where login = '" + user.getLogin() + "' and password = '" + user.getPassword() + "'";
        return refactList(sql).get(0);
    }

    @Override
    public User findByLogin(User user) {
        String sql = "from User where login = '" + user.getLogin() + "'";
        return refactList(sql).get(0);
    }

    @Override
    public User findByIp(User user) throws ExceptionNullMethod {
        error();
        return null;
    }

    private List<User> refactList(String sql) {
        return openSession(session -> {
            List<User> rsl = session.createQuery(sql).list();
            if (rsl.size() > 0) {
                return rsl;
            } else {
                rsl.add(new User(0));
                return rsl;
            }
        });
    }
}
