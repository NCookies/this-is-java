module com.ncookie.tcp_chatting {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ncookie.tcp_chatting to javafx.fxml;
    exports com.ncookie.tcp_chatting;
}