module com.infix.topostfix.dataproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.infix.topostfix.dataproject to javafx.fxml;
    exports com.infix.topostfix.dataproject;
}