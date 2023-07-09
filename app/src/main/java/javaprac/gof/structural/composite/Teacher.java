package javaprac.gof.structural.composite;

import java.util.*;


public class Teacher {

    private final String name;
    private final String role;
    private final List<Teacher> reports;

    public Teacher(String name, String role) {
        this.name = name;
        this.role = role;
        this.reports = new LinkedList<Teacher>();
    }

    public void add(Teacher teacher) {
        reports.add(teacher);
    }

    public void remove(Teacher teacher) {
        reports.remove(teacher);
    }

    public List<Teacher> getReports() {
        return reports;
    }

    @Override
    public String toString() {
        return String.format("%s is the %s and s/he has %d reports", name, role, reports.size());
    }

}
