package ru.callinsicght.countwords.reposiroty;

import ru.callinsicght.countwords.model.App;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;

import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
public class AppRepository implements Store<App> {
    private static final AppRepository INSTANCE = new AppRepository();

    public static AppRepository getInstance() {
        return INSTANCE;
    }
    //мтод не раелизован
    @Override
    public App add(App app) throws ExceptionSuchObjectAlreadyIs, ExceptionNullMethod {
        error();
       return null;
    }

    //мтод не раелизован
    @Override
    public App delete(App app) {
        openSession(session -> {
                    session.delete(session.get(App.class, app.getId()));
                    return app;
                }
        );
        return app;
    }

    @Override
    public App edit(App app) throws ExceptionSuchObjectAlreadyIs, ExceptionNullMethod {
        error();
        return null;
    }

    @Override
    public List<App> findAll() {
        return openSession(session -> session.createQuery("from App ").list());
    }

    //поиск по id
    @Override
    public App findById(App App) {
        App rsl = openSession(session -> session.get(App.class, App.getId()));
        if (rsl == null) {
            rsl = new App(0);
        }
        return rsl;
    }

    //поиск по наименованию
    @Override
    public List<App> findByName(App app) {
        String sql = "from app where name = '" + app.getName() + "'";
        LOGGER.info("sql = " + sql);
        return refactList(sql);
    }

    //не реализован
    @Override
    public App findByLoginPass(App App) throws ExceptionNullMethod {
        error();
        return null;
    }

    //не реализован
    @Override
    public App findByLogin(App app) throws ExceptionNullMethod {
        error();
        return null;
    }

    private List<App> refactList(String sql) {
        return openSession(session -> {
            List<App> rsl = session.createQuery(sql).list();
            if (rsl.size() > 0) {
                return rsl;
            } else {
                rsl.add(new App(0));
                return rsl;
            }
        });
    }
}
