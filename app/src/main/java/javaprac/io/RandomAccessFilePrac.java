package javaprac.io;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

import javaprac.Prac;
import javaprac.common.*;


public class RandomAccessFilePrac implements Prac {

    private static final String FILE_PATH = "src/main/resources/random_access_file_prac";

    @Override
    public void runPrac() {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Harry Hacker", 40000,  1989, 10, 1);
        staff[1] = new Employee("Creak Craker", 90000,  1980, 1, 1);
        staff[2] = new Employee("Polle Pauler", 33000,  1999, 9, 1);

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(FILE_PATH))) {
            writeData(staff, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (RandomAccessFile in = new RandomAccessFile(FILE_PATH, "r")) {
            Employee[] newStaff = readData(in);
            Arrays.stream(newStaff).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice I/O - read & write bytes to file using RandomAccessFile.";
    }

    private void writeData(Employee[] employees, DataOutput out) throws IOException {
        for (Employee e : employees) {
            writeEmployee(out, e);
        }
    }

    private void writeEmployee(DataOutput out, Employee employee) throws IOException {
        writeFixedString(employee.getName(), Employee.NAME_SIZE_CHARS, out);

        out.writeDouble(employee.getSalary());
        LocalDate hireDay = employee.getHireDay();
        out.writeInt(hireDay.getYear());
        out.writeInt(hireDay.getMonthValue());
        out.writeInt(hireDay.getDayOfMonth());
    }

    private void writeFixedString(String s, int size, DataOutput out) throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if (i < s.length()) {
                ch = s.charAt(i);
            }
            out.writeChar(ch);
        }
    }

    private Employee[] readData(RandomAccessFile in) throws IOException {
        int n = (int) (in.length() / Employee.RECORD_SIZE_BYTES);
        Employee[] employees = new Employee[n];

        for (int i = n - 1; i >= 0; i--) {
            in.seek(i * Employee.RECORD_SIZE_BYTES);
            employees[i] = readEmployee(in);
        }

        return employees;
    }

    private Employee readEmployee(DataInput in) throws IOException {
        String name = readFixedString(Employee.NAME_SIZE_CHARS, in);
        double salary = in.readDouble();
        int year = in.readInt();
        int month = in.readInt();
        int day = in.readInt();

        return new Employee(name, salary, year, month, day);
    }

    private String readFixedString(int size, DataInput in) throws IOException {
        StringBuilder b = new StringBuilder(size);

        int i = 0;
        boolean more = true;

        while (more && i < size) {
            char ch = in.readChar();
            i++;
            if (ch == 0) {
                more = false;
            } else {
                b.append(ch);
            }
        }
        in.skipBytes(2 * (size - i));

        return b.toString();
    }
}
