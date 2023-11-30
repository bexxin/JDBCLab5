package com.example.beckyrutherford_comp228lab5;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
        TableView<PlayerGameRecord> reportTable = new TableView<>();

        //Columns
        TableColumn<PlayerGameRecord, String> firstColumn = new TableColumn<>("Date");
        TableColumn<PlayerGameRecord,String> secondColumn = new TableColumn<>("Game");
        TableColumn<PlayerGameRecord,String> thirdColumn = new TableColumn<>("Score");

        reportTable.getColumns().addAll(firstColumn,secondColumn,thirdColumn);

        //get GameAndPlayer records from database
        ObservableList<PlayerGameRecord> playersRecords = DatabaseConnection.getPlayerGameRecord(firstNameFromList,lastNameFromList);
        //Create cell value factory for each column
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("game"));
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("score"));


        reportTable.setItems(playersRecords);


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
