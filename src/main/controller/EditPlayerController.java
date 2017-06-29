package main.controller;


import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.stage.Stage;
import main.data.JDBC;
import main.model.Player;
import java.util.List;

public class EditPlayerController {

    Stage stage;
    String name, email, oldName, oldEmail;
    Integer id, c;
    List<Player> playerList;

    @FXML javafx.scene.control.TextField txtName;
    @FXML javafx.scene.control.TextField txtEmail;

    public void setStage (Stage stage){
        this.stage = stage;
    }
    public void setData (List<Player> pl, Integer counter){
        this.playerList = pl;
        this.name = pl.get(counter).getName();
        this.email = pl.get(counter).getEmail();
        txtName.setText(name);
        txtEmail.setText(email);
        this.id = pl.get(counter).getID();
        oldName = name;
        oldEmail = email;
        this.c = counter;
    }

    public void close(){
        stage.close();
    }

    public void accept(){
        boolean flag = false;
        boolean emailFlag = false;
        String name = txtName.getText();
        String email = txtEmail.getText();

        Validator valid = new Validator();

        if(valid.isValidEmail(email) || oldEmail.equals(email)){
            emailFlag = true;
            if(valid.uniqueName(name) || oldName.equals(name)) {
                JDBC db = new JDBC();
                db.updatePlayer(name, email, id);
                playerList.get(c).setName(name);
                playerList.get(c).setEmail(email);
                flag = true;
            }
        }
        if(!flag){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            if(emailFlag) {
                alert.setContentText("Podany gracz już istnieje!");
            }else {
                alert.setContentText("Niepoprawny email!");
            }
            alert.setHeaderText(null);
            alert.showAndWait();
        }else{
            stage.close();
        }
    }
}
