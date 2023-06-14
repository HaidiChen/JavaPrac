package javaprac.interfaces;

import java.util.Date;
import java.util.GregorianCalendar;

import javaprac.Prac;
import javaprac.common.*;


public class CloneablePrac implements Prac {

    @Override
    public String getDescription() {
        return "Practice interface : Cloneable";
    }

    @Override
    public void runPrac() {
        try {
            Employee originalObj = new Employee("Hello", 5000);
            originalObj.setHireDay(2020, 2, 2);
            Employee copiedObj = originalObj.clone();
            copiedObj.raiseSalary(10);
            copiedObj.setHireDay(2222, 10, 10);
            System.out.println("original = " + originalObj);
            System.out.println("copy = " + copiedObj);
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Cloneable interface is a tagging interface, i.e., it has no method declaration in it.
     * Mainly used for deep copy so that mutable Object type field members could be copied correctly.
     * However, it's recommended not to implement this interface if possible.
     */
    private class Employee implements Cloneable {

        private String name;
        private double salary;
        private Date hireDay;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
            this.hireDay = new Date();
        }

        @Override
        public Employee clone() throws CloneNotSupportedException {
            Employee cloned = (Employee) super.clone();
            cloned.hireDay = (Date) hireDay.clone();

            return cloned;
        }

        public void setHireDay(int year, int month, int day) {
            Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
            hireDay.setTime(newHireDay.getTime());
        }

        public void raiseSalary(double byPercent) {
            double raise = salary * byPercent / 100;
            salary += raise;
        }

        @Override
        public String toString() {
            return "Employee[name=" + name + ", salary=" + salary + ",hireDay=" + hireDay + "]";
        }
    }
}
