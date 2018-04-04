package by.gsu.tkp.util;

import by.gsu.tkp.DBConnector;
import by.gsu.tkp.SQLRequests;
import by.gsu.tkp.beans.Good;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InfoLoader {
    private static Scanner scanner = null;

    private static void createDB(){

        try(Connection cn =DBConnector.getConnection(); PreparedStatement ps1 = cn.prepareStatement(SQLRequests.CREATE_DB
        ); PreparedStatement ps2 = cn.prepareStatement(SQLRequests.CREATE_TABLE)){
            ps1.executeUpdate();
            ps2.executeUpdate();
        }catch (SQLException  e){
            System.out.println("some problems with creation new DB"+e);
        }
    }

    public static void loadDataIntoDB(){
        final int ID_NAME = 1;
        final int ID_PRICE = 2;
        final int ID_AMOUNT = 3;
        createDB();
        try(Connection cn = DBConnector.getConnection(); PreparedStatement ps = cn.prepareStatement(SQLRequests.INSERT_GOOD)) {
            scanner = new Scanner(new FileReader("src/testingData.txt"));
            while (scanner.hasNext()) {
                String[] values = scanner.nextLine().split(";");
                ps.setString(ID_NAME, values[ID_NAME-1]);
                ps.setInt(ID_PRICE, Integer.valueOf(values[ID_PRICE-1]));
                ps.setInt(ID_AMOUNT, Integer.valueOf(values[ID_AMOUNT-1]));
                ps.executeUpdate();
            }

        }catch (SQLException e){
            System.out.println("some problems with loading date from file to db"+e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static List<Good> loadDataFromDB() throws SQLException {
        final int ID_ID = 1;
        final int ID_NAME = 2;
        final int ID_PRICE = 3;
        final int ID_AMOUNT = 4;
        List<Good> goods = new ArrayList<>();
        try(Connection cn = DBConnector.getConnection(); PreparedStatement ps = cn.prepareStatement(SQLRequests.TASK_QUERY)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Good good = new Good(rs.getInt(ID_ID), rs.getString(ID_NAME), rs.getInt(ID_PRICE), rs.getInt(ID_AMOUNT));
                goods.add(good);
            }
            return goods;

        }catch (Exception e){
            throw new SQLException("smth going wrong with loading data from"+e);

        }
    }
}