package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.impl.DBTaskDAOImpl;
import by.gsu.epamlab.model.impl.RamUserDAOImpl;

public class TaskDAOFactory {
    private static DataSource dataSource;
    public static void setDataSource(String dataSourceName) {
        dataSource = DataSource.valueOf(dataSourceName.toUpperCase());
    }

    public static ITaskDAO getClassFromFactory() {
        return dataSource.getTaskDAOImpl();
    }

    private enum DataSource{
        DB {
            @Override
            ITaskDAO getTaskDAOImpl() {
                return new DBTaskDAOImpl();
            }
        },

        RAM {
            @Override
            ITaskDAO getTaskDAOImpl() {
                return null;
            }
        };

        abstract ITaskDAO getTaskDAOImpl();
    }
}
