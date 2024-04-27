package Chapter18.Ex07;

import java.io.BufferedReader;
import java.io.FileReader;

public class AddLineNumberExample {
    public static void main(String[] args) {
        String filePath = "D:\\0_TIL\\Java\\ThisIsJava\\src\\main\\java\\Chapter18\\Ex07\\AddLineNumberExample.java";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String s;
            int line = 1;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.printf("%02d", line);
                System.out.println(": " + s);
                line++;
            }

            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
