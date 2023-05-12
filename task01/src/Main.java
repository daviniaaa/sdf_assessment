package task01;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome.");

        Console cons = System.console();
        String input = "";

        String a;
        String opt;
        String b;

        Double first;
        Double second;
        Double last = 0.0;

        while(!input.equals("exit")) {

            // System.out.println("enter input");
            input = cons.readLine("> ");

            if (input.equals("exit")) {
                break;
            }

            Scanner scan = new Scanner(input);

            a = scan.next();
            opt = scan.next();
            b = scan.next();

            scan.close(); // google says to "get rid of it as soon as you exhaust its input"

            if (a.equals("$last")) {
                first = last;
            } else {
                first = Double.parseDouble(a);
            }

            if (b.equals("$last")) {
                second = last;
            } else {
                second = Double.parseDouble(b);
            }

            switch (opt) {
                case "+":
                    last = first + second;
                    if (last % 1 == 0){
                        DecimalFormat df = new DecimalFormat("###.#");
                        System.out.println(df.format(last));
                        break;
                    }
                    System.out.println(last);
                    break;
                case "-":
                    last = first - second;
                    if (last % 1 == 0){
                        DecimalFormat df = new DecimalFormat("###.#");
                        System.out.println(df.format(last));
                        break;
                    }
                    System.out.println(last);
                    break;
                case "/":
                    last = first / second;
                    if (last % 1 == 0){
                        DecimalFormat df = new DecimalFormat("###.#");
                        System.out.println(df.format(last));
                        break;
                    }
                    System.out.println(last);
                    break;
                case "*":
                    last = first * second;
                    if (last % 1 == 0){
                        DecimalFormat df = new DecimalFormat("###.#");
                        System.out.println(df.format(last));
                        break;
                    }
                    System.out.println(last);
                    break;
                default:
                    System.out.println("Invalid operator");
            }
        }
        System.out.println("Bye bye");
    }
}