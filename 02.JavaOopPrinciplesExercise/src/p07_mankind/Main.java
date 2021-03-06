package p07_mankind;


import p07_mankind.contracts.Human;
import p07_mankind.entities.classes.Student;
import p07_mankind.entities.classes.Worker;
import p07_mankind.entities.exceptions.HumanException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] studentTokens = reader.readLine().split(" ");
        String[] workerTokens = reader.readLine().split(" ");
        reader.close();

        try {
            Human student = new Student(studentTokens[0], studentTokens[1], studentTokens[2]);
            Human worker = new Worker(
                    workerTokens[0], workerTokens[1], Double.parseDouble(workerTokens[2]), Double.parseDouble(workerTokens[3])
            );

            System.out.println(student);
            System.out.println();
            System.out.println(worker);
        } catch (HumanException he) {
            System.out.println(he.getMessage());
//            he.printStackTrace();
        }

    }
}
