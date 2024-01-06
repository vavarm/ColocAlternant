package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.category.Category;
import fr.umontpellier.polytech.ig.colocalternant.category.CategoryFacade;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the category management.
 */
public class CategoryController {

    /**
     * The VBox of the view
     */
    @FXML
    VBox box;

    /**
     * The label of the error message
     */
    @FXML
    public Label errorLabel;

    /**
     * Handles when the user loads the view.
     */
    public void initialize() {
        getAllCategories();
    }

    /**
     * Handles when the user clicks on the "Add" button.
     */
    @FXML
    public void addCategory(String name) {
        errorLabel.setText(CategoryFacade.getInstance().addCategory(name));
    }

    /**
     * Handles when the user clicks on the "Delete" button.
     */
    @FXML
    public void deleteCategory(String name) {
        errorLabel.setText(CategoryFacade.getInstance().deleteCategory(name));
    }

    /**
     * Handles on load.
     */
    public void getAllCategories() {
        ArrayList<Category> categories = CategoryFacade.getInstance().getAllCategories();

        // Create TableView
        TableView<Category> tableView = new TableView<>();
        tableView.setItems(FXCollections.observableArrayList(categories));

        // Create columns
        TableColumn<Category, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getName()));

        TableColumn<Category, VBox> actionsButtonColumn = new TableColumn<>("Actions");
        actionsButtonColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(createButtonContainer(cellData.getValue())));

        // Add columns to the TableView
        tableView.getColumns().addAll(nameColumn, actionsButtonColumn);
        box.getChildren().add(tableView);
    }

    /**
     * Create a VBox to list the actions for the given category.
     * @param category The category.
     * @return The VBox.
     */
    private VBox createButtonContainer(Category category) {
        Button banButton = createAddButton(category);
        Button unbanButton = createDeleteButton(category);

        VBox buttonContainer = new VBox(banButton, unbanButton);
        buttonContainer.setSpacing(5); // Optional: Set the spacing between buttons

        return buttonContainer;
    }

    /**
     * Create a button to add the given category.
     * @param category The category.
     * @return The button.
     */
    private Button createAddButton(Category category) {
        Button button = new Button("Add");
        button.setOnAction(event -> {
            addCategory(category.getName());
        });
        return button;
    }

    /**
     * Create a button to delete the given category.
     * @param category The category.
     * @return The button.
     */
    private Button createDeleteButton(Category category) {
        Button button = new Button("Delete");
        button.setOnAction(event -> {
            deleteCategory(category.getName());
        });
        return button;
    }

}
