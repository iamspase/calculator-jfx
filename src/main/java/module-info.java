module com.iamspase.calculatorjfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.iamspase.calculatorjfx to javafx.fxml;
    exports com.iamspase.calculatorjfx;
}