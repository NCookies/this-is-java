package Chapter18.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UdpSendExample {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket();
        System.out.println("[발신 시작]");

        for (int i = 0; i < 3; i++) {
            String data = "메세지 : " + i;
            byte[] byteArr = data.getBytes(StandardCharsets.UTF_8);
            DatagramPacket datagramPacket = new DatagramPacket(
                    byteArr, byteArr.length,
                    new InetSocketAddress("localhost", 5001)
            );

            datagramSocket.send(datagramPacket);
            System.out.println("[보낸 바이트 수] : " + byteArr.length + " bytes");
        }

        System.out.println("[발신 종료]");
        datagramSocket.close();
    }
}
