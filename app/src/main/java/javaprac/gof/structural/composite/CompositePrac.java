package javaprac.gof.structural.composite;

import java.util.*;

import javaprac.Prac;


public class CompositePrac implements Prac {

    @Override
    public void runPrac() {
        Teacher principal = new Teacher("Dr. Tom", "Principal");
        Teacher mathDeptHead = new Teacher("Dr. Sam", "Head of Math Dept");
        Teacher computerSciDeptHead = new Teacher("Dr. Wu", "Head of Computer Science");

        Teacher mathTeacher1 = new Teacher("Prof. Ki", "Teacher in Math Dept");
        Teacher mathTeacher2 = new Teacher("Prof. Aka", "Teacher in Math Dept");

        Teacher computerSciTeacher1 = new Teacher("Prof. Su", "Teacher in ComputerScience Dept");
        Teacher computerSciTeacher2 = new Teacher("Prof. Yika", "Teacher in ComputerScience Dept");
        Teacher computerSciTeacher3 = new Teacher("Prof. Eack", "Teacher in ComputerScience Dept");

        principal.add(mathDeptHead);
        principal.add(computerSciDeptHead);

        mathDeptHead.add(mathTeacher1);
        mathDeptHead.add(mathTeacher2);

        computerSciDeptHead.add(computerSciTeacher1);
        computerSciDeptHead.add(computerSciTeacher2);
        computerSciDeptHead.add(computerSciTeacher3);

        printDirectory(principal);

        computerSciDeptHead.remove(computerSciTeacher3);

        System.out.println("After Prof. Eack left:");
        printDirectory(principal);
    }

    @Override
    public String getDescription() {
        return "Design pattern - [structural] Composite";
    }

    private void printDirectory(Teacher root) {
        System.out.println(root);
        List<Teacher> reports = root.getReports();

        reports.forEach(this::printDirectory);
    }
}
