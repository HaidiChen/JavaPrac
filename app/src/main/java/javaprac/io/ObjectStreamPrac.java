package javaprac.io;

import java.io.*;
import java.util.*;
import java.time.*;

import javaprac.Prac;
import javaprac.common.*;


public class ObjectStreamPrac implements Prac {

    private static final String FILE_PATH = "src/main/resources/object_stream_prac";

    @Override
    public void runPrac() {
        Employee harry = new Employee("Harry Hacker", 40000,  1989, 10, 1);

        Manager creak = new Manager("Creak Craker", 90000,  1980, 1, 1);
        creak.setSecretary(harry);

        Manager polle = new Manager("Polle Pauler", 33000,  1999, 9, 1);
        polle.setSecretary(harry);

        Employee[] staff = new Employee[3];
        staff[0] = creak;
        staff[1] = harry;
        staff[2] = polle;

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(staff);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Employee[] newStaff = (Employee[]) in.readObject();

            newStaff[1].raiseSalary(10);
            Arrays.stream(newStaff).forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Practice I/O - write & read objects to & from file.";
    }
}
