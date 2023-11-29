package com.example.beckyrutherford_comp228lab5;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewPlayerStatsController {
    public static void showPlayerStatsReport(ListView<String> playersListBox) {
        String selectedPlayer=playersListBox.getSelectionModel().getSelectedItem();
        if(selectedPlayer==null || selectedPlayer.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Player Selected");
            alert.setContentText("Please select a player to view game stats");
            alert.showAndWait();
            return;
        }
        //Store first and last name into string array
        String[] playerName = selectedPlayer.split(" ");
        String firstNameFromList = playerName[0];
        String lastNameFromList = playerName [1];

        //Create new stage for Player Stat Report
        Stage playerStatsStage = new Stage();
        playerStatsStage.initModality(Modality.APPLICATION_MODAL);
        playerStatsStage.setTitle("Player Statistics");

        //Report Components
        Label reportLabel = new Label(firstNameFromList+"'s Game Stats");
        TableView<String> reportTable = new TableView<>();

        //Columns
        TableColumn firstColumn = new TableColumn("Date");
        TableColumn secondColumn = new TableColumn("Game");
        TableColumn thirdColumn = new TableColumn("Score");

        reportTable.getColumns().addAll(firstColumn,secondColumn,thirdColumn);

        //TODO call method from database connection then populate table with appropriate data


        //OK Button logic
        Button okButton = new Button("OK");
        okButton.setOnAction(actionEvent -> {
            playerStatsStage.close();
        });

        //VBox for report modal
        VBox reportContainer = new VBox();
        reportContainer.getChildren().addAll(reportLabel,reportTable,okButton);

        //Add VBox to scene/stage
        Scene reportTableScene = new Scene(reportContainer);
        playerStatsStage.setScene(reportTableScene);
        playerStatsStage.showAndWait();



    }
}
