package com.example.beckyrutherford_comp228lab5;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.List;

public class AddPlayerController {

    public static void showAddPlayerForm(ListView<String> playersListBox) {
        //Create new stage for Add Game Form
        Stage addPlayerStage = new Stage();
        addPlayerStage.initModality(Modality.APPLICATION_MODAL);
        addPlayerStage.setTitle("Add New Player");

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
            //get data from text fields
            String firstNameData = firstName.getText();
            String lastNameData = lastName.getText();
            String addressData = address.getText();
            String postalCodeData = postalCode.getText();
            String provinceData = province.getText();
            String phoneNumberData = phoneNumber.getText();

            List<String> updatedPlayersList = DatabaseConnection.addPlayerToList(firstNameData,lastNameData,addressData,postalCodeData,provinceData,phoneNumberData);
            playersListBox.getItems().clear();
            playersListBox.getItems().addAll(updatedPlayersList);

            addPlayerStage.close();
        });

        VBox addPlayerForm = new VBox();
        addPlayerForm.getChildren().addAll(addPlayerLabel,firstNameLabel,firstName,lastNameLabel,lastName,addressLabel,address,postalCodeLabel,postalCode,provinceLabel,province,phoneNumberLabel,phoneNumber,addButton);
        addPlayerForm.setAlignment(Pos.CENTER);
        addPlayerForm.setPrefWidth(350);
        VBox.setMargin(addPlayerLabel, new Insets(15,15,15,15));
        VBox.setMargin(addButton, new Insets(15,15,15,15));

        //Styles
        addPlayerLabel.setStyle("-fx-font-size:28; -fx-text-fill: #FF6347;");
        addButton.setStyle("-fx-font-size:18; -fx-font-weight: bold; -fx-background-color: #FFD700; -fx-text-fill: #FF6347;-fx-effect: dropshadow(three-pass-box, #00BFFF, 10, 0, 0, 0);");
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
        Scene addPlayerScene = new Scene(addPlayerForm);
        addPlayerScene.getRoot().setStyle("-fx-background-color: #1F1F1F;");
        addPlayerStage.setScene(addPlayerScene);
        addPlayerStage.showAndWait();
    }
}
