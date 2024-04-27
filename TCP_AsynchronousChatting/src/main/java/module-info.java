module com.ncookie.tcp_asynchronouschatting {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ncookie.tcp_asynchronouschatting to javafx.fxml;
    exports com.ncookie.tcp_asynchronouschatting;
}