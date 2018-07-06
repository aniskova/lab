package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.util.ConnectionPool;
import by.gsu.epamlab.util.Constants;
import java.sql.*;

public class DBUserDAOImpl implements IUserDAO {
private static final String GET_USER_SQL ="SELECT * FROM users WHERE login = ?";
private static final String SET_USER_SQL ="INSERT INTO users (login, password) VALUES (?,?)";

    @Override
    public User getUser(String login, String pass) {
        final int ID_IND = 1;
        final int ID_LOGIN = 2;
        final int ID_PASS = 3;
        try(Connection cn = ConnectionPool.getConnection(); PreparedStatement ps = cn.prepareStatement(GET_USER_SQL)){
            ps.setString(ID_LOGIN-1,login);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt(ID_IND);
                String passDB = rs.getString(ID_PASS);
                if(pass.equals(passDB)){
                    return new User(id, login, pass);
                }else {
                    throw new DAOException(Constants.INCORRECT_PASS_ERROR);
                }
            }else {
                throw new DAOException(Constants.USER_DOESNT_EXIST_ERROR);
            }
        } catch (SQLException e) {
            throw new DAOException(Constants.DATA_SOURCE_ERROR +e);
        }
    }

    @Override
    public User addUser(String login, String pass) {
        final int IND_LOG=1;
        final int IND_PASS=2;
       try (Connection cn =ConnectionPool.getConnection();
            PreparedStatement ps = cn.prepareStatement(SET_USER_SQL, Statement.RETURN_GENERATED_KEYS);){
           ps.setString(IND_LOG, login);
           ps.setString(IND_PASS, pass);
           ps.executeUpdate();
           ResultSet rs = ps.getGeneratedKeys();
           if(rs.next()){
               int id = rs.getInt(1);
               return new User(id, login, pass);
           }else {
               throw  new DAOException(Constants.DATA_SOURCE_ERROR);
           }

       } catch (SQLException e) {
           throw new DAOException(Constants.LOGIN_OCCUPIED_ERROR+e);
       }
    }

}
