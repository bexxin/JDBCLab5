package com.example.beckyrutherford_comp228lab5;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
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
        playerStatsStage.setWidth(600);
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
        reportTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

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
        reportContainer.setAlignment(Pos.CENTER);
        reportContainer.setPrefWidth(400);
        reportContainer.setPadding(new Insets(0,20,0,20));



        VBox.setMargin(reportLabel, new Insets(15,15,15,15));
        VBox.setMargin(reportTable, new Insets (20,0,20,0));
        VBox.setMargin(okButton, new Insets(15,15,15,15));

        //Styles
        reportLabel.setStyle("-fx-font-size:24; -fx-text-fill: #FF6347;");
        reportTable.setStyle("-fx-text-fill: #FF6347; ");
        okButton.setStyle("-fx-font-size:18; -fx-font-weight: bold; -fx-background-color: #FFD700; -fx-text-fill: #FF6347;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");
        reportTable.setStyle("-fx-font-size:16;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");
        firstColumn.setStyle("-fx-background-color:#0d0000; -fx-text-fill: #FF6347 ");
        secondColumn.setStyle("-fx-background-color:#0d0000; -fx-text-fill: #FF6347 ");
        thirdColumn.setStyle("-fx-background-color:#0d0000; -fx-text-fill: #FF6347 ");


        //Add VBox to scene/stage
        Scene reportTableScene = new Scene(reportContainer);
        reportTableScene.getRoot().setStyle("-fx-background-color: #1F1F1F;");
        playerStatsStage.setScene(reportTableScene);
        playerStatsStage.showAndWait();



    }
}
