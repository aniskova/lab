package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.model.beans.User;

public interface IUserDAO {
    User getUser(String login, String pass);
    User addUser(String login, String pass);
}
