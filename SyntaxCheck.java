public class SyntaxCheck {

    SyntaxCheck() {}

    public boolean checkthesyntax(String program) {
        String[] splittedSyntax = program.split(" ");
        System.out.println(splittedSyntax);

        SyntaxAnalyser programTokens = new SyntaxAnalyser();
        programTokens.program = program;
        //programTokens.lexExpression(program);
        boolean isProgramCorrected = programTokens.isLanguageCorrect();
        if (isProgramCorrected) {
            System.out.println("BEGIN_END -> [+]");
            programTokens.program = programTokens.program.replace("begin", "");
            programTokens.program = programTokens.program.replace("end", "");
            programTokens.program = programTokens.program.trim();
            System.out.println("ALL_EXPRESSIONS_FOUND:"+programTokens.program);
        }
        boolean all_lexedExpression = programTokens.lexAllExpressions();
        System.out.println(all_lexedExpression);
        return (all_lexedExpression && isProgramCorrected);
    }


}
