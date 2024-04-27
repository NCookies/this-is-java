module com.example.tcp_nio_chatting {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tcp_nio_chatting to javafx.fxml;
    exports com.example.tcp_nio_chatting;
}