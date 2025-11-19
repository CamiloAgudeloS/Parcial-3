module com.example.parcial3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;


    opens com.example.parcial3 to javafx.fxml;
    opens com.example.parcial3.Models to javafx.base;
    exports com.example.parcial3;
    exports com.example.parcial3.Controllers;
    opens com.example.parcial3.Controllers to javafx.fxml;
}