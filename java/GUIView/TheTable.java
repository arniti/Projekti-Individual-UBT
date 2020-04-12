package GUIView;

import DAL.Functions;
import DAL.Movies;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class TheTable {
    private TableView table;
    private static TableColumn IDColumn;
    private static TableColumn descriptionColumn;

    public TableView getTable(){
        return table;
    }

    public TableColumn getIDColumn() {
        return IDColumn;
    }

    public TableColumn getDescriptionColumn() {
        return descriptionColumn;
    }

    public void makeTable(){
        table = new TableView();
        table.setPrefHeight(300);
        table.setPrefWidth(500);
        table.setLayoutX(50);
        table.setLayoutY(150);
    }

    public void makeIDColumn() {
        IDColumn = new TableColumn("ID");
        IDColumn.setPrefWidth(90);
        IDColumn.setCellValueFactory(
                new PropertyValueFactory<Movies,Integer>("ID")
        );
    }

    public void makeDescriptionColumn() {
        descriptionColumn = new TableColumn("Pershkrimi");
        descriptionColumn.setPrefWidth(table.getPrefWidth() - IDColumn.getPrefWidth());
        descriptionColumn.setCellValueFactory(
                new PropertyValueFactory<Movies,String>("description")
        );
    }

    public void addElements() {
        table.getColumns().addAll(IDColumn,descriptionColumn);
    }
}
