package dao.factory;

import dao.content.HvssContentDao;
import dao.content.HvssContentGroupDao;
import dao.content.impl.HvssContentDaoImpl;
import dao.content.impl.HvssContentGroupDaoImpl;

/**
 *
 * @author mrodriguez
 */
public class DaoFactory {

    private DaoFactory() {}

    public static HvssContentDao getUserDao(){
        return new HvssContentDaoImpl();
    }

    public static HvssContentGroupDao getUserRoleDao(){
        return new HvssContentGroupDaoImpl();
    }

}