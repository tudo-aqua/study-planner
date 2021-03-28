package view.semesterview;

import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;
import model.enums.State;

public class ModuleCell extends ListCell<Module> {

    @Override
    public void updateItem(Module module, boolean empty) {
        super.updateItem(module, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

            VBox vBox = new VBox();
            Label labelName = new Label();
            labelName.textProperty().bind(module.nameProperty());
            vBox.getChildren().add(labelName);
            Label labelExamDate = new Label();
            labelExamDate.textProperty().bindBidirectional(module.examDateProperty(), new LocalDateConverter());
            vBox.getChildren().add(labelExamDate);
            Label labelECTS = new Label();

            vBox.getChildren().add(labelECTS);
            labelECTS.textProperty().bind(module.ectsProperty().asString("%d ECTS"));
            if(module.getState()== State.PASSED_WITH_GRADE){
                Label labelGrade = new Label();
                labelGrade.textProperty().bind(module.gradeProperty().asString("Note %.1f"));
                vBox.getChildren().add(labelGrade);

            }
            this.setStyle("-fx-border-color: black");
            this.setStyle("-fx-border-width:10px");

            HBox hBox = new HBox();

            ImageView imageView = new ImageView();
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            hBox.getChildren().add(imageView);
            vBox.getChildren().add(hBox);
            switch (module.getState()){
                case NO_RESULT:imageView.setImage(new Image("/assets/question.png"));break;
                case NOT_PASSED:imageView.setImage(new Image("/assets/cancel.png"));break;
                case PASSED_WITH_GRADE :imageView.setImage(new Image("/assets/checked.png"));break;
                case PASSED_WITHOUT_GRADE:imageView.setImage(new Image("/assets/checked.png"));break;
            }



            setGraphic(vBox);
        }
    }
}
