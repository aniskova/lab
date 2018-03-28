package by.gsu.tkp.util;

import by.gsu.tkp.beans.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileWorker {
    public static List<Employee> readFromFile(String filename){
        ArrayList<Employee> list = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream oin = new ObjectInputStream(fis);
            list = (ArrayList<Employee>) oin.readObject();
            oin.close();

        } catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void writeToFile(String filename, List<?> list){
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> getList(int length, Scanner sc){
        String name;
        String position;
        int salary;
        int surchargeRate;
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            System.out.println("For employee: ");
            sc.nextLine();
            System.out.println("Input name");
            name = sc.nextLine();
            System.out.println("Input position");
            position = sc.nextLine();
            System.out.println("Input salary");
            salary = sc.nextInt();
            System.out.println("Input surcharge rate");
            surchargeRate = sc.nextInt();
            list.add(new Employee(name, position, salary, surchargeRate));
        }
        return  list;
    }

}
