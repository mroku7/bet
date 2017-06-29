package main.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.Main;
import main.data.JDBC;
import main.model.Season;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class OptionsController implements Initializable {

    Stage stage;

    String selectedSeason;
    List<Season> seasons;
    
    @FXML ComboBox <String> cbSeasons;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void accept(){
        String year = selectedSeason;
        String[] parts = year.split("/");
        Integer[] part = new Integer[2];
        part[0] = Integer.parseInt(parts[0]);
        part[1] = Integer.parseInt(parts[1]);
        for (Season season :
                seasons) {
            if(season.year1.equals(part[0]) && season.year2.equals(part[1])) {
                Main.seasonID = season.seasonID;
            }
        }
        JDBC db = new JDBC();
        db.lastSeason(Main.seasonID);
        Main.setTitle();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        List<String> seasonYears = new ArrayList<>();
        JDBC db = new JDBC();
        seasons = db.getAllSeasons();
        for (Season season :
                seasons) {
            StringBuilder sb = new StringBuilder();
            sb.append(season.year1);
            sb.append("/");
            sb.append(season.year2);
            seasonYears.add(sb.toString());
        }
        ObservableList<String> options = FXCollections.observableArrayList(seasonYears);
        cbSeasons.setItems(options);
        cbSeasons.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedSeason = newValue;
            }
        });
    }
}
