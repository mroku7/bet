package main.controller;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.Main;
import main.data.JDBC;
import main.model.Match;
import main.model.Player;
import main.model.PlayerTable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShowTableController implements Initializable {

    Stage stage;
    @FXML private ComboBox<Integer> cBoxNumber;
    private List<Player> playerList = new ArrayList<Player>();
    private List<Match> matchList = new ArrayList<Match>();
    @FXML private TableView<PlayerTable> table;
    TableColumn name = new TableColumn("Imię");
    TableColumn score = new TableColumn("Punkty");
    TableColumn index = new TableColumn("#");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JDBC db = new JDBC();
        List<Integer> nrList = new ArrayList<Integer>();
        nrList = db.getFixtureNumbers(Main.seasonID);
        ObservableList<Integer> optionsNr = FXCollections.observableArrayList(nrList);
        cBoxNumber.setItems(optionsNr);
        cBoxNumber.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                table.setItems(fillTable(newValue));
                table.getSortOrder().add(score);
            }
        });

        index.setSortable(false);
        index.setPrefWidth(25);
        index.setStyle("-fx-alignment: CENTER");
        index.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures param) {
                return new ReadOnlyObjectWrapper(table.getItems().indexOf(param.getValue())+1);
            }
        });

        name.setPrefWidth(150);
        name.setStyle("-fx-padding: 0 0 0 20");
        score.setPrefWidth(75);
        score.setStyle("-fx-alignment: CENTER");
        score.setSortType(TableColumn.SortType.DESCENDING);

        table.getColumns().addAll(index, name, score);
        table.setStyle("-fx-font-size: 15");

        name.setCellValueFactory( new PropertyValueFactory<>("name"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
    }

    public ObservableList<PlayerTable> fillTable(Integer fixtureNr){

        ObservableList<PlayerTable> data = FXCollections.observableArrayList();
        List<PlayerTable> list = new ArrayList<PlayerTable>();

        JDBC db = new JDBC();
        list = db.getOneFixtureScore(fixtureNr);

        for (PlayerTable player :
                list) {
            data.add(player);

        }

        return  data;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
