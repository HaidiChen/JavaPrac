package javaprac.gof.creational.singleton;


public class Captain {

    private static volatile Captain instance;

    private final String name;
    private final String city;
    private final int age;

    private Captain(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public static Captain create() {
        if (instance == null) {
            synchronized(Captain.class) {
                if (instance == null) {
                    instance = new Captain("Hawk", "Big City", 40);
                }
            }
        }

        return instance;
    }

    @Override
    public String toString() {
        return String.format("%d years old Captain %s lives in %s.", age, name, city);
    }
}
