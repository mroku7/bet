package main.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.data.JDBC;
import main.model.Player;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    @FXML
    private TextField filterText;
    @FXML private TableView<Player> table;
    javafx.scene.control.TableColumn name = new javafx.scene.control.TableColumn("Imię");
    javafx.scene.control.TableColumn score = new javafx.scene.control.TableColumn("Email");
    ObservableList<Player> data = FXCollections.observableArrayList();

    public void addPlayer(){

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/view/AddPlayerView.fxml"));
            Parent root1 = fxmlLoader.load();
            AddPlayerController controller = (AddPlayerController) fxmlLoader.getController();
            stage.setScene(new Scene(root1));
            stage.setTitle("Dodaj gracza");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            controller.setStage(stage);
            stage.show();
        }
        catch (Exception e){
        }
    }

    public void addFixture(){

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/view/AddFixtureView.fxml"));
            Parent root1 = fxmlLoader.load();
            AddFixtureController controller = (AddFixtureController) fxmlLoader.getController();
            stage.setScene(new Scene(root1));
            stage.setTitle("Dodaj kolejkę");
            stage.initModality(Modality.APPLICATION_MODAL);
            controller.setStage(stage);
            stage.setResizable(false);
            stage.setWidth(500.0);
            stage.setHeight(500.0);
            stage.show();
        }
        catch (Exception e){
        }
    }

    public void addScore() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/view/AddScoreView.fxml"));
            Parent root1 = fxmlLoader.load();
            AddScoreController controller = (AddScoreController) fxmlLoader.getController();
            stage.setScene(new Scene(root1));
            stage.setTitle("Dodaj wyniki");
            stage.initModality(Modality.APPLICATION_MODAL);
            controller.setStage(stage);
            stage.setResizable(false);
            stage.setWidth(500.0);
            stage.setHeight(500.0);
            stage.show();

        } catch (Exception e) {
        }
    }

    public void addBet(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/view/AddBetView.fxml"));
            Parent root1 = fxmlLoader.load();
            AddBetController controller = (AddBetController) fxmlLoader.getController();
            stage.setScene(new Scene(root1));
            stage.setTitle("Dodaj typy");
            stage.initModality(Modality.APPLICATION_MODAL);
            controller.setStage(stage);
            stage.setResizable(false);
            stage.setWidth(500.0);
            stage.setHeight(550.0);
            stage.show();
        }catch (Exception e){
        }
    }

    public void showTable(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/view/ShowTableView.fxml"));
            Parent root1 = fxmlLoader.load();
            ShowTableController controller = (ShowTableController) fxmlLoader.getController();
            stage.setScene(new Scene(root1));
            stage.setTitle("Dodaj typy");
            stage.initModality(Modality.APPLICATION_MODAL);
            controller.setStage(stage);
            stage.setResizable(false);
            stage.setWidth(500.0);
            stage.setHeight(550.0);

            stage.show();

        }catch (Exception e){
        }
    }

   public void addSeason(){
       try {
           Stage stage = new Stage();
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/view/AddSeasonView.fxml"));
           Parent root1 = fxmlLoader.load();
           AddSeasonController controller = (AddSeasonController) fxmlLoader.getController();
           stage.setScene(new Scene(root1));
           stage.setTitle("Dodaj typy");
           stage.initModality(Modality.APPLICATION_MODAL);
           controller.setStage(stage);
           stage.setResizable(false);
           stage.setWidth(650.0);
           stage.setHeight(400.0);
           stage.show();
       }catch (Exception e){
       }
    }

    public void openOptions(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/view/OptionsView.fxml"));
            Parent root1 = fxmlLoader.load();
            OptionsController controller = (OptionsController) fxmlLoader.getController();
            stage.setScene(new Scene(root1));
            stage.setTitle("Sezon");
            stage.initModality(Modality.APPLICATION_MODAL);
            controller.setStage(stage);
            stage.setResizable(false);
            stage.setWidth(300);
            stage.setHeight(220);
            stage.show();

        }catch (Exception e){
        }

    }
    public void ViewPlayer(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/view/ViewPlayerView.fxml"));
            Parent root1 = fxmlLoader.load();
            ViewPlayerController controller = (ViewPlayerController) fxmlLoader.getController();
            stage.setScene(new Scene(root1));
            stage.setTitle("");
            stage.initModality(Modality.APPLICATION_MODAL);
            controller.setStage(stage);
            stage.setResizable(false);
            stage.setWidth(650.0);
            stage.setHeight(400.0);
            stage.show();

        }catch (Exception e){
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setItems(fillTable());
        table.getColumns().addAll(name, score);
        name.setCellValueFactory( new PropertyValueFactory<>("name"));
        score.setCellValueFactory(new PropertyValueFactory<>("email"));

        FilteredList<Player> filterData = new FilteredList<>(data, p -> true);
        filterText.textProperty().addListener(((observable, oldValue, newValue) -> {
            filterData.setPredicate(player -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (player.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (player.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        }));

        SortedList<Player> sortedData = new SortedList<>(filterData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);

    }

    public ObservableList<Player> fillTable(){
        List<Player> list;

        JDBC db = new JDBC();
        list = db.getAllPlayers();

        for (Player player :
                list) {
            data.add(player);

        }
        return  data;
    }
}
