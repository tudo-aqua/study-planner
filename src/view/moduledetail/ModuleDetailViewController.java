package view.moduledetail;

import controller.ModuleController;
import controller.StudyPlannerController;
import exceptions.DataNotValidException;
import exceptions.ModuleAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Module;
import model.Semester;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Die Klasse stellt den ViewController für die Detailansicht eines Moduls dar.
 * Sie wird sowohl beim erstellen eines neuen Moduls als auch beim bearbeiten eines bestehenden
 * Moduls verwendet.
 */
public class ModuleDetailViewController extends GridPane {

    @FXML
    private Label labelTitle;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonDelete;

    @FXML
    private TextField textFieldModuleName;

    @FXML
    private TextField textFieldECTS;

    @FXML
    private DatePicker datePickerExamDate;

    @FXML
    private ChoiceBox<Semester> choiseBoxSemester;


    private StudyPlannerController studyPlannerController;

    private Module moduleToModify;

    public ModuleDetailViewController(StudyPlannerController spc){
        this();
        this.studyPlannerController = spc;
        this.choiseBoxSemester.setItems(studyPlannerController.getStudyPlanner().getSemesters());
        this.choiseBoxSemester.setValue(studyPlannerController.getStudyPlanner().getSemesters().get(0));
        choiseBoxSemester.setConverter(new StringConverter<Semester>() {


            @Override
            public String toString(Semester semester) {
                return semester.getName();
            }

            @Override
            public Semester fromString(String string) {
                return null;
            }
        });
    }

    public ModuleDetailViewController(StudyPlannerController spc, Module moduleToModify){
        this();
        this.studyPlannerController = spc;
        this.moduleToModify = moduleToModify;
        this.choiseBoxSemester.setItems(studyPlannerController.getStudyPlanner().getSemesters());
        this.choiseBoxSemester.setValue(studyPlannerController.getStudyPlanner().getSemesters().get(0));
        choiseBoxSemester.setConverter(new StringConverter<Semester>() {


            @Override
            public String toString(Semester semester) {
                return semester.getName();
            }

            @Override
            public Semester fromString(String string) {
                return null;
            }
        });
    }


    private ModuleDetailViewController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModuleDetailView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Die Methode wird aufgerufen, wenn der "Beenden"-Button geklickt wird.
     * @param event Die Aktion des Button-Klicks.
     */
    @FXML
    void cancel(ActionEvent event) {
        //Methode zum schließen des Detail-Fensters.
        this.closeModuleDetailView();
    }


    /**
     * Die Methode wird aufgerufen, wenn der "Löschen"-Button geklickt wird.
     * @param event Die Aktion des Button-Klicks.
     */
    @FXML
    void delete(ActionEvent event) {
        studyPlannerController.getModuleController().deleteModule(moduleToModify);
        closeModuleDetailView();
    }

    /**
     * Die Methode wird aufgerufen, wenn der Button zum Erstellen bzw. Bearbeiten eines Moduls geklickt wird.
     * @param event Die Aktion des Button-Klicks.
     */
    @FXML
    void save(ActionEvent event) {
        try{
            //Eingaben des Nutzers werden aus den Textfeldern und dem DatePicker übernommen
            String inputModuleName = textFieldModuleName.getText();
            int inputECTS = Integer.parseInt(textFieldECTS.getText());
            LocalDate inputExamDate = datePickerExamDate.getValue();
            ModuleController moduleController = studyPlannerController.getModuleController();
            //Fallunterscheidung, ob ein neues Modul erstellt werden soll oder ein bestehendes bearbeitet wird
            if(this.moduleToModify == null){
                Module newModule = moduleController.createModule(inputModuleName,inputECTS,inputExamDate);
                Semester selectedSemester = choiseBoxSemester.getValue();
                studyPlannerController.getSemesterController().moveModuleToSemester(newModule, selectedSemester);
            }
            else{
                moduleController.modifyModule(moduleToModify,inputModuleName,inputECTS,inputExamDate);
            }
            closeModuleDetailView();


        } catch (NumberFormatException | DataNotValidException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eingabefehler");
            alert.setHeaderText("Fehler bei Eingabe");
            alert.setContentText("Bitte überprüfen Sie ihre Eingabe.");
            alert.showAndWait();

        } catch (ModuleAlreadyExistsException maee) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modul existiert bereits");
            alert.setHeaderText("Modul existiert bereits");
            alert.setContentText("Ein Modul mit dem von ihnen gewählen Namen existiert bereits. Bitte wählen Sie einen eindeutigen Modulnamen.");
            alert.showAndWait();


        }

    }

    /**
     * Hilfsmethode zum schließen des Detail-Fensters.
     */
    private void closeModuleDetailView(){
        //Das Detailfenster wird geschlossen
        Stage stage = (Stage)this.getScene().getWindow();
        stage.close();
    }

}
