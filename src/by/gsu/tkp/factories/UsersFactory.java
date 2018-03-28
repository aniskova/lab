package by.gsu.tkp.factories;

import by.gsu.tkp.beans.User;

public class UsersFactory {
    public User getUserFromFactory(String nickname, String login, String pass, String sex, String date, String amountPosts){
        return new User(nickname,login, pass, sex, date, amountPosts);
    }
}
