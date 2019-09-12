package ru.callinsicght.countwords.reposiroty;

import ru.callinsicght.countwords.model.DataBase;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;

import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
public class DataBaseRepository implements Store<DataBase> {
    private static final DataBaseRepository INSTANCE = new DataBaseRepository();

    public static DataBaseRepository getInstance() {
        return INSTANCE;
    }

    //мтод не раелизован
    @Override
    public DataBase add(DataBase dataBase) throws ExceptionSuchObjectAlreadyIs {
        if (this.findByIp(dataBase).getId() > 0) {
            LOGGER.info("объект с таким IP = " + this.findByIp(dataBase).getId());
            throw new ExceptionSuchObjectAlreadyIs("объект с таким IP адресом уже есть в бд");
        } else {
            return openSession(session -> {
                session.save(dataBase);
                return session.load(DataBase.class, dataBase.getId());
            });
        }
    }

    //мтод не раелизован
    @Override
    public DataBase delete(DataBase dataBase) {
        openSession(session -> {
                    session.delete(session.get(DataBase.class, dataBase.getId()));
                    return dataBase;
                }
        );
        return dataBase;
    }

    @Override
    public DataBase edit(DataBase dataBase) throws ExceptionSuchObjectAlreadyIs {
        DataBase test = this.findByIp(dataBase);
        if (test.getId() != dataBase.getId() && test.getId() > 0) {
            throw new ExceptionSuchObjectAlreadyIs("объект с таким IP адресом уже есть в бд");
        } else {
            return openSession(session -> {
                session.saveOrUpdate(dataBase);
                return session.get(DataBase.class, dataBase.getId());
            });
        }
    }

    @Override
    public List<DataBase> findAll() {
        return openSession(session -> session.createQuery("from DataBase ").list());
    }

    //поиск по id
    @Override
    public DataBase findById(DataBase dataBase) {
        DataBase rsl = openSession(session -> session.get(DataBase.class, dataBase.getId()));
        if (rsl == null) {
            rsl = new DataBase(0);
        }
        return rsl;
    }

    //поиск по ip
    public DataBase findByIp(DataBase dataBase) {
        String sql = "from DataBase where ip_bd = '" + dataBase.getIpBd() + "'";
        return refactList(sql).get(0);
    }

    //поиск по наименованию
    @Override
    public List<DataBase> findByName(DataBase dataBase) {
        String sql = "from DataBase where name = '" + dataBase.getName() + "'";
        LOGGER.info("sql = " + sql);
        return refactList(sql);
    }

    //не реализован
    @Override
    public DataBase findByLoginPass(DataBase dataBase) throws ExceptionNullMethod {
        error();
        return null;
    }

    //не реализован
    @Override
    public DataBase findByLogin(DataBase dataBase) throws ExceptionNullMethod {
        error();
        return null;
    }

    private List<DataBase> refactList(String sql) {
        return openSession(session -> {
            List<DataBase> rsl = session.createQuery(sql).list();
            if (rsl.size() > 0) {
                return rsl;
            } else {
                rsl.add(new DataBase(0));
                return rsl;
            }
        });
    }
}
