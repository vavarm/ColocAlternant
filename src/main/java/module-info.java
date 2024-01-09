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
    requires java.desktop;

    opens fr.umontpellier.polytech.ig.colocalternant to javafx.fxml;
        opens fr.umontpellier.polytech.ig.colocalternant.controller to javafx.fxml;
    exports fr.umontpellier.polytech.ig.colocalternant.controller;

    opens fr.umontpellier.polytech.ig.colocalternant.controller.accommodation to javafx.fxml;
    exports fr.umontpellier.polytech.ig.colocalternant.accomodation;

    opens fr.umontpellier.polytech.ig.colocalternant.controller.profile to javafx.fxml;
    exports fr.umontpellier.polytech.ig.colocalternant.controller.profile;

    opens fr.umontpellier.polytech.ig.colocalternant.profile to javafx.base;
    exports fr.umontpellier.polytech.ig.colocalternant.profile;    

    opens fr.umontpellier.polytech.ig.colocalternant.controller.accommodationAlert to javafx.fxml;
    exports fr.umontpellier.polytech.ig.colocalternant.controller.accommodationAlert;

    opens fr.umontpellier.polytech.ig.colocalternant.accommodationAlert to javafx.base;
    exports fr.umontpellier.polytech.ig.colocalternant.accommodationAlert;

    exports fr.umontpellier.polytech.ig.colocalternant.notification;
    exports fr.umontpellier.polytech.ig.colocalternant.user;
    exports fr.umontpellier.polytech.ig.colocalternant.controller.accommodation;
    exports fr.umontpellier.polytech.ig.colocalternant.chat;
    exports fr.umontpellier.polytech.ig.colocalternant;
}