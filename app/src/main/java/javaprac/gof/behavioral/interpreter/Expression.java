package javaprac.gof.behavioral.interpreter;


public interface Expression {

    default void interpret(Context context) {
        throw new UnsupportedOperationException("Needs Implementation.");
    }
}
