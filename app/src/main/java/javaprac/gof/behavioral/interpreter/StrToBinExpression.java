package javaprac.gof.behavioral.interpreter;


public class StrToBinExpression implements Expression {

    private final String expression;

    public StrToBinExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public void interpret(Context context) {
        context.getBinaryForm(expression);
    }
}
