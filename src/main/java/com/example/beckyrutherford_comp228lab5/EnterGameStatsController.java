package com.example.beckyrutherford_comp228lab5;

import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class EnterGameStatsController {
    public static void showGameStatsForm(ListView<String> playersListBox){
        System.out.println("showGameStatsForm is called");
        //Display alert if no player is selected
        String selectedPlayer= playersListBox.getSelectionModel().getSelectedItem();
        if(selectedPlayer==null || selectedPlayer.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Player Selected");
            alert.setContentText("Please select a player to enter game stats");
            alert.showAndWait();
            return;
        }
        //Store first and last name into string array
        String[] playerName = selectedPlayer.split(" ");
        String firstNameFromList = playerName[0];
        String lastNameFromList = playerName [1];

        //Create new stage for Game Stats Form
        Stage enterGameStatsStage = new Stage();
        enterGameStatsStage.initModality(Modality.APPLICATION_MODAL);
        enterGameStatsStage.setTitle("Enter Game Stats");

        //Form Components
        Label gameStatsLabel= new Label("Enter Game Statistics for "+firstNameFromList+" "+lastNameFromList);
        Label selectGameLabel = new Label("Select Game:");
        ComboBox gamesList = new ComboBox<>();
        //get games list from database for Combobox
        List<String> gameNames = DatabaseConnection.getGamesList();
        gamesList.getItems().addAll(gameNames);

        Label datePlayedLabel = new Label("Date Played:");
        DatePicker datePicker=new DatePicker(LocalDate.now());
        Label scoreLabel = new Label ("Score:");
        TextField scoreTextField = new TextField();
        Button submitGameButton = new Button("Submit");

        //Submit button logic
        submitGameButton.setOnAction(actionEvent -> {

            //Get game_id for selected game
            String selectedGame = gamesList.getSelectionModel().getSelectedItem().toString();
            int gameId = DatabaseConnection.getGameId(selectedGame);
            //Get player_id for selected player
            int playerId = DatabaseConnection.getPlayerID(firstNameFromList,lastNameFromList);
            LocalDate datePlayed = datePicker.getValue();
            int gameScore = Integer.parseInt(scoreTextField.getText());
            DatabaseConnection.addPlayerGameRecord(gameId,playerId,datePlayed,gameScore);


            //Display confirmation
            Alert gameStatsUpdated = new Alert(Alert.AlertType.INFORMATION);
            gameStatsUpdated.setTitle("Game Stats Submitted");
            gameStatsUpdated.setContentText("Game Stats submitted successfully!");
            gameStatsUpdated.showAndWait();

            enterGameStatsStage.close();
        });

        //VBox for submit stats form
        VBox submitGameStatsForm = new VBox();
        submitGameStatsForm.getChildren().addAll(gameStatsLabel,selectGameLabel,gamesList,datePlayedLabel,datePicker,scoreLabel,scoreTextField,submitGameButton);
        //Add VBox to scene/stage
        Scene submitGameStatsScene = new Scene(submitGameStatsForm);
        enterGameStatsStage.setScene(submitGameStatsScene);
        enterGameStatsStage.showAndWait();


    }
}
