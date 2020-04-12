package GUIModel;

import BLL.CrudException;
import DAL.AddingContent;
import GUIView.TheTable;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import DAL.Functions;
import DAL.Movies;
import GUIView.ButtonsAndTextFields;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Model extends Application {
    Functions fun = new Functions();
    private static ArrayList<Movies> m = new ArrayList<>();
    ButtonsAndTextFields bt = new ButtonsAndTextFields();
    TheTable tt = new TheTable();
    AddingContent add = new AddingContent();

    @Override
    public void start(Stage stage) throws CrudException {
        add.getDescriptions();
        fun.order();
        fun.readMovies();
        bt.setEditTextfieldID();
        bt.createTextField();
        bt.makeEditTextfieldDescription();
        bt.makeDeleteButton();
        bt.makeDeleteTextFiled();
        bt.makeSelectButton();
        bt.makeSelectTextField();
        tt.makeTable();
        tt.makeIDColumn();
        tt.makeDescriptionColumn();
        tt.addElements();
        TableView table = tt.getTable();

        bt.getCreateButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    fun.create(bt.getTx().getText());
                    System.out.println(bt.getTx().getText());
                    bt.getTx().setText("");
                    fun.readMovies();
                } catch (CrudException e) {
                    e.printStackTrace();
                }
                System.out.println("para");
                table.setItems(fun.getMovies());
                System.out.println("pas");
            }
        });

        bt.getEditButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    fun.update(Integer.parseInt(bt.getEditTextfieldID().getText()),bt.getEditTextfieldDescription().getText());
                    tt.getDescriptionColumn().setCellFactory(TextFieldTableCell.forTableColumn());
                    bt.getEditTextfieldID().setText("");
                    bt.getEditTextfieldDescription().setText("");
                    fun.readMovies();
                } catch (CrudException e) {
                    e.printStackTrace();
                }
                table.setItems(fun.getMovies());
            }
        });

        bt.getDeleteButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    fun.delete(Integer.parseInt(bt.getDeleteTextFiled().getText()));
                    bt.getDeleteTextFiled().setText("");
                    tt.getDescriptionColumn().setCellFactory(TextFieldTableCell.forTableColumn());
                } catch (CrudException e) {
                    e.printStackTrace();
                }
                table.setItems(fun.getMovies());
            }
        });

        table.setStyle("-fx-selection-bar: red; -fx-selection-bar-non-focused: salmon;");
        TableView.TableViewSelectionModel<Movies> selectionModel = table.getSelectionModel();
        bt.getSelectButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    for (int i = 0; i < fun.getMovies().size(); i++) {
                        m.add((Movies) fun.getMovies().get(i));
                    }
                    for (int i = 0; i < m.size(); i++) {
                        if (m.get(i).getID() == Integer.parseInt(bt.getSelectTextField().getText())) {
                            selectionModel.select(i);
                        } else {
                            System.out.println("nuk ka");
                            continue;
                        }
                        bt.getSelectTextField().setText("");
                    }
                }catch (NumberFormatException nfe){}
            }
        });

        table.setItems(fun.getMovies());
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPrefSize(800,600);
        gridPane.setPadding(new Insets(5,5,5,50));
        BackgroundImage myBI= null;
        try {
            myBI = new BackgroundImage(new Image(new FileInputStream("C:\\Users\\Admin\\Desktop\\UBT\\UBT-Semestri 4\\Lab\\Projekti Individual\\src\\main\\java\\GUIModel\\popcorn.jpg"),800,600,false,true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        gridPane.setBackground(new Background(myBI));gridPane.setEffect(new GaussianBlur());

        HBox hbox = new HBox();
        hbox.getChildren().addAll(bt.getCreateButton(),bt.getTx());
        hbox.setSpacing(15);
        hbox.setLayoutY(460);
        hbox.setLayoutX(50);


        Group g = new Group
                (
                    gridPane,hbox,bt.getEditButton()
                    ,bt.getEditTextfieldID(),bt.getEditTextfieldDescription()
                    ,bt.getDeleteButton(),bt.getDeleteTextFiled()
                    ,bt.getSelectButton(),bt.getSelectTextField(),table
                );

        Scene scene = new Scene(g,800,600);
        scene.setFill(Color.RED);
        stage.setTitle("Movies Library");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
