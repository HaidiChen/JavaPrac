package javaprac.common;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * Serializable interface is a tagging interface which has no methods declared in it.
 * Mainly used for persisitng objects to disk.
 */
public class Employee implements Serializable {

    public static final int NAME_SIZE_CHARS = 12;
    public static final int RECORD_SIZE_BYTES = NAME_SIZE_CHARS * 2 + 20;

    private final String name;
    private double salary;
    private final LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return "Employee[name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
    }
}
