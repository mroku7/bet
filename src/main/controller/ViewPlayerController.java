package main.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.data.JDBC;
import main.model.Player;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewPlayerController implements Initializable {

    Stage stage;
    JDBC db = new JDBC();
    List<Player> playerList = db.getAllPlayers();
    int counter = 0;

    @FXML javafx.scene.control.Label lblName;
    @FXML javafx.scene.control.Label lblEmail;
    @FXML Button btnLeft;
    @FXML Button btnRight;
    @FXML Button btnDelete;

    public void next(){
        counter++;
        if (counter>=playerList.size())
            counter = 0;

        setName();
    }
    public void previous(){
        counter--;
        if (counter<0)
            counter = playerList.size()-1;

        setName();
    }

    public void setName(){
        lblName.setText(playerList.get(counter).getName());
        lblEmail.setText(playerList.get(counter).getEmail());
    }

    public void delete(){
        int id = playerList.get(counter).getID();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuń");
        alert.setHeaderText("Usunięcie gracza "+playerList.get(counter).getName());
        alert.setContentText("Jesteś pewien?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            JDBC db = new JDBC();
            db.deletePlayer(id);
            playerList.remove(counter);
            next();
            alert.close();
        }
    }

    public void edit(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/view/EditPlayerView.fxml"));
            Parent root1 = fxmlLoader.load();
            EditPlayerController controller = (EditPlayerController) fxmlLoader.getController();
            stage.setScene(new Scene(root1));
            stage.setTitle("Edytuj");
            stage.initModality(Modality.APPLICATION_MODAL);
            controller.setStage(stage);
            controller.setData(playerList, counter);
            stage.setResizable(false);
            stage.setWidth(300.0);
            stage.setHeight(200.0);
            stage.show();
            stage.setOnHiding(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent event) {
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                            next();
                            previous();
                        }
                    });
                }
            });
        }
        catch (Exception e){
        }
    }

    void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setName();
    }
}
