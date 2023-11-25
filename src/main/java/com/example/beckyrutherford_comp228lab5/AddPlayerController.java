package com.example.beckyrutherford_comp228lab5;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddPlayerController {


    public static void showAddPlayerForm() {
        //Create new stage for Add Game Form
        Stage addPlayerStage = new Stage();
        addPlayerStage.initModality(Modality.APPLICATION_MODAL);
        addPlayerStage.setTitle("Add New Game");

        //Form components
        Label addPlayerLabel= new Label("Player Information");
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
        Button addButton =new Button("Add Player");

        //AddButton Logic
        addButton.setOnAction(actionEvent -> {
            String firstNameData = firstName.getText();
            String lastNameData = lastName.getText();
            String addressData = address.getText();
            String postalCodeData = postalCode.getText();
            String provinceData = province.getText();
            String phoneNumberData = phoneNumber.getText();

            //Add logic here to insert new game into the database

            addPlayerStage.close();
        });

        VBox addPlayerForm = new VBox();
        addPlayerForm.getChildren().addAll(addPlayerLabel,firstNameLabel,firstName,lastNameLabel,lastName,addressLabel,address,postalCodeLabel,postalCode,provinceLabel,province,phoneNumberLabel,phoneNumber,addButton);

        //Add VBox to scene/stage
        Scene addPlayerScene = new Scene(addPlayerForm);
        addPlayerStage.setScene(addPlayerScene);
        addPlayerStage.showAndWait();
    }
}
