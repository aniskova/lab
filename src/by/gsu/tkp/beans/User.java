package by.gsu.tkp.beans;

import java.sql.Date;

public class User {
    private String nickname;
    private String login;
    private String pass;
    private String sex;
    private Date date;
    private int amountPosts;

    public User() {
    }

    public User(String nickname, String login, String pass, String sex, Date date, int amountPosts) {
        this.nickname = nickname;
        this.login = login;
        this.pass = pass;
        this.sex = sex;
        this.date = date;
        this.amountPosts = amountPosts;
    }
     public User(String nickname, String login, String pass, String sex, String date, String amountPosts) {
        this.nickname = nickname;
        this.login = login;
        this.pass = pass;
        this.sex = sex;
        setDate(date);
        setAmountPosts(amountPosts);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmountPosts() {
        return amountPosts;
    }

    public void setAmountPosts(int amountPosts) {
        this.amountPosts = amountPosts;
    }

    public void setAmountPosts(String amountPosts) {
        this.amountPosts = Integer.parseInt(amountPosts);
    }
    public void setDate(String date) {
        setDate(Date.valueOf(date));
    }

    @Override
    public String toString() {
        return  nickname + ";" + login +
                ";" + pass + ";" + sex +
                ";" + date + ";" + amountPosts +";";
    }
}
