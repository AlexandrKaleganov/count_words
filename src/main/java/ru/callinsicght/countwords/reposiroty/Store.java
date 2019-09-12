package ru.callinsicght.countwords.reposiroty;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import ru.callinsicght.countwords.reposiroty.err.ExceptionSuchObjectAlreadyIs;
import ru.callinsicght.countwords.service.Sfactory;

import java.util.List;
import java.util.function.Function;


/**
 * @param <E>
 * @author Alexander Kaleganov
 * @since 17.05.2019
 */
public interface Store<E> {
    Sfactory S_FACTORY = Sfactory.getINSTANCE();
    Logger LOGGER = Logger.getLogger(Store.class);

    /**
     * refactor close factory and close session
     *
     * @param fank метод который будем вызывать обновление удаление изменение запрос
     * @param <E>  объект который мы получим
     * @return объект получим в зависимости от реализации
     */
    @SuppressWarnings("TypeParameterHidesVisibleType")
    default <E> E openSession(Function<Session, E> fank) {
        E rsl = null;
        try (Session session = S_FACTORY.getFactory().openSession()) {
            try {
                session.beginTransaction();
                rsl = fank.apply(session);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rsl;
    }

    default void error() throws ExceptionNullMethod {
            throw new ExceptionNullMethod();
    }

    E add(E e) throws ExceptionSuchObjectAlreadyIs;

    E delete(E e) throws ExceptionNullMethod;

    E edit(E e) throws ExceptionSuchObjectAlreadyIs, ExceptionNullMethod;

    List<E> findAll();

    E findById(E e);

    List<E> findByName(E e);

    E findByLoginPass(E e) throws ExceptionNullMethod;

    E findByLogin(E e) throws ExceptionNullMethod;

}
