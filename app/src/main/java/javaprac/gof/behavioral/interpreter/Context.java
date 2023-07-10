package javaprac.gof.behavioral.interpreter;


public class Context {

    public void getBinaryForm(String input) {
        int i = Integer.parseInt(input);
        String binaryString = Integer.toBinaryString(i);
        System.out.println("Binary equivalent of " + input + " is " + binaryString);
    }
}
