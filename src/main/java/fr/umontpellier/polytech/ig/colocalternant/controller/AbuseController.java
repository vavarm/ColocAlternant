package fr.umontpellier.polytech.ig.colocalternant.controller;

import fr.umontpellier.polytech.ig.colocalternant.FXRouter;
import fr.umontpellier.polytech.ig.colocalternant.abuse.Abuse;
import fr.umontpellier.polytech.ig.colocalternant.abuse.AbuseFacade;
import fr.umontpellier.polytech.ig.colocalternant.abuse.StatusEnum;
import fr.umontpellier.polytech.ig.colocalternant.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Controller class for handling abuse-related actions and interactions.
 */
public class AbuseController {

    @FXML
    public TextField message;
    @FXML
    public RadioButton pending;
    @FXML
    public RadioButton rejected;
    @FXML
    public RadioButton resolved;
    @FXML
    public ToggleGroup tg;
    @FXML
    public Button validate;
    @FXML
    public Label user;
    @FXML
    public Button back;

    /**
     * Default constructor for the AbuseController class.
     */
    public AbuseController() {
        this.message = new TextField();
        tg = new ToggleGroup();
        pending = new RadioButton();
        rejected = new RadioButton();
        resolved = new RadioButton();
        validate = new Button();
        user = new Label();
        back = new Button();
    }

    /**
     * Initializes the controller, setting up the user label and radio button groups.
     */
    public void initialize(){
        user.setText(AbuseFacade.getInstance().getAbuser().getFirstName() + " " + AbuseFacade.getInstance().getAbuser().getLastName());
        pending.setToggleGroup(tg);
        rejected.setToggleGroup(tg);
        resolved.setToggleGroup(tg);
    }

    /**
     * Handles the creation of an abuse when the create button is clicked.
     * @param e The click event
     */
    @FXML
    public void onCreate(ActionEvent e){
        createAbuse(message.getText(), AbuseFacade.getInstance().getAbuser());
    }

    /**
     * Handles the update of an abuse when the update button is clicked.
     * @param e The click event
     */
    @FXML
    public void onUpdate(ActionEvent e){
        System.out.println(tg.getToggles());
        System.out.println(tg.getSelectedToggle());

        String status = ((RadioButton)tg.getSelectedToggle()).getText();
        switch(status){
            case "Pending":
                updateAbuse(AbuseFacade.getInstance().getCurrentAbuse(), StatusEnum.PENDING);
                break;
            case "Rejected":
                updateAbuse(AbuseFacade.getInstance().getCurrentAbuse(), StatusEnum.REJECTED);
                break;
            case "Resolved":
                updateAbuse(AbuseFacade.getInstance().getCurrentAbuse(), StatusEnum.RESOLVED);
                break;
        }
    }

    /**
     * Handles the deletion of an abuse when the delete button is clicked.
     * @param e The click event
     */
    @FXML
    public void onDelete(ActionEvent e){
        deleteAbuse(AbuseFacade.getInstance().getCurrentAbuse());
    }

    /**
     * Handles the creation of a new abuse entry based on the provided message and destination user.
     *
     * @param message The message associated with the abuse.
     * @param dest The destination user for the abuse.
     */
    public void createAbuse(String message, User dest) {
        AbuseFacade.getInstance().createAbuse(message, dest);
    }

    /**
     * Handles the update of an existing abuse entry with the specified status.
     *
     * @param abuse The abuse entry to update.
     * @param status The new status for the abuse entry.
     */
    public void updateAbuse(Abuse abuse, StatusEnum status) {
        AbuseFacade.getInstance().updateAbuse(abuse, status);
    }

    /**
     * Handles the deletion of an existing abuse entry.
     *
     * @param abuse The abuse entry to delete.
     */
    public void deleteAbuse(Abuse abuse) {
        AbuseFacade.getInstance().deleteAbuse(abuse);
    }

    /**
     * Navigates back to the chat interface with the person associated with the current abuse entry.
     *
     * @param e The ActionEvent triggered by the back button.
     */
    @FXML
    public void backToChat(ActionEvent e){
        ChatMessageController.personToChatWith = AbuseFacade.getInstance().getAbuser();
        try {
            FXRouter.goTo("chat-message", (Integer) FXRouter.getData(), false);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Navigates back to the list of abuse entries.
     *
     * @param e The ActionEvent triggered by the back button.
     */
    @FXML
    public void backToAbusesList(ActionEvent e){
        try {
            FXRouter.goTo("abusesList", (Integer) FXRouter.getData(), false);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
