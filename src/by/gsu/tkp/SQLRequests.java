package by.gsu.tkp;

public class SQLRequests {
    public static final String CREATE_DB="CREATE database IF NOT EXISTS forLab9;";
    public static final String CREATE_TABLE="create table IF NOT EXISTS forLab9.goods (\n" +
            "id int (10) AUTO_INCREMENT,\n" +
            "name varchar(20) NOT NULL,\n" +
            "price int(10) NOT NULL,\n" +
            "amount int(10) NOT NULL,\n" +
            "PRIMARY KEY (id)); \n";

    public static final String INSERT_GOOD ="INSERT INTO forLab9.goods(name, price, amount) VALUES (?,?,?)";
    public static final String TASK_QUERY ="SELECT * from forLab9.goods ORDER BY amount DESC";
}
