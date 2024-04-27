package Chapter18.Ex11;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ClientExample {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5001);
        OutputStream os = socket.getOutputStream();

        String filePath = "C:\\Users\\swsta\\OneDrive\\사진\\SCAN0001-1.jpg";
        File file = new File(filePath);

        byte[] bytes = null;
        String message = null;

        String fileName = file.getName();

        message = fileName;
        bytes = message.getBytes(StandardCharsets.UTF_8);
        bytes = Arrays.copyOf(bytes, 100);
        os.write(bytes);

        System.out.println("[파일 이름 송신 완료]");

        System.out.println("[파일 데이터 보내는 중...]");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] readBytes = new byte[1000];
        int readByteNo;

        while ((readByteNo = fileInputStream.read(readBytes)) != -1) {
            os.write(readBytes);
        }

        os.flush();

        System.out.println("[파일 보내기 완료]");

        fileInputStream.close();
        os.close();
        socket.close();
    }
}
