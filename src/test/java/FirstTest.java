import classes.MathParser;
import classes.SingleOperationCalculator;
import classes.StringReplacer;
import interfaces.Calculator;
import org.testng.Assert;
import org.testng.annotations.*;



public class FirstTest {
    @Test
    public  void firstTest(){
        String example = "31+45*2+24/(9-1)+7^3";
        int answer = 0;

        MathParser parser = new MathParser();
        Calculator calculator = new SingleOperationCalculator();
        StringReplacer replacer = new StringReplacer();

        while (parser.canParse()) {
            String expression = parser.findSingleExpression(example); //Находим приоритетное подвыражение парсером
            if (expression != null) {
                if (parser.containsOneOperator(expression)) {
                    int result = calculator.solveString(expression); //Вычисляем значение приоритетного подвыражения
                    String stringResult = Integer.toString(result);
                    example = replacer.replace(example, stringResult,
                            parser.scopeReplacement()[0], parser.scopeReplacement()[1]); //Заменяем подвыражение значением этого подвыражения
                } else {
                    int begin = parser.scopeReplacement()[0]+1;
                    String newexpression = parser.findSingleExpression(expression); //Находим приоритетное подвыражение парсером
                    if (newexpression != null) {
                        int result = calculator.solveString(newexpression); //Вычисляем значение приоритетного подвыражения
                        String stringResult = Integer.toString(result);
                        example = replacer.replace(example, stringResult,
                                begin, begin+parser.scopeReplacement()[1]); //Заменяем подвыражение значением этого подвыражения

                    }
                }
            }
        }
        answer = Integer.parseInt(example); //в конечном итоге заменив каждое подвыражение в предыдущем цикле получим результат вычислений.
        System.out.println(answer);
        Assert.assertEquals( answer, 467);
    }
}
