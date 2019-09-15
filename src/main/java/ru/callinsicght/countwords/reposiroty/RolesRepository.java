package ru.callinsicght.countwords.reposiroty;

import ru.callinsicght.countwords.model.Roles;

import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
public class RolesRepository implements Store<Roles> {
    private static final RolesRepository INSTANCE = new RolesRepository();

    public static RolesRepository getInstance() {
        return INSTANCE;
    }

    //мтод не раелизован
    @Override
    public Roles add(Roles roles) {
        if (this.findByName(roles).get(0).getId() > 0) {
            return roles;
        }
        return openSession(session -> {
            session.save(roles);
            return session.load(Roles.class, roles.getId());
        });
    }

    //мтод не раелизован
    @Override
    public Roles delete(Roles roles) throws ExceptionNullMethod {
        error();
        return null;
    }

    //мтод не раелизован
    @Override
    public Roles edit(Roles roles) throws ExceptionNullMethod {
        error();
        return null;
    }

    @Override
    public List<Roles> findAll() {
        return openSession(session -> session.createQuery("from Roles ").list());
    }

    //мтод не раелизован
    @Override
    public Roles findById(Roles roles) {
        Roles rsl = openSession(session -> session.get(Roles.class, roles.getId()));
        if (rsl == null) {
            rsl = new Roles(0);
        }
        return rsl;
    }

    //мтод не раелизован
    @Override
    public List<Roles> findByName(Roles roles) {
        String sql = "from Roles where name = '" + roles.getName() + "'";
        LOGGER.info(" roles.getName() = " + roles.getName());
        return refactList(sql);
    }

    @Override
    public Roles findByLoginPass(Roles roles) throws ExceptionNullMethod {
        error();
        return null;
    }

    @Override
    public Roles findByLogin(Roles roles) throws ExceptionNullMethod {
        error();
        return null;
    }

    @Override
    public Roles findByIp(Roles roles) throws ExceptionNullMethod {
        error();
        return null;
    }

    private List<Roles> refactList(String sql) {
        return openSession(session -> {
            List<Roles> rsl = session.createQuery(sql).list();
            if (rsl.size() > 0) {
                return rsl;
            } else {
                rsl.add(new Roles(0));
                return rsl;
            }
        });
    }
}
