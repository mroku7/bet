package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.data.JDBC;


public class AddPlayerController {

    Stage stage;
    @FXML javafx.scene.control.TextField txfName;
    @FXML javafx.scene.control.TextField txfEmail;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void accept() {
            boolean flag = false;
            boolean emailFlag = false;
            String name = txfName.getText();
            String email = txfEmail.getText();

            Validator valid = new Validator();

            if(valid.isValidEmail(email)){
                emailFlag = true;
                if(valid.uniqueName(name)) {
                    JDBC db = new JDBC();
                    db.insertPlayer(name, email);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setContentText("Dodano gracza "+name+" do bazy!");
                    alert.setHeaderText(null);
                    alert.showAndWait();
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

    public void close(){
        stage.close();
    }

}
