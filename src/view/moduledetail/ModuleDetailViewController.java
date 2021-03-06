package view.moduledetail;

import service.ModuleService;
import service.StudyPlannerService;
import exceptions.DataNotValidException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import entity.Module;
import entity.Semester;
import entity.StudyPlanner;
import entity.enums.State;

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
    private TextField textFieldCreditPoints;

    @FXML
    private DatePicker datePickerExamDate;

    @FXML
    private ChoiceBox<Semester> choiseBoxSemester;

    @FXML
    private RadioButton radioButtonPassedWithGrade;

    @FXML
    private RadioButton radioButtonPassedWithoutGrade;

    @FXML
    private RadioButton radioButtonNotPassed;

    @FXML
    private RadioButton radioButtonNoResult;

    @FXML
    private Label labelGrade;

    @FXML
    private Slider sliderGradeValue;

    @FXML
    private Label labelGradeValue;

    private ToggleGroup toggleGroupStatus;


    private StudyPlannerService studyPlannerService;

    private Module moduleToModify;

    public ModuleDetailViewController(StudyPlannerService spc){
        this();
        this.studyPlannerService = spc;
        this.choiseBoxSemester.setItems(studyPlannerService.getStudyPlanner().getSemesters());
        this.choiseBoxSemester.setValue(studyPlannerService.getStudyPlanner().getSemesters().get(0));
        labelGradeValue.setText("");
        buttonSave.setText("Modul erstellen");
        buttonDelete.setVisible(false);
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
        initializeToggleGroup();

    }

    public ModuleDetailViewController(StudyPlannerService spc, Module moduleToModify){
        this();
        this.studyPlannerService = spc;
        this.moduleToModify = moduleToModify;
        this.buttonSave.setText("Modul bearbeiten");
        this.textFieldModuleName.setText(moduleToModify.getName());
        this.textFieldCreditPoints.setText(moduleToModify.getCreditPoints()+"");
        this.textFieldModuleName.setEditable(false);

        this.datePickerExamDate.setValue(moduleToModify.getExamDate());
        this.choiseBoxSemester.setItems(studyPlannerService.getStudyPlanner().getSemesters());
        StudyPlanner studyPlanner = studyPlannerService.getStudyPlanner();
        Semester  currentSemester = studyPlanner.getCurrentSemesterOfModule(moduleToModify);
        this.choiseBoxSemester.setValue(currentSemester);
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
        initializeToggleGroup();
        State state = moduleToModify.getState();

        labelGrade.setVisible(state == State.PASSED_WITH_GRADE);
        labelGradeValue.setVisible(state == State.PASSED_WITH_GRADE);
        sliderGradeValue.setVisible(state == State.PASSED_WITH_GRADE);
        switch (moduleToModify.getState()){
            case PASSED_WITH_GRADE: radioButtonPassedWithGrade.setSelected(true);break;
            case NO_RESULT: radioButtonNoResult.setSelected(true);break;
            case NOT_PASSED:radioButtonNotPassed.setSelected(true);break;
            case PASSED_WITHOUT_GRADE:radioButtonPassedWithoutGrade.setSelected(true);break;
        }
        if(state == State.PASSED_WITH_GRADE){

            int value = GradeConverter.gradeToSliderValue(moduleToModify.getGrade());
            sliderGradeValue.setValue(value);
            labelGradeValue.setText(String.format("%.1f",GradeConverter.sliderValueToGrade(value)));
        }


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
        //Methode zum Schließen des Detail-Fensters.
        this.closeModuleDetailView();
    }


    /**
     * Die Methode wird aufgerufen, wenn der "Löschen"-Button geklickt wird.
     * @param event Die Aktion des Button-Klicks.
     */
    @FXML
    void delete(ActionEvent event) {
        studyPlannerService.getModuleService().deleteModule(moduleToModify);
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
            int inputCreditPoints = Integer.parseInt(textFieldCreditPoints.getText());
            LocalDate inputExamDate = datePickerExamDate.getValue();
            Semester inputSemester = this.choiseBoxSemester.getValue();
            ModuleService moduleService = studyPlannerService.getModuleService();
            //Fallunterscheidung, ob ein neues Modul erstellt werden soll oder ein bestehendes bearbeitet wird
            if(this.moduleToModify == null){
                Module newModule = moduleService.createModule(inputModuleName,inputCreditPoints,inputExamDate,inputSemester);
                moduleService.setStateToModule(newModule,getSelectedState(),GradeConverter.sliderValueToGrade(sliderGradeValue.getValue()));

            }
            else{
                moduleService.modifyModule(moduleToModify,inputCreditPoints,inputExamDate,inputSemester);
                moduleService.setStateToModule(moduleToModify,getSelectedState(),GradeConverter.sliderValueToGrade(sliderGradeValue.getValue()));

            }

            closeModuleDetailView();


        } catch (NumberFormatException | DataNotValidException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eingabefehler");
            alert.setHeaderText("Fehler bei Eingabe");
            alert.setContentText("Bitte überprüfen Sie ihre Eingabe.");
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

    private void initializeToggleGroup(){
        this.toggleGroupStatus = new ToggleGroup();
        radioButtonNoResult.setToggleGroup(toggleGroupStatus);
        radioButtonNotPassed.setToggleGroup(toggleGroupStatus);
        radioButtonPassedWithGrade.setToggleGroup(toggleGroupStatus);
        radioButtonPassedWithoutGrade.setToggleGroup(toggleGroupStatus);
        toggleGroupStatus.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (toggleGroupStatus.getSelectedToggle() != null) {

                labelGrade.setVisible(radioButtonPassedWithGrade.isSelected());
                labelGradeValue.setVisible(radioButtonPassedWithGrade.isSelected());
                sliderGradeValue.setVisible(radioButtonPassedWithGrade.isSelected());

            }
        });
        radioButtonNoResult.setSelected(true);
        sliderGradeValue.setMin(1);
        sliderGradeValue.setMax(10);
        sliderGradeValue.setSnapToTicks(true);
        sliderGradeValue.setMajorTickUnit(1);
        sliderGradeValue.setMinorTickCount(0);
        sliderGradeValue.setShowTickMarks(true);
        sliderGradeValue.valueProperty().addListener((obs, oldval, newVal) -> labelGradeValue.setText(String.format("%.1f",GradeConverter.sliderValueToGrade(newVal.intValue()))));
        labelGradeValue.setText("1,0");
    }





    private State getSelectedState(){
        if(radioButtonNotPassed.isSelected())
            return State.NOT_PASSED;
        if(radioButtonPassedWithoutGrade.isSelected())
            return State.PASSED_WITHOUT_GRADE;
        if(radioButtonPassedWithGrade.isSelected())
            return State.PASSED_WITH_GRADE;
        return State.NO_RESULT;
    }
}
