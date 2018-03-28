import by.gsu.tkp.beans.Employee;
import by.gsu.tkp.util.FileWorker;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input list length");
        int length = sc.nextInt();
        List<Employee> list = FileWorker.getList(length, sc);
        FileWorker.writeToFile("lab3.out", list);
        List<Employee> list2 =FileWorker.readFromFile("lab3.out");
        for (Employee employee : list2) {
            System.out.println(employee);
        }

    }
}
