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
 * 
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
     * Default constructor
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

    public void initialize(){
        user.setText(AbuseFacade.getInstance().getAbuser().getFirstName() + " " + AbuseFacade.getInstance().getAbuser().getLastName());
        pending.setToggleGroup(tg);
        rejected.setToggleGroup(tg);
        resolved.setToggleGroup(tg);

    }
    @FXML
    public void onCreate(ActionEvent e){
        createAbuse(message.getText(), AbuseFacade.getInstance().getAbuser());
    }

    @FXML
    public void onUpdate(ActionEvent e){
        System.out.println(tg.getToggles());
        System.out.println(tg.getSelectedToggle());

        String status = tg.getSelectedToggle().toString();
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

    @FXML
    public void onDelete(ActionEvent e){
        deleteAbuse(AbuseFacade.getInstance().getCurrentAbuse());
    }


    /**
     * @param message 
     * @param dest 
     * @return
     */
    public void createAbuse(String message, User dest) {
        AbuseFacade.getInstance().createAbuse(message, dest);
    }

    /**
     * @param abuse 
     * @param status 
     * @return
     */
    public void updateAbuse(Abuse abuse, StatusEnum status) {
        AbuseFacade.getInstance().updateAbuse(abuse, status);
    }

    /**
     * @param abuse 
     * @return
     */
    public void deleteAbuse(Abuse abuse) {
        AbuseFacade.getInstance().deleteAbuse(abuse);
    }

    @FXML
    public void backToChat(ActionEvent e){
        ChatMessageController.personToChatWith = AbuseFacade.getInstance().getAbuser();
        try {
            FXRouter.goTo("chat-message");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void backToProfile(ActionEvent e){

    }

    @FXML
    public void backToAbusesList(ActionEvent e){
        try {
            FXRouter.goTo("abusesList");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }



}