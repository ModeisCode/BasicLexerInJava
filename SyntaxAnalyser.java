import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SyntaxAnalyser {

    //{"begin+","statement+","var+","expression [+]","operator+","end+"};
    public String program;
    String begin_regex = "^begin ";
    String end_regex = " end$";
    String var_regex = "[A-F]";
    String operator_regex = "\\+|-|\\*|\\^|=";
    SyntaxAnalyser() {

    }

    public String[] splitAllTextWithSemicolon(String program) {
        for (String splitted: program.split(";")) {
            System.out.println("EXPRESSION_FOUND:" + splitted);
        }
        return program.split(";");
    }

    public boolean lexAllExpressions() {
        for (String expression : splitAllTextWithSemicolon(program)) {
            if (!lexExpression(expression))
                return false;
        }
        return true;
    }

    public boolean isLanguageCorrect() {
        Pattern p_begin = Pattern.compile(begin_regex);
        Pattern p_end = Pattern.compile(end_regex);
        Matcher matcher_begin = p_begin.matcher(program);
        Matcher matcher_end = p_end.matcher(program);
        if (matcher_begin.find() && matcher_end.find()) {
            return true;
        }
        else
            return false;
    }

    public boolean lexExpression(String expression) {
        System.out.println("started");
        System.out.println("EXPRESSION:" + expression);

        Pattern p_var = Pattern.compile(var_regex);
        Pattern p_operator_2 = Pattern.compile(operator_regex);

        Matcher m_var = p_var.matcher(expression);
        Matcher m_operator = p_operator_2.matcher(expression);
        Stream<MatchResult> res = m_var.results();
        List<Token> m_all_tokens = new ArrayList<>();
        res.forEach(mres -> {
            Token mres_token = new Token("VAR",mres.group());
            mres_token.start_index = mres.start();
            m_all_tokens.add(mres_token);
        });

        Stream<MatchResult> res_o = m_operator.results();

        res_o.forEach(mop -> {
            Token mop_token = new Token("OPERATOR",mop.group());
            mop_token.start_index = mop.start();
            m_all_tokens.add(mop_token);
        });
        System.out.println("---");
        Token[] m_all_tokens_arr = new Token[expression.length()];
        List<Token> tokenizer = new ArrayList<>();
        for (Token token : m_all_tokens) {
            m_all_tokens_arr[token.start_index] = token;
        }

        for (Token token: m_all_tokens_arr) {
            if (token != null) {
                System.out.println(token.type +"," + token.data);
            }
            else {
                System.out.println("NULL");
            }
        }

        System.out.println("FINISHED!!");

        return checkTokensAreValidate(m_all_tokens_arr);
    }

    public String getTokenType(String token_data) {
        switch (token_data) {
            case "=":
                return "EQUAL";
            default:
                return "OPERATOR";
        }
    }

    public boolean Analyse() {
        return lexExpression(program);
    }

    public Token getLastElement(String expression,Pattern p_var,Pattern p_operator,List<Token> expression_tokens,String expression_check) {
        char lastItem = expression.charAt(expression.length() -1);
        Matcher matcher_v = p_var.matcher(lastItem + "");
        Matcher matcher_o = p_operator.matcher(lastItem + "");
        if (matcher_v.find()) {
            return new Token("VAR", matcher_v.group());
        }
        else if (matcher_o.find()) {
            return new Token("OPERATOR", matcher_v.group());
        }
        return new Token("EMPTY_TYPE","EMPTY_DATA");
    }

    public boolean checkTokensAreValidate(Token[] tokens) {
        int token_amount = tokens.length;
        for (Token t : tokens) {
            System.out.println(">" + t.data + "," + t.type);
        }
        if (token_amount == 1) {
            if (tokens[0].type == "VAR")
                return true;
        }
        else if (token_amount == 3) {
            if (tokens[0].type == "VAR" && tokens[1].type == "EQUAL" && tokens[2].type == "VAR")
                return true;
        }
        else if (token_amount > 2 ) {
            if ((tokens[0].type.equals("VAR")) && (tokens[1].data.equals("="))) {
                String[] validate = {"VAR","OPERATOR"};
                int c = 0;
                for (int i = 2; i < tokens.length; i++) {
                    System.out.println( "N"+validate[c]);
                    System.out.println(i + "DATA:"+tokens[i].data);
                    if (tokens[i].type != validate[c]) {
                        System.out.println(validate[c]);
                        System.out.println(tokens[i].data + "," + tokens[i].type + "|" + i);
                        System.out.println("FALSE--------------------------");
                        return false;
                    }
                    c++;
                    if (c > 1) {
                        c = 0;
                    }
                }
                if(tokens[token_amount - 1].type == "OPERATOR") {
                    return false;
                }
                return true;
            }
        }
        System.out.println("FALSE--------------------------2 " + token_amount );
        return false;
    }



}
