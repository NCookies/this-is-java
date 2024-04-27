package Chapter18.FileIO.FileOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample {
    public static void main(String[] args) throws IOException {
        String originFileName = "./test_img.jpg";
        String targetFileName = "./test_img_copy.jpg";

        FileInputStream fis = new FileInputStream(originFileName);
        FileOutputStream fos = new FileOutputStream(targetFileName);

        int readByteNo;
        byte[] readBytes = new byte[100];
        while ( (readByteNo = fis.read(readBytes)) != 0) {
            fos.write(readBytes, 0, readByteNo);
        }
        
        fos.flush();
        fos.close();
        fis.close();

        System.out.println("복사 완료");
    }
}
