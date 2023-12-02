package com.example.beckyrutherford_comp228lab5;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;

public class UpdatePlayerController {
    public static void showUpdatedPlayerForm(ListView<String> playersListBox){
        System.out.println("showUpdatedForm is called");
        //Display alert if no player is selected
        String selectedPlayer= playersListBox.getSelectionModel().getSelectedItem();
        if(selectedPlayer==null || selectedPlayer.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Player Selected");
            alert.setContentText("Please select a player to update");
            alert.showAndWait();
            return;
        }

        //Store first and last name into string array
        String[] playerName = selectedPlayer.split(" ");
        String firstNameFromList = playerName[0];
        String lastNameFromList = playerName [1];

        //Create new stage for Update Player Form
        Stage updatePlayerStage = new Stage();
        updatePlayerStage.setWidth(500);
        updatePlayerStage.initModality(Modality.APPLICATION_MODAL);
        updatePlayerStage.setTitle("Update Player");

        //Form components
        Label updatePlayerLabel= new Label("Current Player Information");
        Label updatePlayerDirections = new Label("Modify fields to update player demographic data.");
        Label firstNameLabel = new Label ("First Name:");
        TextField firstName = new TextField();
        Label lastNameLabel = new Label ("Last Name:");
        TextField lastName = new TextField();
        Label addressLabel = new Label ("Street Address:");
        TextField address = new TextField();
        Label postalCodeLabel = new Label ("Postal Code:");
        TextField postalCode = new TextField();
        Label provinceLabel = new Label ("Province:");
        TextField province = new TextField();
        Label phoneNumberLabel = new Label ("Phone Number:");
        TextField phoneNumber = new TextField();
        Button updateButton =new Button("Update Player");

        //Populate text fields with current information
        firstName.setText(firstNameFromList);
        lastName.setText(lastNameFromList);
        //get current demo data from database
        Map<String, String> currentPlayerDemoData =DatabaseConnection.getPlayerDemoData(firstNameFromList,lastNameFromList);
        //populate text fields with current information from database
        address.setText(currentPlayerDemoData.get("Address"));
        postalCode.setText(currentPlayerDemoData.get("Postal Code"));
        province.setText(currentPlayerDemoData.get("Province"));
        phoneNumber.setText(currentPlayerDemoData.get("Phone Number"));


        //UpdatePlayerButton logic
        updateButton.setOnAction(actionEvent -> {
            //get data from text fields
            String firstNameData = firstName.getText();
            String lastNameData = lastName.getText();
            String addressData = address.getText();
            String postalCodeData = postalCode.getText();
            String provinceData = province.getText();
            String phoneNumberData = phoneNumber.getText();

            DatabaseConnection.updatePlayerDemo(firstNameData,lastNameData,addressData,postalCodeData,provinceData,phoneNumberData);
            //Display confirmation
            Alert databaseUpdated = new Alert(Alert.AlertType.INFORMATION);
            databaseUpdated.setTitle("Update Successful");
            databaseUpdated.setContentText("Player information updated successfully!");
            databaseUpdated.showAndWait();


            updatePlayerStage.close();
        });
        //VBox for update Form
        VBox updatePlayerForm = new VBox();
        updatePlayerForm.getChildren().addAll(updatePlayerLabel,updatePlayerDirections,firstNameLabel,firstName,lastNameLabel,lastName,addressLabel,address,postalCodeLabel,postalCode,provinceLabel,province,phoneNumberLabel,phoneNumber,updateButton);
        updatePlayerForm.setAlignment(Pos.CENTER);
        updatePlayerForm.setPrefWidth(350);
        VBox.setMargin(updatePlayerLabel, new Insets(15,15,15,15));
        VBox.setMargin(updateButton, new Insets(15,15,15,15));

        //Styles
        updatePlayerLabel.setStyle("-fx-font-size:28; -fx-text-fill: #FF6347;");
        updateButton.setStyle("-fx-font-size:18; -fx-font-weight: bold; -fx-background-color: #FFD700; -fx-text-fill: #FF6347;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");
        updatePlayerDirections.setStyle("-fx-font-size:18; -fx-text-fill: #FF6347;");
        firstNameLabel.setStyle("-fx-text-fill: #FF6347;");
        lastNameLabel.setStyle("-fx-text-fill: #FF6347;");
        addressLabel.setStyle("-fx-text-fill: #FF6347;");
        postalCodeLabel.setStyle("-fx-text-fill: #FF6347;");
        provinceLabel.setStyle("-fx-text-fill: #FF6347;");
        phoneNumberLabel.setStyle("-fx-text-fill: #FF6347;");
        firstName.setMaxWidth(200);
        lastName.setMaxWidth(200);
        address.setMaxWidth(200);
        postalCode.setMaxWidth(200);
        province.setMaxWidth(200);
        phoneNumber.setMaxWidth(200);

        //Add VBox to scene/stage
        Scene updatePlayerScene = new Scene(updatePlayerForm);
        updatePlayerScene.getRoot().setStyle("-fx-background-color: #1F1F1F;");
        updatePlayerStage.setScene(updatePlayerScene);
        updatePlayerStage.showAndWait();

    }

}
