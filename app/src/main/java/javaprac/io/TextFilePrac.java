package javaprac.io;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

import javaprac.Prac;
import javaprac.common.*;


public class TextFilePrac implements Prac {

    private static final String FILE_PATH = "src/main/resources/text_file_prac";

    @Override
    public void runPrac() {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Harry Hacker", 40000,  1989, 10, 1);
        staff[1] = new Employee("Creak Craker", 90000,  1980, 1, 1);
        staff[2] = new Employee("Polle Pauler", 33000,  1999, 9, 1);

        try (PrintWriter out = new PrintWriter(FILE_PATH, "UTF-8")) {
            writeData(staff, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner in = new Scanner(new FileInputStream(FILE_PATH), "UTF-8")) {
            Employee[] newStaff = readData(in);

            Arrays.stream(newStaff).forEach(System.out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice I/O - read & write text files.";
    }

    private void writeData(Employee[] employees, PrintWriter out) throws IOException {
        out.println(employees.length);

        for (Employee e : employees) {
            writeEmployee(out, e);
        }
    }

    private void writeEmployee(PrintWriter out, Employee employee) throws IOException {
        out.println(employee.getName() + "|" + employee.getSalary() + "|" + employee.getHireDay());
    }

    private Employee[] readData(Scanner in) {
        int n = in.nextInt();
        // nextInt() will not take the newline symbol e.g., '\n' and reader stays in the same line.
        // so nextLine() should be called to consume that symbol and move to the next line
        in.nextLine();

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(in);
        }

        return employees;
    }

    private Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDay = LocalDate.parse(tokens[2]);
        int year = hireDay.getYear();
        int month = hireDay.getMonthValue();
        int day = hireDay.getDayOfMonth();

        return new Employee(name, salary, year, month, day);
    }
}
