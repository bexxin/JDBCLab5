module com.example.beckyrutherford_comp228lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.oracle.database.jdbc;


    opens com.example.beckyrutherford_comp228lab5 to javafx.fxml;
    exports com.example.beckyrutherford_comp228lab5;
}