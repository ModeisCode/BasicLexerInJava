import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
/*
    SyntaxCheck sc = new SyntaxCheck();
    boolean isCorrect = sc.checkthesyntax("begin A=B+C;E=D^A end");
    System.out.println("CORRECTION->" + isCorrect);

    SyntaxCheck sc2 = new SyntaxCheck();
    boolean isCorrect2 = sc2.checkthesyntax("begin A=B-C;F=D*A end");
    System.out.println("CORRECTION->" + isCorrect2);
*/
    SyntaxCheck sc3 = new SyntaxCheck();
    boolean isCorrect3 = sc3.checkthesyntax("begin A=A-C+C+D+A+F+D+C;F=D*D^C^C-B end");
    System.out.println("CORRECTION->" + isCorrect3);


    }



}