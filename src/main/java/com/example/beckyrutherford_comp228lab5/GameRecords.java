package com.example.beckyrutherford_comp228lab5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class GameRecords extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        //Initial gridpane
        GridPane pane = new GridPane();

        //App Title
        Label appTitle = new Label("Game Records");

        //List of Games
        Label listOfGames = new Label("Games");
        ListView<String> gamesListBox = new ListView<>();

        //Populate the gamesListBox from database
        List<String> gamesListData = DatabaseConnection.getGamesList();
        gamesListBox.getItems().addAll(gamesListData);


        //List of Players
        Label listOfPlayers = new Label("Players");
        ListView<String> playersListBox = new ListView<>();

        //Populate the playersListBox from database
        List<String> playersListData = DatabaseConnection.getPlayersList();
        playersListBox.getItems().addAll(playersListData);

        //Buttons
        Button addGame = new Button("Add Game");
        Button addPlayer = new Button("Add Player");
        Button enterGameStats = new Button("Enter Game Scores");
        Button viewPlayer = new Button("View Player Stats");
        Button editPlayer = new Button("Update Player");

        //Button actions
        addGame.setOnAction(actionEvent -> AddGameController.showAddGameForm(gamesListBox));
        addPlayer.setOnAction(actionEvent -> AddPlayerController.showAddPlayerForm(playersListBox));
        enterGameStats.setOnAction(actionEvent -> EnterGameStatsController.showGameStatsForm(playersListBox));
        editPlayer.setOnAction(actionEvent -> UpdatePlayerController.showUpdatedPlayerForm(playersListBox));

        //VBox for PlayerButtons
        VBox playerButtons = new VBox();
        playerButtons.getChildren().addAll(enterGameStats,viewPlayer,editPlayer);

        //Add elements to pane
        pane.add(appTitle,1,0,2,1);
        pane.add(listOfGames,0,1);
        pane.add(listOfPlayers,3,1);
        pane.add(gamesListBox,0,2);
        pane.add(playersListBox, 3,2);
        pane.add(addGame,0,3);
        pane.add(addPlayer,3,3);
        pane.add(playerButtons,4,2);

        //Add pane to scene,stage
        Scene scene = new Scene(pane);
        stage.setTitle("Game Records");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}