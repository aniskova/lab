import by.gsu.tkp.beans.User;
import by.gsu.tkp.factories.UsersFactory;
import by.gsu.tkp.readers.UsersReaderCSV;
import by.gsu.tkp.util.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        final String FILE_IN_NAME = "in";
        final String FILE_OUT_NAME = Util.DIR+ "out"+Util.EXT_CSV;
        UsersFactory uf = new UsersFactory();
        UsersReaderCSV usersReaderCSV = new UsersReaderCSV(FILE_IN_NAME, uf);
        List <User> users = usersReaderCSV.loadUserList();
        users.sort(Util.<User>compare()
                .thenComparing(u -> u.getNickname())
                .thenComparing(u -> u.getLogin()).thenComparing(user -> user.getSex()));
        PrintWriter pw=null;
        try {
           pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(FILE_OUT_NAME)));
            users.forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            pw.close();
        }

    }
}
