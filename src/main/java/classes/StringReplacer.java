package classes;

public class StringReplacer {
    public String replace(String string, String subString, int from, int to){
        return new StringBuilder(string).replace(from, to, subString).toString();
    }
}
