package view.welcome;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.studyplan.StudyPlanViewController;

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

    private Stage primaryStage;
    public WelcomeViewController(Stage primaryStage){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            this.primaryStage = primaryStage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void createNewStudyPlan(ActionEvent event) {
        Scene studyPlanScene = new Scene(new StudyPlanViewController(primaryStage));
        this.primaryStage.setScene(studyPlanScene);
    }

    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}
