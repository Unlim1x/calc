package classes;
import interfaces.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathParser implements Parser{

    //Регулярка для самых внутренних скобок \([^\(\)]+\) : ищем все от и до совпадения и считаем, что внутри по правилам внизу
    //Заменяем подстроку полученным выражением

    //1. Поиск операции возведения в степень: [^\n\+\-*\/]+[\^][^\*\/\+\n]+

    //2. Поиск операции умножения и деления: [^\n\+\-\*\/]+[\*\/][^\*\/\+\n]+

    //3. Поиск операций сложения и вычитания: [^\n\+\-\/]+[\+\-][^\*\/\+\-\n]+


    int leftScope;
    int rightScope;
    boolean can_parse = true;

    @Override
    public String findSingleExpression(String input) {

        String innerBracketRegex = "\\([^\\(\\)]+\\)";
        String powRegex = "[^\\n\\+\\-*\\/]+[\\^][^\\*\\/\\+\\n]+";
        String mdRegex = "[^\\n\\+\\-\\*\\/]+[\\*\\/][^\\*\\/\\+\\n\\-]+";
        String asRegex = "[^\\n\\+\\-\\/]+[\\+\\-][^\\*\\/\\+\\-\\n]+";


        String innerBracketExpression = result(innerBracketRegex, input);
        if (innerBracketExpression!=null)
            return input.substring(leftScope+1, rightScope-1);

        String powExpression = result(powRegex, input);
        if (powExpression!=null)
            return input.substring(leftScope, rightScope);

        String mdExpression = result(mdRegex, input);
        if (mdExpression!=null)
            return input.substring(leftScope, rightScope);

        String asExpression = result(asRegex, input);
        if (asExpression!=null)
            return input.substring(leftScope, rightScope);

        can_parse = false;
        return null;
    }

    private String result(String regex, String text){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            leftScope = matcher.start();
            rightScope = matcher.end();
            return text.substring(leftScope, rightScope);
        }
        return null;
    }

    public int[] scopeReplacement(){
        return new int[] {leftScope, rightScope};
    }
@Override
    public boolean canParse() {
        return can_parse;
    }

    public boolean containsOneOperator(String substring){
        int quantity = 0;
        if (substring.indexOf('^')!=-1)
            quantity++;
        if (substring.indexOf('*')!=-1)
            quantity++;
        if (substring.indexOf('/')!=-1)
            quantity++;
        if (substring.indexOf('+')!=-1)
            quantity++;
        if (substring.indexOf('-')!=-1)
            quantity++;
        return quantity <= 1;
    }
}
