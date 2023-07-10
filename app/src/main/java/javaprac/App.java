package javaprac;


public class App {

    public static void main(String[] args) throws Exception {
        Practices.getTestingPracs().forEach(prac -> prac.run());
    }

    public String getGreeting() {
        return "Hello World!";
    }

}
