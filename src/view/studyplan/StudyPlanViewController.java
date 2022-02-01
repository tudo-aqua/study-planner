package view.studyplan;


import service.StudyPlannerService;
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
import entity.Semester;
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
    private Label labelCollectedCreditPoints;

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

    private StudyPlannerService studyPlannerService;

    public StudyPlanViewController(Stage primaryStage, StudyPlannerService spc){
        this.studyPlannerService = spc;
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
        labelAvgGrade.textProperty().bind(studyPlannerService.getStudyPlanner().getStatistics().avgGradeProperty().asString("%.1f"));
        labelCollectedCreditPoints.textProperty().bind(studyPlannerService.getStudyPlanner().getStatistics().collectedCreditPointsProperty().asString("%d / "+ studyPlannerService.getStudyPlanner().getCourseOfStudyCreditPoints()+ " Leistungspunkte"));
        labelTitel.setText("Studienverlaufsplan - " + studyPlannerService.getStudyPlanner().getCourseOfStudyName());
        for(Semester semester: this.studyPlannerService.getStudyPlanner().getSemesters())
           hBoxSemesterContainer.getChildren().add(new SemesterViewController(studyPlannerService,semester));

        progressBar.progressProperty().bind(this.studyPlannerService.getStudyPlanner().getStatistics().collectedCreditPointsProperty().divide((double) studyPlannerService.getStudyPlanner().getCourseOfStudyCreditPoints()));
        Tooltip tooltip = new Tooltip();
        tooltip.setText(
                "Neues Modul erstellen"
        );
        buttonAddModule.setTooltip(tooltip);

    }

    @FXML
    void addModule(ActionEvent event) {

        if(studyPlannerService.getStudyPlanner().getSemesters().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hinweis");
            alert.setHeaderText("Semester erstellen");
            alert.setContentText("Bitte zuerst ein Semester erstellen.");
            alert.showAndWait();
        }
        else{
            Stage stage = new Stage();
            Scene newSemesterScene = new Scene(new ModuleDetailViewController(studyPlannerService));
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
        Scene newSemesterScene = new Scene(new SemesterDetailViewController(studyPlannerService,false, hBoxSemesterContainer));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setScene(newSemesterScene);
        stage.showAndWait();
    }

    @FXML
    void modifySemester(ActionEvent event) {
        Stage stage = new Stage();
        Scene modifySemesterScene = new Scene(new SemesterDetailViewController(studyPlannerService,true, null));
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
        try {
            this.studyPlannerService.getIOService().storeData("data.sp");
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Fehler beim Speichern");
            alert.setContentText("Fehler beim Speicher. Bitte versuchen Sie es erneut.");
            alert.showAndWait();
        }
        Platform.exit();
        System.exit(0);
    }

}
