module com.pds.curiousmind {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.pds.curiousmind.home to javafx.fxml;
    opens com.pds.curiousmind.signup to javafx.fxml;
    exports com.pds.curiousmind.home;
    exports com.pds.curiousmind.login;
    exports com.pds.curiousmind.signup;
    opens com.pds.curiousmind.login to javafx.fxml;

}