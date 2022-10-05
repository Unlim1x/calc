package classes;


import interfaces.Calculator;

public class SingleOperationCalculator implements Calculator {
    //Класс принимает на вход строку с одним оператором
    //Определяет оператор ^ * / + -
    //Выполняет необходимое вычисление и возвращает результат

    private int substringToInt(String ex, int from, int to){
        return Integer.parseInt(ex.substring(from, to));
    }

    private int subStringToInt(String ex, int from){
        return Integer.parseInt(ex.substring(from));
    }


    @Override
    public int solveString(String expression) {
        int powPosition = expression.indexOf('^');
        int multiplyPosition = expression.indexOf('*');
        int divPosition = expression.indexOf('/');
        int plusPosition = expression.indexOf('+');
        int minusPosition = expression.indexOf('-');
        if (powPosition != -1){
               return involution(substringToInt(expression, 0, powPosition),
                       subStringToInt(expression, powPosition+1));
        }
        else if(multiplyPosition !=-1){
            return multiply(substringToInt(expression, 0, multiplyPosition),
                    subStringToInt(expression, multiplyPosition+1));
        }
        else if(divPosition !=-1){
            return divide(substringToInt(expression, 0, divPosition),
                    subStringToInt(expression, divPosition+1));
        }
        else if(plusPosition !=-1){
            return sum(substringToInt(expression, 0, plusPosition),
                    subStringToInt(expression, plusPosition+1));
        }
        else if(minusPosition !=-1){
            return subtract(substringToInt(expression, 0, minusPosition),
                    subStringToInt(expression, minusPosition+1));
        }

        return 0;
    }


    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public int divide(int div, int dir) {
        return div / dir;
    }

    @Override
    public int involution(int a, int b) {
        return (int)Math.pow(a,b);
    }
}
