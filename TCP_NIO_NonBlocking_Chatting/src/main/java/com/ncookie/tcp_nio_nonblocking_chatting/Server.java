package com.ncookie.tcp_nio_nonblocking_chatting;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Server extends Application {
    Selector selector;
    ServerSocketChannel serverSocketChannel;
    List<Client> connections = new Vector<Client>();


    TextArea txtDisplay;
    Button btnStartStop;


    void startServer() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(5001));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            if (serverSocketChannel.isOpen()) {
                stopServer();
            }
            return;
        }
        
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // 작업 처리 준비가 된 채널이 있을 때까지 대기
                        int keyCount = selector.select();

                        if (keyCount == 0) {
                            continue;
                        }

                        Set<SelectionKey> selectedKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectedKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            if (selectionKey.isAcceptable()) {
                                accept(selectionKey);
                            } else if (selectionKey.isReadable()) {
                                Client client = (Client) selectionKey.attachment();
                                client.receive(selectionKey);
                            } else if (selectionKey.isWritable()) {

                                Client client = (Client) selectionKey.attachment();
                                client.send(selectionKey);
                            }
                            iterator.remove();
                        }
                    } catch (Exception e) {
                        if (serverSocketChannel.isOpen()) {
                            stopServer();
                        }
                        break;
                    }
                }
            }
        };

        thread.start();

        Platform.runLater(() -> {
            displayText("[서버 시작]");
            btnStartStop.setText("stop");
        });
    }

    void stopServer() {
        try {
            Iterator<Client> iterator = connections.iterator();
            while (iterator.hasNext()) {
                Client client = iterator.next();
                client.socketChannel.close();
                iterator.remove();
            }

            if (serverSocketChannel != null && serverSocketChannel.isOpen()) {
                serverSocketChannel.close();
            }
            if (selector != null && selector.isOpen()) {
                selector.close();
            }

            Platform.runLater(() -> {
                displayText("[서버 멈춤]");
                btnStartStop.setText("start");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void accept(SelectionKey selectionKey) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();

            String message = "[연결 수락 : " + socketChannel.getRemoteAddress() + " : " +
                    Thread.currentThread().getName() + "]";
            Platform.runLater(() -> displayText(message));

            Client client = new Client(socketChannel);
            connections.add(client);

            Platform.runLater(() -> displayText("[연결 개수 : " + connections.size() + "]"));
        } catch (Exception e) {
            if (serverSocketChannel.isOpen()) {
                stopServer();
            }
        }
    }

    class Client {
        SocketChannel socketChannel;
        String sendData;

        Client(SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            socketChannel.configureBlocking(false);
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
            selectionKey.attach(this);
        }

        void receive(SelectionKey selectionKey) {
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(100);

                int byteCount = socketChannel.read(byteBuffer);
                
                // 상대방이 SocketChannel의 close() 메소드를 호출한 경우
                if (byteCount == -1) {
                    throw new IOException();
                }

                String message = "[요청 처리 : " + socketChannel.getRemoteAddress() + " : " +
                        Thread.currentThread().getName() + "]";
                Platform.runLater(() -> displayText(message));

                byteBuffer.flip();
                Charset charset = StandardCharsets.UTF_8;
                String data = charset.decode(byteBuffer).toString();

                // 모든 클라이언트에게 문자열 전송
                for (Client client : connections) {
                    client.sendData = data;
                    SelectionKey key = client.socketChannel.keyFor(selector);
                    key.interestOps(SelectionKey.OP_WRITE);     // 작업 유형 변경
                }
                // 변경된 작업 유형을 감지하도록 하기 위해 Selector의 select() 블로킹을 해제하고 다시 실행하도록 함
                selector.wakeup();
            } catch (Exception e) {
                try {
                    connections.remove(this);
                    String message = "[클라이언트 통신 안됨 : " +
                            socketChannel.getRemoteAddress() + " : " +
                            Thread.currentThread().getName() + "]";
                    Platform.runLater(() -> displayText(message));
                    socketChannel.close();
                } catch (IOException e2) {

                }
            }
        }

        void send(SelectionKey selectionKey) {
            try {
                Charset charset = StandardCharsets.UTF_8;
                ByteBuffer byteBuffer = charset.encode(sendData);
                socketChannel.write(byteBuffer);
                selectionKey.interestOps(SelectionKey.OP_READ);     // 작업 유형 변경
                selector.wakeup();
            } catch (Exception e) {
                try {
                    connections.remove(this);
                    String message = "[클라이언트 통신 안됨 : " +
                            socketChannel.getRemoteAddress() + " : " +
                            Thread.currentThread().getName() + "]";
                    Platform.runLater(() -> displayText(message));
                    socketChannel.close();
                } catch (IOException e2) {

                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = new BorderPane();
        root.setPrefSize(500, 300);

        txtDisplay = new TextArea();
        txtDisplay.setEditable(false);
        BorderPane.setMargin(txtDisplay, new Insets(0, 0, 2, 0));
        root.setCenter(txtDisplay);

        btnStartStop = new Button("start");
        btnStartStop.setPrefHeight(30);
        btnStartStop.setMaxWidth(Double.MAX_VALUE);

        btnStartStop.setOnAction(e -> {
            if (btnStartStop.getText().equals("start")) {
                startServer();
            } else if (btnStartStop.getText().equals("stop")) {
                stopServer();
            }
        });
        root.setBottom(btnStartStop);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("app.css").toString());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Server");
        primaryStage.setOnCloseRequest(event -> stopServer());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    void displayText(String text) {
        txtDisplay.appendText(text + "\n");
    }
}
