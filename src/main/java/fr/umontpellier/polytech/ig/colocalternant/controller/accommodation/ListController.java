/**
 * The ListController class is responsible for controlling the user interface of the accommodation list view.
 * It displays a TableView showing the list of all accommodations, with columns for title, location, description,
 * price, photo, and actions. Additionally, it provides functionality to add a new accommodation or view detailed
 * information about a selected accommodation.
 */
package fr.umontpellier.polytech.ig.colocalternant.controller.accommodation;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.Accommodation;
import fr.umontpellier.polytech.ig.colocalternant.accomodation.AccommodationFacade;
import fr.umontpellier.polytech.ig.colocalternant.category.Category;
import fr.umontpellier.polytech.ig.colocalternant.category.CategoryFacade;
import fr.umontpellier.polytech.ig.colocalternant.profile.EnumRole;
import fr.umontpellier.polytech.ig.colocalternant.profile.Profile;
import fr.umontpellier.polytech.ig.colocalternant.profile.ProfileFacade;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The ListController class handles the display of the list of accommodations.
 */
public class ListController {

    /**
     * The VBox of the view.
     */
    @FXML
    VBox box;

    @FXML
    Button add;


    @FXML
    private Button back;

    /**
     * Handles the initialization of the ListController when the view is loaded.
     */
    @FXML
    public void initialize() {
        onCreation();
        add.setText("Add an accommodation");
        add.setOnAction(event -> {
            try {
                FXRouter.goTo("insertAccommodation", getProfileID(), false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.back.setText("Back");
        this.back.setOnAction(event -> {
            try {
                FXRouter.goTo("main", getProfileID(), false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private int getProfileID() {
        Object data = FXRouter.getData();
        int profileId = (int) data;
        return profileId;
    }

    /**
     * Creates the TableView and adds it to the VBox, showing the list of all accommodations.
     */
    public void onCreation() {

        // Create a TextField for category search
        TextField categorySearchField = new TextField();
        categorySearchField.setPromptText("Search by Category");

        ArrayList<Accommodation> accommodations = AccommodationFacade.getInstance().getAllAccommodations();

        // Create TableView
        TableView<Accommodation> tableView = new TableView<>();
        tableView.setItems(FXCollections.observableArrayList(accommodations));

        // Create a button to trigger the search
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> filterByCategory(categorySearchField.getText(), tableView));

        // Create columns
        TableColumn<Accommodation, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getTitle()));

        TableColumn<Accommodation, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getLocation()));

        TableColumn<Accommodation, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Accommodation, Float> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Accommodation, Float> energicReportColumn = new TableColumn<>("Energetic Report");
        energicReportColumn.setCellValueFactory(new PropertyValueFactory<>("energicReport"));

        TableColumn<Accommodation, String> specialFonctionalitiesColumn = new TableColumn<>("Special Functionalities");
        specialFonctionalitiesColumn.setCellValueFactory(new PropertyValueFactory<>("specialFonctionalities"));


        TableColumn<Accommodation, ImageView> photoColumn = new TableColumn<>("Photo");
        photoColumn.setCellValueFactory(cellData -> {
            ImageView imageView = new ImageView();
            try {
                imageView.setImage(new Image(cellData.getValue().getPhotos()));
            } catch (IllegalArgumentException e) {
                imageView.setImage(new Image("https://www.freeiconspng.com/uploads/no-image-icon-4.png"));
            }
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            return new SimpleObjectProperty<>(imageView);
        });

        TableColumn<Accommodation, ArrayList<Category>> categoriesColumn = new TableColumn<>("Categories");
        categoriesColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCategories()));

        TableColumn<Accommodation, VBox> actionsButtonColumn = new TableColumn<>("Actions");
        actionsButtonColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(createButtonContainer(cellData.getValue())));

        TableColumn<Accommodation, VBox> requestRentalButtonColumn = new TableColumn<>("Request Rental");
        requestRentalButtonColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(createRequestRentalButtonContainer(cellData.getValue())));

        // Set column widths
        titleColumn.setMinWidth(200);
        locationColumn.setMinWidth(200);
        descriptionColumn.setMinWidth(50);
        priceColumn.setMinWidth(150);
        categoriesColumn.setMinWidth(150);
        photoColumn.setMinWidth(150);

        // Add columns to TableView
        tableView.getColumns().addAll(titleColumn, locationColumn, descriptionColumn, priceColumn, energicReportColumn, specialFonctionalitiesColumn, photoColumn, categoriesColumn, actionsButtonColumn, requestRentalButtonColumn);

        box.getChildren().removeAll();
        box.getChildren().addAll(categorySearchField, searchButton, tableView);

        // Button see all Categories
        Button seeAllCategories = new Button("See all Categories");
        seeAllCategories.setOnAction(event -> {
            TableView<Category> tableView1 = new TableView<>();
            tableView1.setItems(FXCollections.observableArrayList(getAllCategories()));

            TableColumn<Category, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().getName()));

            tableView1.getColumns().add(nameColumn);
            box.getChildren().add(tableView1);
        });
    }

    /**
     * Creates a VBox to list the actions for the given accommodation.
     *
     * @param accommodation The accommodation.
     * @return The VBox.
     */
    private VBox createButtonContainer(Accommodation accommodation) {
        Button showButton = createShowButton(accommodation);

        VBox buttonContainer = new VBox(showButton);
        buttonContainer.setSpacing(5); // Optional: Set the spacing between buttons

        return buttonContainer;
    }

    /**
     * Creates a button that redirects to the page displaying detailed information about the given accommodation.
     *
     * @param accommodation The accommodation.
     * @return The button.
     */
    private Button createShowButton(Accommodation accommodation) {
        Button button = new Button("Show");

        button.setOnAction(event -> {
            try {
                AccommodationFacade.getInstance().setCurrentAccommodation(accommodation);
                FXRouter.goTo("accommodationInfo", getProfileID(), false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }

    /**
     * Creates a VBox to contain the button for requesting a rental for the given accommodation.
     * @param accommodation The accommodation.
     */
    private VBox createRequestRentalButtonContainer(Accommodation accommodation) {
        // Get the current user's profile
        List<Profile> profiles = ProfileFacade.getInstance().getAllProfiles();
        Profile profile = profiles.stream().filter(p -> p.getId() == getProfileID()).findFirst().orElse(null);

        if (profile == null) {
            return new VBox();
        }

        if (!(profile.getRole() == EnumRole.Tenant)) {
            return new VBox();
        }
        Button requestRentalButton = createRequestRentalButton(accommodation);

        VBox buttonContainer = new VBox(requestRentalButton);
        buttonContainer.setSpacing(5); // Optional: Set the spacing between buttons

        return buttonContainer;
    }

    /**
     * Creates a button that redirects to the page redirecting to the page to request a rental for the given accommodation.
     * @param accommodation The accommodation.
     * @return The button.
     */
    private Button createRequestRentalButton(Accommodation accommodation) {
        Button button = new Button("Request Rental");

        button.setOnAction(event -> {
            try {
                AccommodationFacade.getInstance().setCurrentAccommodation(accommodation);
                FXRouter.goTo("requestRental", getProfileID(), false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }


    /**
     * Filters the list of accommodations based on the given categories.
     * @param category The category to filter on.
     * @return The filtered list of accommodations.
     */
    private void filterByCategory(String category, TableView<Accommodation> tableView) {
        ArrayList<Accommodation> allAccommodations = AccommodationFacade.getInstance().getAllAccommodations();

        if (category.isEmpty()) {
            // If the category is empty, show all accommodations
            tableView.setItems(FXCollections.observableArrayList(allAccommodations));
        } else {
            // Filter accommodations based on the entered category
            ArrayList<Accommodation> filteredAccommodations = allAccommodations.stream()
                    .filter(accommodation -> accommodation.getCategories().stream()
                            .anyMatch(cat -> cat.getName().toLowerCase().contains(category.toLowerCase())))
                    .collect(Collectors.toCollection(ArrayList::new));

            tableView.setItems(FXCollections.observableArrayList(filteredAccommodations));
        }
    }

    /**
     * Receives the list of categories to filter on from the CategoryController.
     * @return The list of categories to filter on.
     */
    public ArrayList<Category> getAllCategories() {
        return CategoryFacade.getInstance().getAllCategories();
    }
}
