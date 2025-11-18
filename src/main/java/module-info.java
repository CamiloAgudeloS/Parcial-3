module com.example.parcial3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.example.parcial3;
    requires javafx.base;


    opens com.example.parcial3 to javafx.fxml;
    exports com.example.parcial3;
    exports com.example.parcial3.Controllers;
    opens com.example.parcial3.Controllers to javafx.fxml;
}