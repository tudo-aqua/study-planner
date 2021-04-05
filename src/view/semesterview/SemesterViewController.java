package view.semesterview;
import controller.StudyPlannerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Module;
import model.Semester;
import model.Statistics;
import view.moduledetail.ModuleDetailViewController;

import java.io.IOException;

public class SemesterViewController extends GridPane {
    @FXML
    private Label labelName;

    @FXML
    private Label labelCreditPointsForSemester;

    @FXML
    private Label labelStartDate;

    @FXML
    private Label labelEndDate;

    @FXML
    private ListView<Module> listViewModuls;

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
        Statistics statistics = this.studyPlannerController.getStudyPlanner().getStatistics();
        labelName.textProperty().bind(semester.nameProperty());
        labelStartDate.textProperty().bindBidirectional(semester.startDateProperty(), new LocalDateConverter());


        labelEndDate.textProperty().bindBidirectional(semester.endDateProperty(), new LocalDateConverter());
        labelCreditPointsForSemester.textProperty().bind(statistics.creditPointsForSemesterProperty(semester).asString("%d Leistungspunkte"));
        listViewModuls.setCellFactory(studentListView -> new ModuleCell());

        listViewModuls.itemsProperty().bind(semester.modulesProperty());
        listViewModuls.setOnMouseClicked(click -> {

            if (click.getClickCount() == 2) {
                //Aktuell ausgewähltes Modul ermitteln
                Module selectedItem = listViewModuls.getSelectionModel().getSelectedItem();
                if(selectedItem != null){
                    Stage stage = new Stage();
                    Scene modifySemesterScene = new Scene(new ModuleDetailViewController(studyPlannerController,selectedItem));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.initOwner(primaryStage);
                    stage.setScene(modifySemesterScene);
                    stage.showAndWait();
                }
            }
        });

    }

    public Semester getSemester(){
        return this.semester;
    }
}


