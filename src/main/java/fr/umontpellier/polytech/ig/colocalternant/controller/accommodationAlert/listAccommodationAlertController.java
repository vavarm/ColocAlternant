package fr.umontpellier.polytech.ig.colocalternant.controller.accommodationAlert;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.accommodationAlert.AccommodationAlert;
import fr.umontpellier.polytech.ig.colocalternant.accommodationAlert.AccommodationAlertFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class listAccommodationAlertController {
    private AccommodationAlertFacade alertFacade = AccommodationAlertFacade.getInstance();

    @FXML
    private TableView<AccommodationAlert> alertTable;

    @FXML
    private TableColumn<AccommodationAlert, Integer> idCol;

    @FXML
    private TableColumn<AccommodationAlert, String> locationCol;

    @FXML
    private TableColumn<AccommodationAlert, Float> surfaceCol;

    @FXML
    private TableColumn<AccommodationAlert, Float> minPriceCol;

    @FXML
    private TableColumn<AccommodationAlert, Float> maxPriceCol;

    @FXML
    private TableColumn<AccommodationAlert, Void> deleteCol;

    @FXML
    private TableColumn<AccommodationAlert, Void> updateCol;

    @FXML
    private Button backButton;
    @FXML
    private Button alertButton;


    @FXML
    public void handleBackButtonClick(ActionEvent actionEvent) {
        System.out.println("Redirecting to main-view");
        try { FXRouter.goTo("listNotification", getProfileID(), false); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    @FXML
    public void handleCreateAlterButtonClick(ActionEvent actionEvent) {
        System.out.println("Redirecting to insertUpdateAccommodationAlert-view");
        try { FXRouter.goTo("manageAlert", getProfileID(), false); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    public void initialize() {
        backButton.setOnAction(this::handleBackButtonClick);
        alertButton.setOnAction(this::handleCreateAlterButtonClick);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        surfaceCol.setCellValueFactory(new PropertyValueFactory<>("surface"));
        minPriceCol.setCellValueFactory(new PropertyValueFactory<>("minPrice"));
        maxPriceCol.setCellValueFactory(new PropertyValueFactory<>("maxPrice"));

        List<AccommodationAlert> listAlerts = alertFacade.getOwnAccommodationAlert(getProfileID());

        deleteCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            {
                deleteButton.setOnAction(event -> {
                    AccommodationAlert alert = getTableView().getItems().get(getIndex());
                    int id = alert.getId();
                    handleDeleteButtonClick(id);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) { setGraphic(null); }
                else { setGraphic(deleteButton); }
            }
        });

        // Configuration des cellules de colonne "Update"
        updateCol.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");
            {
                updateButton.setOnAction(event -> {
                    AccommodationAlert alert = getTableView().getItems().get(getIndex());
                    handleUpdateButtonClick(alert);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) { setGraphic(null); }
                else { setGraphic(updateButton); }
            }
        });

        alertTable.getItems().setAll(listAlerts);
    }

    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }

    private void handleUpdateButtonClick(AccommodationAlert alert) {
        alertFacade.setCurrentAccommodationAlert(alert);
        System.out.println("Redirecting to insertUpdateAccommodationAlert-view");
        try { FXRouter.goTo("manageAlert", getProfileID(), true); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    private void handleDeleteButtonClick(int id) {
        alertFacade.deleteAccommodationAlert(id);
        System.out.println("ReloadlistAccommodationAlert-view");
        try { FXRouter.goTo("listAlert", getProfileID(), false); }
        catch (IOException e) { throw new RuntimeException(e); }
    }
}
