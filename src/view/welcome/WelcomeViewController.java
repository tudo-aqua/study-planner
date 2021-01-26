package view.welcome;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class WelcomeViewController extends GridPane {

    @FXML
    private TextField textFieldCourseOfStudy;

    @FXML
    private TextField textFieldECTS;

    @FXML
    private Button buttonCreateNewStudyPlan;

    @FXML
    private Button buttonExit;

    public WelcomeViewController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void createNewStudyPlan(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}
