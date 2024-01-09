package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.abuse.Abuse;
import fr.umontpellier.polytech.ig.colocalternant.abuse.AbuseFacade;
import fr.umontpellier.polytech.ig.colocalternant.abuse.StatusEnum;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.Accommodation;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.AccommodationFacade;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class AbusesListController {

    @FXML
    private TableView<Abuse> abuseTable;


    private TableColumn<Abuse, Integer> idColumn;


    private TableColumn<Abuse, String> messageColumn;

    private TableColumn<Abuse, String> destColumn;

    private TableColumn<Abuse, StatusEnum> statusColumn;

    @FXML
    Button back;

    public void initialize() {
        // Initialize columns
        idColumn = new TableColumn<>();
        messageColumn = new TableColumn<>();
        destColumn = new TableColumn<>();
        statusColumn = new TableColumn<>();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        destColumn.setCellValueFactory(new PropertyValueFactory<>("dest"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Populate table with all abuses
        List<Abuse> allAbuses = AbuseFacade.getInstance().getAllAbuses();
        ObservableList<Abuse> abusesObservableList = FXCollections.observableArrayList(allAbuses);
        abuseTable.setItems(abusesObservableList);
        TableColumn<Abuse, VBox> actionsButtonColumn = new TableColumn<>("Actions");
        actionsButtonColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(createButtonContainer(cellData.getValue())));
        abuseTable.getColumns().addAll(idColumn, messageColumn, destColumn, statusColumn, actionsButtonColumn);
        back.setText("Back");
        back.setOnAction(event -> {
            try {
                FXRouter.goTo("main");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    /**
     * Creates a VBox to list the actions for the given abuse.
     *
     * @param abuse The abuse.
     * @return The VBox.
     */
    private VBox createButtonContainer(Abuse abuse) {
        Button updateButton = createUpdateButton(abuse);
        Button deleteButton = createDeleteButton(abuse);

        VBox buttonContainer = new VBox(updateButton, deleteButton);
        buttonContainer.setSpacing(5); // Optional: Set the spacing between buttons

        return buttonContainer;
    }

    /**
     * Creates a button that redirects to the page allowing the admin to handle the given abuse.
     *
     * @param abuse The abuse.
     * @return The button.
     */
    private Button createUpdateButton(Abuse abuse) {
        Button button = new Button("Handle");

        button.setOnAction(event -> {
            try {
                AbuseFacade.getInstance().setCurrentAbuse(abuse);
                AbuseFacade.getInstance().setAbuser(abuse.getDest());
                FXRouter.goTo("updateAbuse");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }

    /**
     * Creates a button that redirects to the page allowing the admin to delete the given abuse.
     *
     * @param abuse The abuse.
     * @return The button.
     */
    private Button createDeleteButton(Abuse abuse) {
        Button button = new Button("Delete");

        button.setOnAction(event -> {
            try {
                AbuseFacade.getInstance().setCurrentAbuse(abuse);
                AbuseFacade.getInstance().setAbuser(abuse.getDest());

                FXRouter.goTo("deleteAbuse");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }

    @FXML
    public void refreshTable(ActionEvent event) {
        // Refresh the table content (you can call this method when needed)
        List<Abuse> allAbuses = AbuseFacade.getInstance().getAllAbuses();
        ObservableList<Abuse> abusesObservableList = FXCollections.observableArrayList(allAbuses);
        abuseTable.setItems(abusesObservableList);
    }
}
