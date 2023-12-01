package com.example.beckyrutherford_comp228lab5;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class GameRecords extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        //Initial gridpane
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);

        //Title Background
        Polygon titleTriangle = new Polygon (10,10,200,0,190,150);
        titleTriangle.setTranslateY(-20);
        titleTriangle.setRotate(150);
        titleTriangle.setScaleX(1.4);
        titleTriangle.setStyle("-fx-background-color: #800000; -fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0); ");

        //App Title
        Label appTitle = new Label("Game Records");
        appTitle.setStyle("-fx-font-size:28; -fx-text-fill: #FF6347;");

        //Stackpane for title
        StackPane titlePane = new StackPane();
        titlePane.getChildren().addAll(titleTriangle,appTitle);

        //List of Games
        Label listOfGames = new Label("Games");
        GridPane.setHalignment(listOfGames, HPos.CENTER);
        listOfGames.setStyle("-fx-font-size:24; -fx-text-fill: #FF6347;");
        ListView<String> gamesListBox = new ListView<>();

        //Populate the gamesListBox from database
        List<String> gamesListData = DatabaseConnection.getGamesList();
        gamesListBox.getItems().addAll(gamesListData);


        //List of Players
        Label listOfPlayers = new Label("Players");
        GridPane.setHalignment(listOfPlayers, HPos.CENTER);
        listOfPlayers.setStyle("-fx-font-size:24; -fx-text-fill: #FF6347;");
        ListView<String> playersListBox = new ListView<>();

        //Populate the playersListBox from database
        List<String> playersListData = DatabaseConnection.getPlayersList();
        playersListBox.getItems().addAll(playersListData);
        //ListBoxStyling
        gamesListBox.setStyle("-fx-font-size:16; -fx-control-inner-background: #0d0000;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");
        playersListBox.setStyle("-fx-font-size:16;-fx-control-inner-background: #0d0000;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");

        //Buttons
        Button addGame = new Button("Add Game");
        Button addPlayer = new Button("Add Player");
        Button enterGameStats = new Button("Enter Game Scores");
        Button viewPlayer = new Button("View Player Stats");
        Button editPlayer = new Button("Update Player");
        //Button styles
        addGame.setStyle("-fx-font-size:18; -fx-font-weight: bold; -fx-background-color: #FFD700; -fx-text-fill: #FF6347;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");
        addPlayer.setStyle("-fx-font-size:18; -fx-font-weight: bold; -fx-background-color: #FFD700; -fx-text-fill: #FF6347;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");
        enterGameStats.setStyle("-fx-font-size:18; -fx-font-weight: bold; -fx-background-color: #FFD700; -fx-text-fill: #FF6347;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");
        viewPlayer.setStyle("-fx-font-size:18; -fx-font-weight: bold; -fx-background-color: #FFD700; -fx-text-fill: #FF6347;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");
        editPlayer.setStyle("-fx-font-size:18; -fx-font-weight: bold; -fx-background-color: #FFD700; -fx-text-fill: #FF6347;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");

        //Button actions
        addGame.setOnAction(actionEvent -> AddGameController.showAddGameForm(gamesListBox));
        addPlayer.setOnAction(actionEvent -> AddPlayerController.showAddPlayerForm(playersListBox));
        viewPlayer.setOnAction(actionEvent -> ViewPlayerStatsController.showPlayerStatsReport(playersListBox));
        enterGameStats.setOnAction(actionEvent -> EnterGameStatsController.showGameStatsForm(playersListBox));
        editPlayer.setOnAction(actionEvent -> UpdatePlayerController.showUpdatedPlayerForm(playersListBox));

        //VBox for PlayerButtons
        VBox playerButtons = new VBox();
        playerButtons.getChildren().addAll(enterGameStats,viewPlayer,editPlayer);
        playerButtons.setAlignment(Pos.CENTER);
        playerButtons.setSpacing(25);

        //Add elements to pane
        pane.add(titlePane,0,0,4,1);
        GridPane.setHalignment(titlePane,HPos.CENTER);
        pane.add(listOfGames,0,1);
        pane.add(addGame,0,2);
        GridPane.setHalignment(addGame, HPos.CENTER);
        pane.add(gamesListBox,0,3);
        pane.add(listOfPlayers,3,1);
        pane.add(addPlayer,3,2);
        GridPane.setHalignment(addPlayer, HPos.CENTER);
        pane.add(playersListBox, 3,3);
        pane.add(playerButtons,2,3,1,2);

        //Add pane to scene,stage
        Scene scene = new Scene(pane);
        scene.getRoot().setStyle("-fx-background-color: #1F1F1F;");
        stage.setTitle("Game Records");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}