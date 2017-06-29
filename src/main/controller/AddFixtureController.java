package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.Main;
import main.data.JDBC;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddFixtureController implements Initializable{

    Stage stage;
    List<String> teamsOriginal = new ArrayList<String>();

    @FXML javafx.scene.control.TextField txfFixtureNr;
    @FXML ComboBox<String> cbHome1;
    @FXML ComboBox<String> cbHome2;
    @FXML ComboBox<String> cbHome3;
    @FXML ComboBox<String> cbHome4;
    @FXML ComboBox<String> cbHome5;
    @FXML ComboBox<String> cbHome6;
    @FXML ComboBox<String> cbHome7;
    @FXML ComboBox<String> cbHome8;
    @FXML ComboBox<String> cbAway1;
    @FXML ComboBox<String> cbAway2;
    @FXML ComboBox<String> cbAway3;
    @FXML ComboBox<String> cbAway4;
    @FXML ComboBox<String> cbAway5;
    @FXML ComboBox<String> cbAway6;
    @FXML ComboBox<String> cbAway7;
    @FXML ComboBox<String> cbAway8;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void accept() {
        boolean flag = false;
        List<String> home = new ArrayList<String>();
        List<String> away = new ArrayList<String>();

        home.add(cbHome1.getValue());
        home.add(cbHome2.getValue());
        home.add(cbHome3.getValue());
        home.add(cbHome4.getValue());
        home.add(cbHome5.getValue());
        home.add(cbHome6.getValue());
        home.add(cbHome7.getValue());
        home.add(cbHome8.getValue());

        away.add(cbAway1.getValue());
        away.add(cbAway2.getValue());
        away.add(cbAway3.getValue());
        away.add(cbAway4.getValue());
        away.add(cbAway5.getValue());
        away.add(cbAway6.getValue());
        away.add(cbAway7.getValue());
        away.add(cbAway8.getValue());

        int fixtureNumber = 0;
        try {
            fixtureNumber = Integer.parseInt(txfFixtureNr.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setContentText("Błędne dane!");
            alert.setHeaderText(null);
            alert.showAndWait();
            flag = true;
        }
        Validator valid = new Validator();
        List<String> tlist = new ArrayList<>(home);
        tlist.addAll(away);
        if (valid.allFieldsEntered(tlist) && valid.allFieldsUnique(tlist) && valid.uniqueFixtureInSeason(fixtureNumber) && 0 < fixtureNumber && fixtureNumber < 38) {
            System.out.println(fixtureNumber);
            JDBC db = new JDBC();
            db.insertFixture(home, away, fixtureNumber, Main.seasonID);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setContentText("Kolejka dodana do bazy!");
            alert.setHeaderText(null);
            alert.showAndWait();
            close();
        } else {
            if (!flag) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setContentText("Błędne dane!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      loadTeams(Main.seasonID);
    }
    public void loadTeams(Integer sID){

        JDBC db = new JDBC();
        teamsOriginal = db.getTeams(sID);
        ObservableList<String> options=  FXCollections.observableArrayList(teamsOriginal);
        cbHome1.setItems(options);
        cbHome2.setItems(options);
        cbHome3.setItems(options);
        cbHome4.setItems(options);
        cbHome5.setItems(options);
        cbHome6.setItems(options);
        cbHome7.setItems(options);
        cbHome8.setItems(options);

        cbAway1.setItems(options);
        cbAway2.setItems(options);
        cbAway3.setItems(options);
        cbAway4.setItems(options);
        cbAway5.setItems(options);
        cbAway6.setItems(options);
        cbAway7.setItems(options);
        cbAway8.setItems(options);
    }

    public void close(){
        stage.close();
    }
}
