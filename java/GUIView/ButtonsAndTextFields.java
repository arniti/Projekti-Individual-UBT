package GUIView;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class ButtonsAndTextFields {
    private TextField createTextField;
    private Button createButton = new Button("Shto filmin");
    private Button editButton = new Button("Edito filmin");
    private Button deleteButton = new Button("Anulo");
    private Button selectButton = new Button("Selekto");
    private TextField editTextfieldID;
    private TextField editTextfieldDescription;
    private TextField deleteTextFiled;
    private TextField selectTextField;

    public TextField getTx(){
        return createTextField;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void makeDeleteButton() {
        deleteButton.setLayoutY(200);
        deleteButton.setLayoutX(560);
    }

    public TextField getDeleteTextFiled(){
        return deleteTextFiled;
    }

    public void makeDeleteTextFiled() {
        deleteTextFiled = new TextField();
        deleteTextFiled.setPromptText("ID:");
        deleteTextFiled.setLayoutY(200);
        deleteTextFiled.setLayoutX(620);
    }

    public Button getEditButton() {
        editButton.setLayoutX(50);
        editButton.setLayoutY(500);
        return editButton;
    }

    public TextField getEditTextfieldID(){
        return editTextfieldID;
    }

    public TextField getEditTextfieldDescription(){
        return editTextfieldDescription;
    }

    public void makeEditTextfieldDescription() {
        editTextfieldDescription = new TextField();
        editTextfieldDescription.setPromptText("Pershkrimi:");
        editTextfieldDescription.setLayoutX(editTextfieldID.getLayoutX()+60);
        editTextfieldDescription.setLayoutY(editTextfieldID.getLayoutY());
        editTextfieldDescription.setPrefWidth(createTextField.getPrefWidth() - 60);
    }

    public void setEditTextfieldID(){
        editTextfieldID = new TextField();
        editTextfieldID.setPromptText("ID:");
        editTextfieldID.setPrefWidth(500-450);
        editTextfieldID.setLayoutX(140);
        editTextfieldID.setLayoutY(500);
    }

    public void createTextField() {
        createTextField = new TextField();
        createTextField.setPrefWidth(500-90);
        createTextField.setPromptText("Sheno Pershkrimin...");
        createTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createTextField.setText("");
            }
        });
    }

    public Button getSelectButton() {
        return selectButton;
    }

    public void makeSelectButton() {
        selectButton.setLayoutX(560);
        selectButton.setLayoutY(260);
    }

    public TextField getSelectTextField() {
        return selectTextField;
    }

    public void makeSelectTextField() {
        selectTextField = new TextField();
        selectTextField.setPromptText("ID:");
        selectTextField.setLayoutX(620);
        selectTextField.setLayoutY(260);
    }
}
