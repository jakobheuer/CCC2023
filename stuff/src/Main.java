import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String file = "lvl1/Files/level2_5";
        String filePath = "src/test/resources/fileTest.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }


        try {
            String filePath = "src/test/resources/fileTest.txt";
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));

            for (Question question : questionsArray) {
                if (question != null) {
                    if (firstLoop) {
                        out.println(question.questionToString());
                        firstLoop = false;
                    } else {
                        out.println();
                        out.println(question.questionToString());
                    }
                }
            }
            out.close();
        } catch (IOException e) {
            System.out.println("File Error");
            e.printStackTrace();
        }
    }
}