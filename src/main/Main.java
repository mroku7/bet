package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.MainController;
import main.data.JDBC;
import java.util.ArrayList;
import java.util.List;
public class Main extends Application {

    public static Integer seasonID = 0;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
        Scene scene = new Scene(root);
        stage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        MainController main = new MainController();
        JDBC db = new JDBC();
        seasonID = db.getLastSeason();
        System.out.println(seasonID);
        if(seasonID!=0)
            setTitle();
    }

    public static void setTitle(){
        JDBC db = new JDBC();
        List <Integer> year= new ArrayList<>(db.getSeasonYearByID(seasonID));
        stage.setTitle("Typer Polska Liga "+year.get(0)+"/"+year.get(1));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
