import java.util.ArrayList;
import java.util.Scanner;

public class Runner {


        private static int amountThread;
        private static String operation;
        private static int limit;
        public static ArrayList<Long> list = new ArrayList<>();
        static Scanner scn;

        public static void main (String [] args){
            System.out.println("number of threads = ");
            scn = new Scanner(System.in);
            amountThread = scn.nextInt();
            System.out.println("number 'a' (limit) = ");
            limit = scn.nextInt();
            System.out.println("choose operation: '+' or '-' or '*':");
            String temp = scn.next();
            if (temp.equals("+") || temp.equals("-") || temp.equals("*")){
                operation = temp;
            }else {
                System.out.println("The type of operation isn't correct");
                System.exit(0);
            }
            scn.close();
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Result is: "+ getResult());
        }

        public static void execute() throws InterruptedException{
            Counter counter = new Counter();

            for (int i = 0; i < amountThread; i++){
                Thread thread = new LabThread(counter, limit);
                thread.start();
            }
            Thread.sleep(1000);
        }

        public static long getResult(){
            long result = 0;
            switch (operation) {
                case "+": {
                    for (long el : list){
                        result += el;
                    }
                }
                break;
                case "-": {
                    for (long el : list) {
                        result += el;
                    }
                }
                break;
                case "*": {
                    result = 1;
                    for (long el : list) {
                        result *= el;
                    }
                }
                break;
            }
            return result;
        }
    }

