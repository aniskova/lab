package by.gsu.tkp.readers;

import by.gsu.tkp.beans.User;
import by.gsu.tkp.util.Util;
import by.gsu.tkp.factories.UsersFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersReaderCSV {
    private Scanner sc;
    private UsersFactory uf;

    public UsersReaderCSV(String fileName, UsersFactory usersFactory) {
        this.uf = usersFactory;
        String fullFileName = Util.DIR + fileName + Util.EXT_CSV;
        try {
            sc = new Scanner(new File(fullFileName));
        } catch (FileNotFoundException e) {
            System.out.println(String.format(Util.FILE_NOT_FOUND_FORMAT, fullFileName));
            closeReader();
            System.exit(0);
        }
    }

    public boolean hasResult() {
        return sc.hasNextLine();
    }
    public User nextResult()  {
        String[] lineData = sc.nextLine().split(Util.CSV_SEPARATOR);

        String nick = lineData[Util.INDEX_NICK];
        String login = lineData[Util.INDEX_LOGIN];
        String pass = lineData[Util.INDEX_PASS];
        String sex = lineData[Util.INDEX_SEX];
        String date = lineData[Util.INDEX_DATE];
        String posts = lineData[Util.INDEX_POSTS];
        return uf.getUserFromFactory(nick,login, pass, sex, convertDate(date), posts);
    }
    public List<User> loadUserList(){
        List<User> list= new ArrayList<>();
        while(hasResult()){
            User user = nextResult();
            list.add(user);
        }
        return list;
    }
    private static String convertDate(String date) {
        try {
            date = Util.DF_HYPHEN.format(Util.DF_DOT.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(date);
        }
        return date;
    }
    public void closeReader() {
        if (sc != null) {
            sc.close();
        }
    }
}
