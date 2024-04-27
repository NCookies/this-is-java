package Chapter19.Buffer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ByteBufferToStringExample {
    public static void main(String[] args) {
        Charset charset = StandardCharsets.UTF_8;

        // 문자열 -> 인코딩 -> ByteBuffer
        String data = "안녕하세요";
        ByteBuffer byteBuffer = charset.encode(data);
        
        // ByteBuffer -> 디코딩 -> CharBuffer -> 문자열
        data = charset.decode(byteBuffer).toString();
        System.out.println("문자열 복원 : " + data);
    }
}
