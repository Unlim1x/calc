package classes;

import java.util.Scanner;

//Основная задача класса вернуть введенную строку
public class Console {

    public String input() {
        Scanner console = new Scanner(System.in);

            System.out.println("Для корректного завершения работы, введите q \n");
            System.out.println("Чтобы выводить все промежуточные операции введите s \n"); //aka stacktrace
            System.out.println("Введите выражение: ");

            String command = console.nextLine();
            command = command.trim();

            if (command.equals("q")) {
                return "q";
            }
            else if(command.equals("s")){
                return "s";
            }
            else{
                return command;
            }
    }
    public void output(String out){
        System.out.println(out);
    }
}
