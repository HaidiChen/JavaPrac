package javaprac.gof.behavioral.interpreter;

import javaprac.Prac;


public class InterpreterPrac implements Prac {

    @Override
    public void runPrac() {
        Context context = new Context();
        Expression expression = new StrToBinExpression("12345");
        expression.interpret(context);
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [behavioral] Interpreter";
    }
}
