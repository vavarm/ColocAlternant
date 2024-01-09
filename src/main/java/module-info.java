/**
 * Module info for the application.
 */
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
    opens fr.umontpellier.polytech.ig.colocalternant.profile to javafx.base;
    exports fr.umontpellier.polytech.ig.colocalternant;
    exports fr.umontpellier.polytech.ig.colocalternant.user;
    exports fr.umontpellier.polytech.ig.colocalternant.controller;
    exports fr.umontpellier.polytech.ig.colocalternant.accomodation;
    exports fr.umontpellier.polytech.ig.colocalternant.profile;
    exports fr.umontpellier.polytech.ig.colocalternant.rental;
    opens fr.umontpellier.polytech.ig.colocalternant.controller to javafx.fxml;
    exports fr.umontpellier.polytech.ig.colocalternant.controller.accommodation;
    opens fr.umontpellier.polytech.ig.colocalternant.controller.accommodation to javafx.fxml;
    exports fr.umontpellier.polytech.ig.colocalternant.controller.profile;
    opens fr.umontpellier.polytech.ig.colocalternant.controller.profile to javafx.fxml;
    exports fr.umontpellier.polytech.ig.colocalternant.controller.rental;
    opens fr.umontpellier.polytech.ig.colocalternant.controller.rental to javafx.fxml;

    exports fr.umontpellier.polytech.ig.colocalternant.chat;
}