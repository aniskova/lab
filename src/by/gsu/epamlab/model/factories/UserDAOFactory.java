package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.impl.DBUserDAOImpl;
import by.gsu.epamlab.model.impl.RamUserDAOImpl;

public class UserDAOFactory {
    private static DataSource dataSource;
    public static void setDataSource(String dataSourceName) {
        dataSource = DataSource.valueOf(dataSourceName.toUpperCase());
    }

    public static IUserDAO getClassFromFactory() {
        return dataSource.getUserDAOImpl();
    }

    private enum DataSource{
        DB {
            @Override
            IUserDAO getUserDAOImpl() {
                return new DBUserDAOImpl();
            }
        },

        RAM {
            @Override
            IUserDAO getUserDAOImpl() {
                return new RamUserDAOImpl();
            }
        };

        abstract IUserDAO getUserDAOImpl();
    }
}
