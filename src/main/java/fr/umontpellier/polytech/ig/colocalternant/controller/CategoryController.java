package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.category.Category;
import fr.umontpellier.polytech.ig.colocalternant.category.CategoryFacade;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the category management.
 */
public class CategoryController {

    /**
     * The text field of the category name
     */
    @FXML
    public TextField categoryName;
    /**
     * The button to add a category
     */
    @FXML
    public Button addCategoryButton;

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
     * Then reload the page.
     */
    @FXML
    public void addCategory(String name) {
        CategoryFacade.getInstance().addCategory(name);
        try {
            FXRouter.goTo("categoryList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles when the user clicks on the "Delete" button.
     * Then reload the page.
     */
    @FXML
    public void deleteCategory(String name) {
        CategoryFacade.getInstance().deleteCategory(name);
        try {
            FXRouter.goTo("categoryList");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Button deleteButton = createDeleteButton(category);

        VBox buttonContainer = new VBox(deleteButton);
        buttonContainer.setSpacing(5); // Optional: Set the spacing between buttons

        return buttonContainer;
    }

    /**
     * Handles when the user clicks on the "Add" button.
     * Then reload the page.
     */
    public void addCategory(ActionEvent actionEvent) {
        if (categoryName.getText().isEmpty()) {
            errorLabel.setText("Please enter a name for the category");
        } else {
            addCategory(categoryName.getText());
        }
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

    /**
     * Handles when the user clicks on the "Back" button.
     * Then go back to the main page.
     */
    public void back(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
