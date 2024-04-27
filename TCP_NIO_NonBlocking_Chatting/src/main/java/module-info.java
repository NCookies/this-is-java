module com.ncookie.tcp_nio_nonblocking_chatting {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ncookie.tcp_nio_nonblocking_chatting to javafx.fxml;
    exports com.ncookie.tcp_nio_nonblocking_chatting;
}