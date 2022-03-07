module com.example.conventer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.conventer to javafx.fxml;
    exports com.example.conventer;
}