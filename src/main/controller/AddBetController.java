package main.controller;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Main;
import main.data.JDBC;
import main.model.Match;
import main.model.Player;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBetController implements Initializable {

    Stage stage;
    Player player;

    @FXML private ComboBox<Integer> cBoxNumber;
    @FXML private ComboBox<String> cBoxName;
    @FXML private Label lblHome1;
    @FXML private Label lblHome2;
    @FXML private Label lblHome3;
    @FXML private Label lblHome4;
    @FXML private Label lblHome5;
    @FXML private Label lblHome6;
    @FXML private Label lblHome7;
    @FXML private Label lblHome8;
    @FXML private Label lblAway1;
    @FXML private Label lblAway2;
    @FXML private Label lblAway3;
    @FXML private Label lblAway4;
    @FXML private Label lblAway5;
    @FXML private Label lblAway6;
    @FXML private Label lblAway7;
    @FXML private Label lblAway8;
    @FXML private TextField txfHome1;
    @FXML private TextField txfHome2;
    @FXML private TextField txfHome3;
    @FXML private TextField txfHome4;
    @FXML private TextField txfHome5;
    @FXML private TextField txfHome6;
    @FXML private TextField txfHome7;
    @FXML private TextField txfHome8;
    @FXML private TextField txfAway1;
    @FXML private TextField txfAway2;
    @FXML private TextField txfAway3;
    @FXML private TextField txfAway4;
    @FXML private TextField txfAway5;
    @FXML private TextField txfAway6;
    @FXML private TextField txfAway7;
    @FXML private TextField txfAway8;
    @FXML private HBox HBox1;
    @FXML private HBox HBox2;
    @FXML private HBox HBox3;
    @FXML private HBox HBox4;
    @FXML private HBox HBox5;
    @FXML private HBox HBox6;
    @FXML private HBox HBox7;
    @FXML private HBox HBox8;


    Integer actualFixtureNr = null;

    List<Match> matchList = new ArrayList<>();


    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JDBC db = new JDBC();
        List<Integer> nrList;
        List<String> nameList;
        nrList = db.getFixtureNumbers(Main.seasonID);
        nameList = db.getPlayersName();
        ObservableList<Integer> optionsNr = FXCollections.observableArrayList(nrList);
        ObservableList<String> optionsName = FXCollections.observableArrayList(nameList);
        cBoxNumber.setItems(optionsNr);
        cBoxName.setItems(optionsName);
        HBox1.setVisible(false);
        HBox2.setVisible(false);
        HBox3.setVisible(false);
        HBox4.setVisible(false);
        HBox5.setVisible(false);
        HBox6.setVisible(false);
        HBox7.setVisible(false);
        HBox8.setVisible(false);
        cBoxName.setVisible(false);
        cBoxName.setPromptText("Imię");
        cBoxNumber.setPromptText("Kolejka");
        cBoxNumber.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                actualFixtureNr = newValue;
                cBoxName.setVisible(true);
                HBox1.setVisible(false);
                HBox2.setVisible(false);
                HBox3.setVisible(false);
                HBox4.setVisible(false);
                HBox5.setVisible(false);
                HBox6.setVisible(false);
                HBox7.setVisible(false);
                HBox8.setVisible(false);
            }
        });
        cBoxName.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                JDBC db = new JDBC();
                player = null;
                player = db.getPlayer(newValue);
                if(db.betIsPlaced(player.getID(), actualFixtureNr)) {
                    HBox1.setVisible(false);
                    HBox2.setVisible(false);
                    HBox3.setVisible(false);
                    HBox4.setVisible(false);
                    HBox5.setVisible(false);
                    HBox6.setVisible(false);
                    HBox7.setVisible(false);
                    HBox8.setVisible(false);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("");
                    alert.setContentText(player.getName() + " posiada już typy dla kolejki " + actualFixtureNr);
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }else {
                    addLabel(actualFixtureNr);
                }

            }
        });

    }

    public void addLabel(Integer nr){
        matchList.clear();
        txfHome1.clear();
        txfHome2.clear();
        txfHome3.clear();
        txfHome4.clear();
        txfHome5.clear();
        txfHome6.clear();
        txfHome7.clear();
        txfHome8.clear();
        txfAway1.clear();
        txfAway2.clear();
        txfAway3.clear();
        txfAway4.clear();
        txfAway5.clear();
        txfAway6.clear();
        txfAway7.clear();
        txfAway8.clear();

        JDBC db = new JDBC();
        matchList = db.getMatchScore(nr);
        HBox1.setVisible(true);
        HBox2.setVisible(true);
        HBox3.setVisible(true);
        HBox4.setVisible(true);
        HBox5.setVisible(true);
        HBox6.setVisible(true);
        HBox7.setVisible(true);
        HBox8.setVisible(true);

        lblHome1.setText(matchList.get(0).getHomeTeam());
        lblAway1.setText(matchList.get(0).getAwayTeam());

        lblHome2.setText(matchList.get(1).getHomeTeam());
        lblAway2.setText(matchList.get(1).getAwayTeam());

        lblHome3.setText(matchList.get(2).getHomeTeam());
        lblAway3.setText(matchList.get(2).getAwayTeam());

        lblHome4.setText(matchList.get(3).getHomeTeam());
        lblAway4.setText(matchList.get(3).getAwayTeam());

        lblHome5.setText(matchList.get(4).getHomeTeam());
        lblAway5.setText(matchList.get(4).getAwayTeam());

        lblHome6.setText(matchList.get(5).getHomeTeam());
        lblAway6.setText(matchList.get(5).getAwayTeam());

        lblHome7.setText(matchList.get(6).getHomeTeam());
        lblAway7.setText(matchList.get(6).getAwayTeam());

        lblHome8.setText(matchList.get(7).getHomeTeam());
        lblAway8.setText(matchList.get(7).getAwayTeam());
    }

    public void accept(){

        if(getText(txfHome1)==null || getText(txfHome2)==null || getText(txfHome3)==null || getText(txfHome4)==null || getText(txfHome5)==null || getText(txfHome6)==null || getText(txfHome7)==null || getText(txfHome8)==null ||
           getText(txfAway1)==null || getText(txfAway2)==null || getText(txfAway3)==null || getText(txfAway4)==null || getText(txfAway5)==null || getText(txfAway6)==null || getText(txfAway7)==null || getText(txfAway8)==null){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setContentText("Wprowadź wszystkie wyniki.");
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {

        matchList.get(0).setHomeScore(getText(txfHome1));
        matchList.get(0).setAwayScore(getText(txfAway1));

        matchList.get(1).setHomeScore(getText(txfHome2));
        matchList.get(1).setAwayScore(getText(txfAway2));

        matchList.get(2).setHomeScore(getText(txfHome3));
        matchList.get(2).setAwayScore(getText(txfAway3));

        matchList.get(3).setHomeScore(getText(txfHome4));
        matchList.get(3).setAwayScore(getText(txfAway4));

        matchList.get(4).setHomeScore(getText(txfHome5));
        matchList.get(4).setAwayScore(getText(txfAway5));

        matchList.get(5).setHomeScore(getText(txfHome6));
        matchList.get(5).setAwayScore(getText(txfAway6));

        matchList.get(6).setHomeScore(getText(txfHome7));
        matchList.get(6).setAwayScore(getText(txfAway7));

        matchList.get(7).setHomeScore(getText(txfHome8));
        matchList.get(7).setAwayScore(getText(txfAway8));

        JDBC db = new JDBC();
        Integer i = 1;
        for (Match match :
                matchList) {
            db.insertBet(match, i, player.getID(), Main.seasonID);
            i++;
        }
        db.inserScore(player.getID(), matchList.get(0).getFixtureNr(), Main.seasonID);

         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("");
         alert.setContentText("Wyniki uaktualnione.");
         alert.setHeaderText(null);
         alert.showAndWait();

         stage.close();
        }
    }

    public Integer getText(TextField text){

        try {
            Integer score = Integer.parseInt(text.getText());
            return score;
        } catch (Exception e) {
            return null;
        }

    }


    public void close(){
        stage.close();
    }
}
