package main.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import main.data.JDBC;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddSeasonController {

    Stage stage;
    @FXML javafx.scene.control.TextField txfTeam1;
    @FXML javafx.scene.control.TextField txfTeam2;
    @FXML javafx.scene.control.TextField txfTeam3;
    @FXML javafx.scene.control.TextField txfTeam4;
    @FXML javafx.scene.control.TextField txfTeam5;
    @FXML javafx.scene.control.TextField txfTeam6;
    @FXML javafx.scene.control.TextField txfTeam7;
    @FXML javafx.scene.control.TextField txfTeam8;
    @FXML javafx.scene.control.TextField txfTeam9;
    @FXML javafx.scene.control.TextField txfTeam10;
    @FXML javafx.scene.control.TextField txfTeam11;
    @FXML javafx.scene.control.TextField txfTeam12;
    @FXML javafx.scene.control.TextField txfTeam13;
    @FXML javafx.scene.control.TextField txfTeam14;
    @FXML javafx.scene.control.TextField txfTeam15;
    @FXML javafx.scene.control.TextField txfTeam16;
    @FXML javafx.scene.control.TextField txfSeason1;
    @FXML javafx.scene.control.TextField txfSeason2;

    public void accept(){
        boolean flag = true;
        List<String> teams = new ArrayList<>();
        int[] season = new int[2];

        teams.add(txfTeam1.getText());
        teams.add(txfTeam2.getText());
        teams.add(txfTeam3.getText());
        teams.add(txfTeam4.getText());
        teams.add(txfTeam5.getText());
        teams.add(txfTeam6.getText());
        teams.add(txfTeam7.getText());
        teams.add(txfTeam8.getText());
        teams.add(txfTeam9.getText());
        teams.add(txfTeam10.getText());
        teams.add(txfTeam11.getText());
        teams.add(txfTeam12.getText());
        teams.add(txfTeam13.getText());
        teams.add(txfTeam14.getText());
        teams.add(txfTeam15.getText());
        teams.add(txfTeam16.getText());

        try {
            season[0] = Integer.parseInt(txfSeason1.getText());
        }catch (Exception e){
           flag = false;
        }
        try {
            season[1] = Integer.parseInt(txfSeason2.getText());
        }catch (Exception e){
            flag = false;
        }
        int check = 1;
        check+=season[0];
        Validator valid = new Validator();
        if(valid.allFieldsEntered(teams) && valid.uniqueSeason(season) && valid.allFieldsUnique(teams) && season[0]<season[1] && season[1]==check && flag){
            JDBC db = new JDBC();
            Collections.sort(teams);
            db.insertSeason(teams,season[0],season[1]);
            int id=0;
            id = db.getSeasonID(season[0], season[1]);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setContentText("Sezon dodany do bazy!");
            alert.setHeaderText(null);
            alert.showAndWait();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setContentText("Błędne dane!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void close(){
        stage.close();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
