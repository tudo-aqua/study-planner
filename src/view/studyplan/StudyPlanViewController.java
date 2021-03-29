package view.studyplan;


import controller.StudyPlannerController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Semester;
import view.moduledetail.ModuleDetailViewController;
import view.semesterdetail.SemesterDetailViewController;
import view.semesterview.SemesterViewController;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StudyPlanViewController extends GridPane {

    @FXML
    private Label labelTitel;

    @FXML
    private Label labelAvgGrade;

    @FXML
    private Label labelCollectedECTS;

    @FXML
    private MenuItem buttonAddSemester;

    @FXML
    private MenuItem buttonModifySemester;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private HBox hBoxSemesterContainer;

    @FXML
    private Button buttonAddModule;

    private Stage primaryStage;

    private StudyPlannerController studyPlannerController;

    public StudyPlanViewController(Stage primaryStage, StudyPlannerController spc){
        this.studyPlannerController = spc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudyPlanView.fxml"));
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
    public void initialize() {
        labelAvgGrade.textProperty().bind(studyPlannerController.getStudyPlanner().getStatistics().avgGradeProperty().asString("%.1f"));
        labelCollectedECTS.textProperty().bind(studyPlannerController.getStudyPlanner().getStatistics().collectedCreditPointsProperty().asString("%d / "+studyPlannerController.getStudyPlanner().getCourseOfStudyCreditPoints()+ " Leistungspunkte"));
        labelTitel.setText("Studienverlaufsplan - " + studyPlannerController.getStudyPlanner().getCourseOfStudyName());
        for(Semester semester: this.studyPlannerController.getStudyPlanner().getSemesters())
           hBoxSemesterContainer.getChildren().add(new SemesterViewController(studyPlannerController,semester));

        progressBar.progressProperty().bind(this.studyPlannerController.getStudyPlanner().getStatistics().collectedCreditPointsProperty().divide((double) studyPlannerController.getStudyPlanner().getCourseOfStudyCreditPoints()));
        Tooltip tooltip = new Tooltip();
        tooltip.setText(
                "Neues Modul erstellen"
        );
        buttonAddModule.setTooltip(tooltip);

    }

    @FXML
    void addModule(ActionEvent event) {

        if(studyPlannerController.getStudyPlanner().getSemesters().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hinweis");
            alert.setHeaderText("Semester erstellen");
            alert.setContentText("Bitte zuerst ein Semester erstellen.");
            alert.showAndWait();
        }
        else{
            Stage stage = new Stage();
            Scene newSemesterScene = new Scene(new ModuleDetailViewController(studyPlannerController));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            stage.setScene(newSemesterScene);
            stage.showAndWait();
        }

    }

    @FXML
    void addSemester(ActionEvent event) {
        Stage stage = new Stage();
        Scene newSemesterScene = new Scene(new SemesterDetailViewController(studyPlannerController,false, hBoxSemesterContainer));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setScene(newSemesterScene);
        stage.showAndWait();
    }

    @FXML
    void modifySemester(ActionEvent event) {
        Stage stage = new Stage();
        Scene modifySemesterScene = new Scene(new SemesterDetailViewController(studyPlannerController,true, null));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.initOwner(primaryStage);
        stage.setScene(modifySemesterScene);
        stage.showAndWait();
    }



    @FXML
    void help(ActionEvent event) {
        try {

            Desktop.getDesktop().open(new File("Produktbeschreibung_StudyPlanner.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exit(ActionEvent event) {
        this.studyPlannerController.getIoController().storeData();
        Platform.exit();
        System.exit(0);
    }

}
