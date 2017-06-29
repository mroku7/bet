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


public class AddScoreController implements Initializable {

    Stage stage;
    @FXML private ComboBox<Integer> cBoxNumber;
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

    @FXML private Button btnOk;
    @FXML private Button btnCancel;

    List<Match> matchList = new ArrayList<Match>();

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JDBC db = new JDBC();
        List<Integer> list;
        list = db.getFixtureNumbers(Main.seasonID);
        ObservableList<Integer> options = FXCollections.observableArrayList(list);
        cBoxNumber.setItems(options);
        HBox1.setVisible(false);
        HBox2.setVisible(false);
        HBox3.setVisible(false);
        HBox4.setVisible(false);
        HBox5.setVisible(false);
        HBox6.setVisible(false);
        HBox7.setVisible(false);
        HBox8.setVisible(false);
        cBoxNumber.setPromptText("Kolejka");
        cBoxNumber.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                addScoreLabel(newValue);
            }
        });
    }

    public void addScoreLabel(Integer nr){
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


        if(!(matchList.get(0).getHomeScore()==null)) {
            txfHome1.setText(matchList.get(0).getHomeScore().toString());
            txfAway1.setText(matchList.get(0).getAwayScore().toString());
        }
        if(!(matchList.get(1).getHomeScore()==null)) {
            txfHome2.setText(matchList.get(1).getHomeScore().toString());
            txfAway2.setText(matchList.get(1).getAwayScore().toString());
        }
        if(!(matchList.get(2).getHomeScore()==null)) {
            txfHome3.setText(matchList.get(2).getHomeScore().toString());
            txfAway3.setText(matchList.get(2).getAwayScore().toString());
        }
        if(!(matchList.get(3).getHomeScore()==null)) {
            txfHome4.setText(matchList.get(3).getHomeScore().toString());
            txfAway4.setText(matchList.get(3).getAwayScore().toString());
        }
        if(!(matchList.get(4).getHomeScore()==null)) {
            txfHome5.setText(matchList.get(4).getHomeScore().toString());
            txfAway5.setText(matchList.get(4).getAwayScore().toString());
        }
        if(!(matchList.get(5).getHomeScore()==null)) {
            txfHome6.setText(matchList.get(5).getHomeScore().toString());
            txfAway6.setText(matchList.get(5).getAwayScore().toString());
        }
        if(!(matchList.get(6).getHomeScore()==null)) {
            txfHome7.setText(matchList.get(6).getHomeScore().toString());
            txfAway7.setText(matchList.get(6).getAwayScore().toString());
        }
        if(!(matchList.get(7).getHomeScore()==null)) {
            txfHome8.setText(matchList.get(7).getHomeScore().toString());
            txfAway8.setText(matchList.get(7).getAwayScore().toString());
        }

    }

    public void accept(){
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

        for (Match match :
                matchList) {
            JDBC db = new JDBC();
            db.updateMatchScore(match);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setContentText("Wyniki uaktualnione.");
        alert.setHeaderText(null);
        alert.showAndWait();

        updatePlayerScore(matchList.get(0).getFixtureNr());

        stage.close();
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

    public void updatePlayerScore(Integer fixtureNr){
        JDBC db = new JDBC();
        List<Player> playerList = new ArrayList<Player>();
        List<Match> matchListt = new ArrayList<Match>();

        playerList = db.getAllPlayers();
        matchListt = db.getMatchScore(fixtureNr);
        for (Player player :
                playerList) {
            Integer score=0;
            for (Match match :
                    matchList) {
                score+=CalculateScore.playerOneFixture(player.getID(), match);
            }
            db.updateScore(score,fixtureNr,player.getID(), Main.seasonID);
        }
    }
}
