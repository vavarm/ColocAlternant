package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.abuse.Abuse;
import fr.umontpellier.polytech.ig.colocalternant.abuse.AbuseFacade;
import fr.umontpellier.polytech.ig.colocalternant.abuse.StatusEnum;
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

/**
 * Controller class for managing the list of abuse entries and associated actions.
 */
public class AbusesListController {

    @FXML
    private TableView<Abuse> abuseTable;

    private TableColumn<Abuse, Integer> idColumn;
    private TableColumn<Abuse, String> messageColumn;
    private TableColumn<Abuse, String> destColumn;
    private TableColumn<Abuse, StatusEnum> statusColumn;

    @FXML
    Button back;

    /**
     * Initializes the controller, setting up the table columns, populating the table with abuse entries,
     * and configuring actions for handling and deleting abuses.
     */
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

        // Create actions column with handle and delete buttons
        TableColumn<Abuse, VBox> actionsButtonColumn = new TableColumn<>("Actions");
        actionsButtonColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(createButtonContainer(cellData.getValue())));
        abuseTable.getColumns().addAll(idColumn, messageColumn, destColumn, statusColumn, actionsButtonColumn);

        // Set up back button
        back.setText("Back");
        back.setOnAction(event -> {
            try {
                FXRouter.goTo("main", getProfileID(), false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Creates a VBox to list the actions for the given abuse.
     *
     * @param abuse The abuse.
     * @return The VBox containing action buttons.
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
     * @return The "Handle" button.
     */
    private Button createUpdateButton(Abuse abuse) {
        Button button = new Button("Handle");

        button.setOnAction(event -> {
            try {
                AbuseFacade.getInstance().setCurrentAbuse(abuse);
                AbuseFacade.getInstance().setAbuser(abuse.getDest());
                FXRouter.goTo("updateAbuse", getProfileID(), false);
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
     * @return The "Delete" button.
     */
    private Button createDeleteButton(Abuse abuse) {
        Button button = new Button("Delete");

        button.setOnAction(event -> {
            try {
                AbuseFacade.getInstance().setCurrentAbuse(abuse);
                AbuseFacade.getInstance().setAbuser(abuse.getDest());
                FXRouter.goTo("deleteAbuse", getProfileID(), false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }

    /**
     * Refreshes the content of the table by fetching the latest list of abuses.
     *
     * @param event The ActionEvent triggered by the refresh button.
     */
    @FXML
    public void refreshTable(ActionEvent event) {
        List<Abuse> allAbuses = AbuseFacade.getInstance().getAllAbuses();
        ObservableList<Abuse> abusesObservableList = FXCollections.observableArrayList(allAbuses);
        abuseTable.setItems(abusesObservableList);
    }

    /**
     * Retrieves the profile ID from the router's data.
     *
     * @return The profile ID.
     */
    private int getProfileID() {
        Object data = FXRouter.getData();
        return (int) data;
    }
}
