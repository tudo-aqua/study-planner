package view.welcome;

import service.StudyPlannerService;
import exceptions.DataNotValidException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private TextField textFieldCreditPoints;

    @FXML
    private Button buttonCreateNewStudyPlan;

    @FXML
    private Button buttonExit;

    private Stage primaryStage;

    private StudyPlannerService studyPlannerService;
    public WelcomeViewController(Stage primaryStage, StudyPlannerService spc){

        this.studyPlannerService = spc;
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


        try {
            String inputCourseOfStudy = textFieldCourseOfStudy.getText();
            int creditPoints = Integer.parseInt(textFieldCreditPoints.getText());
            studyPlannerService.initializeStudyPlanner(inputCourseOfStudy,creditPoints);
            Scene studyPlanScene = new Scene(new StudyPlanViewController(primaryStage, studyPlannerService));
            this.primaryStage.setScene(studyPlanScene);
        } catch (DataNotValidException | NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eingabefehler");
            alert.setHeaderText("Fehler bei Eingabe");
            alert.setContentText("Bitte überprüfen Sie ihre Eingabe.");
            alert.showAndWait();
        }

    }

    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}
