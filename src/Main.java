import by.gsu.tkp.beans.Good;
import by.gsu.tkp.util.InfoLoader;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        InfoLoader.loadDataIntoDB();
        try {
            List<Good> goods = InfoLoader.loadDataFromDB();
            for (Good good : goods) {
                System.out.format("%32s%10d%16d%n",good.getName(),good.getPrice(), good.getAmount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
