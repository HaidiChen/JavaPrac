package javaprac.common;


public class Manager extends Employee {

    private Employee secretary;

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
    }

    public void setSecretary(Employee employee) {
        this.secretary = employee;
    }

    @Override
    public String toString() {
        return super.toString() + "    S/he has a secretary = " + secretary.toString();
    }
}
