package view.semesterview;
import controller.StudyPlannerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Module;
import model.Semester;
import model.Statistics;
import view.moduledetail.ModuleDetailViewController;
import java.io.IOException;

public class SemesterViewController extends GridPane {
    @FXML
    private Label labelName;

    @FXML
    private Label labelCollectedECTSInSemester;

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
        labelCollectedECTSInSemester.textProperty().bind(statistics.collectedEctsForSemesterProperty(semester).asString("%d ECTS"));
        listViewModuls.setCellFactory(studentListView -> new ModuleCell());
        listViewModuls.setItems(semester.getModules());
        listViewModuls.setOnMouseClicked(click -> {

            if (click.getClickCount() == 2) {
                //Use ListView's getSelected Item
                Module selectedItem = listViewModuls.getSelectionModel().getSelectedItem();
                if(selectedItem != null){
                    Stage stage = new Stage();
                    Scene modifySemesterScene = new Scene(new ModuleDetailViewController(studyPlannerController,selectedItem));
                    stage.initModality(Modality.WINDOW_MODAL);
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


