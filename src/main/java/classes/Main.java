package classes;

public class Main {

    public static void main(String[] args) {
        boolean showAll = false;
        boolean active = true;
        Console console = new Console();
        while (active){
            String command = console.input();
            switch (command){
                case "q":
                    active = false;
                    break;
                case "s":
                    showAll = !showAll;
                    if(showAll)
                        console.output("Включен вывод промежуточных вычислений");
                    else
                        console.output("Вывод промежуточных вычислений отключен");
                    break;
                default:
                    Core.calculate(command, showAll);
            }
        }

    }

}
