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

        // Add columns to the TableView
        tableView.getColumns().add(nameColumn);
        box.getChildren().add(tableView);
    }

}
