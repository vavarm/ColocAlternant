module fr.umontpellier.polytech.ig.colocalternant {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.testng;
    requires junit;

    opens fr.umontpellier.polytech.ig.colocalternant to javafx.fxml;
    exports fr.umontpellier.polytech.ig.colocalternant;
    exports fr.umontpellier.polytech.ig.colocalternant.user;
}