package Chapter18.Ex11;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerExample {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 5001));

        System.out.println("[서버 시작]");

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                InputStream is = socket.getInputStream();

                byte[] bytes = new byte[1000];
                int readByteCount = is.read(bytes, 0, 100);
                String fileName = new String(bytes, 0, 100, StandardCharsets.UTF_8).trim();

                System.out.println("[파일 받기 시작] " + fileName);
                FileOutputStream fos = new FileOutputStream("C:\\Temp\\" + fileName);
                while ((readByteCount = is.read(bytes)) != -1) {
                    fos.write(bytes, 0, readByteCount);
                }
                System.out.println("[파일 받기 완료]");

                fos.close();
                is.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        serverSocket.close();
        System.out.println("[서버 종료]");
    }
}
