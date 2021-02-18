package view.semesterview;
import controller.StudyPlannerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Semester;

import java.io.IOException;
import java.time.LocalDate;

public class SemesterViewController extends GridPane {
    @FXML
    private Label labelName;

    @FXML
    private Label labelCollectedECTSInSemester;

    @FXML
    private Label labelStartDate;

    @FXML
    private Label labelEndDate;

    private Stage primaryStage;

    private StudyPlannerController studyPlannerController;

    private Semester semester;

    public SemesterViewController(StudyPlannerController spc, Semester semester){
        this.semester = semester;
        this.studyPlannerController = spc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SemesterView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    public void initialize() {
        labelName.textProperty().bind(semester.nameProperty());
        labelStartDate.textProperty().bind(semester.startDateProperty().asString());
        labelEndDate.textProperty().bind(semester.endDateProperty().asString());
        labelCollectedECTSInSemester.textProperty().bind(semester.collectedECTSProperty().asString());

    }

    public Semester getSemester(){
        return this.semester;
    }
}


